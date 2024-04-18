package DAO

import Interfaces.ICompanyDAO
import Models.Company

import java.sql.*

class CompanyDAO implements ICompanyDAO {
    ConnectionDB connectionDAO =  new ConnectionDB()

    @Override
    List<Company> selectCompanies() {
        Connection connection = null
        PreparedStatement stm = null
        List<Company> companies = new ArrayList<>()

        String selectCompanies = "SELECT * FROM companies;"

        try{
            connection = connectionDAO.connection()
            stm = connection.prepareStatement(selectCompanies)

            ResultSet result = stm.executeQuery()

            companies = this.listCompanies(result)
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }

        return companies
    }

    @Override
    int insertCompany(Company company) {
        Connection connection = null
        PreparedStatement stm = null
        int id = 0

        String insertCompany = "INSERT INTO companies (name, cnpj, email," +
                " description, country, cep, password)  " +
                "VALUES (?,?,?,?,?,?,?);"

        try {
            connection = ConnectionDB.connection()
            stm = connection.prepareStatement(insertCompany, Statement.RETURN_GENERATED_KEYS)

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
            connection.close()
            stm.close()
        }

        return id
    }

    @Override
    boolean updateCompany(Company company, int id){
        Connection connection = null
        PreparedStatement stm = null
        boolean updateLines = false

        String updateCompany = "UPDATE companies SET name = ?, cnpj = ?, " +
                "email = ?, description = ?, country = ?, cep = ?, password = ? " +
                "WHERE id = ?;"

        try {
            connection = ConnectionDB.connection()
            stm = connection.prepareStatement(updateCompany)

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
            connection.close()
            stm.close()
        }

        return updateLines
    }

    @Override
    boolean deleteCompany(int id){
        Connection connection = null
        PreparedStatement stm = null
        boolean hasDelete = false

        String deleteCompany = "DELETE FROM companies WHERE id = ?;"

        try {
            connection = ConnectionDB.connection()
            stm = connection.prepareStatement(deleteCompany)

            stm.setInt(1, id)

            int result = stm.executeUpdate()

            if (result != 0) {
               hasDelete = true
            }
        } catch (SQLException e) {
            println e
        } finally {
            connection.close()
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
