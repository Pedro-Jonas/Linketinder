package DAO

import Interfaces.ICompanyDAO
import Models.Company
import factories.IConnectionFactory

import java.sql.*

class CompanyDAO implements ICompanyDAO {
    IConnectionFactory connectionDB

    CompanyDAO(IConnectionFactory connectionDB) {
        this.connectionDB = connectionDB
    }

    @Override
    int insertCompany(Company company) {
        PreparedStatement stm = null
        int id = 0

        String insertNewCompany = "INSERT INTO companies (name, cnpj, email," +
                " description, country, cep, password)  " +
                "VALUES (?,?,?,?,?,?,?);"

        try {
            Connection connection = connectionDB.getConnection()
            stm = connection.prepareStatement(insertNewCompany, Statement.RETURN_GENERATED_KEYS)

            stm.setString(1, company.name)
            stm.setString(2, company.cnpj)
            stm.setString(3, company.email)
            stm.setString(4, company.description)
            stm.setString(5, company.country)
            stm.setString(6, company.cep)
            stm.setString(7, company.password)

            stm.executeUpdate()
            ResultSet rs = stm.getGeneratedKeys()

            if (rs.next()) {
                id = rs.getInt(1)
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            stm.close()
        }

        return id
    }

    @Override
    List<Company> selectCompanies() {
        PreparedStatement stm = null
        List<Company> companies = new ArrayList<>()

        String selectCompanies = "SELECT * FROM companies;"

        try{
            Connection connection = connectionDB.getConnection()
            stm = connection.prepareStatement(selectCompanies)

            ResultSet result = stm.executeQuery()

            companies = this.listCompanies(result)
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            stm.close()
        }

        return companies
    }

    @Override
    boolean updateCompany(Company company, int id){
        PreparedStatement stm = null
        boolean updateLines = false

        String updateCompanyById = "UPDATE companies SET name = ?, cnpj = ?, " +
                "email = ?, description = ?, country = ?, cep = ?, password = ? " +
                "WHERE id = ?;"

        try {
            Connection connection = connectionDB.getConnection()
            stm = connection.prepareStatement(updateCompanyById)

            stm.setString(1, company.name)
            stm.setString(2, company.cnpj)
            stm.setString(3, company.email)
            stm.setString(4, company.description)
            stm.setString(5, company.country)
            stm.setString(6, company.cep)
            stm.setString(7, company.password)
            stm.setInt(7, id)

            int result = stm.executeUpdate()

            if (result != 0) {
                updateLines = true
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            stm.close()
        }

        return updateLines
    }

    @Override
    boolean deleteCompany(int id){
        PreparedStatement stm = null
        boolean hasDelete = false

        String deleteCompanyById = "DELETE FROM companies WHERE id = ?;"

        try {
            Connection connection = connectionDB.getConnection()
            stm = connection.prepareStatement(deleteCompanyById)

            stm.setInt(1, id)

            int result = stm.executeUpdate()

            if (result != 0) {
               hasDelete = true
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            stm.close()
        }

        return hasDelete
    }

    @Override
    List<Company> listCompanies(ResultSet result) {
        List<Company> companies = new ArrayList<>()

        while (result.next()) {

            Company company = new Company()

            company.setId(result.getInt("id"))
            company.setName(result.getString("name"))
            company.setEmail(result.getString("email"))
            company.setCnpj(result.getString("cnpj"))
            company.setCountry(result.getString("country"))
            company.setCep(result.getString("cep"))
            company.setDescription(result.getString("description"))
            company.setPassword(result.getString("password"))

            companies.add(company)
        }

        return companies
    }
}
