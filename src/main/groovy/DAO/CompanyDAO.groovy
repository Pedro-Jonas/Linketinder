package DAO

import Models.Company

import java.sql.*

class CompanyDAO {
    ConnectionDB connectionDAO =  new ConnectionDB()

    void showCompanies() {
        Connection connection = connectionDAO.connection()

        String query = "SELECT * FROM companies;"

        PreparedStatement stm = connection.prepareStatement(query)

        try{
            ResultSet result = stm.executeQuery()
            printCompanies(result)
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
        }
    }

    void insertCompany(Company company){
        int id = 0
        Connection connection = ConnectionDB.connection()

        String query = "INSERT INTO companies (name, cnpj, email," +
                " description, country, cep, password)  " +
                "VALUES (?,?,?,?,?,?,?);"

        PreparedStatement stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)

        stm.setString(1, company.name)
        stm.setString(2, company.cnpj)
        stm.setString(3, company.email)
        stm.setString(4, company.description)
        stm.setString(5, company.country)
        stm.setString(6, company.cep)
        stm.setString(7, company.password)

        try {
            int result = stm.executeUpdate()
            ResultSet rs = stm.getGeneratedKeys()

            if (rs.next()) {
                id = rs.getInt(1)
            }

            if (result == 0) {
                println "Falha ao inserir empresa!"
            } else {
                println "Empresa ${company.name} com id - ${id} inserida com sucesso!"
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }
    }

    static void updateCompany(Company company, int id){
        Connection connection = ConnectionDB.connection()

        String query = "UPDATE companies SET name = ?, cnpj = ?, " +
                "email = ?, description = ?, country = ?, cep = ?, password = ? " +
                "WHERE id = ${id};"

        PreparedStatement stm = connection.prepareStatement(query)

        stm.setString(1, company.name)
        stm.setString(2, company.cnpj)
        stm.setString(3, company.email)
        stm.setString(4, company.description)
        stm.setString(5, company.country)
        stm.setString(6, company.cep)
        stm.setString(7, company.password)

        try {
            int result = stm.executeUpdate()

            if (result == 0) {
                println "Falha ao atualizar empresa!"
            } else {
                println "Empresa atualizada com sucesso!"
            }

        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }
    }

    static void deleteCompany(int id){
        Connection connection = ConnectionDB.connection()

        String query = "DELETE FROM companies WHERE id=${id};"

        PreparedStatement stm = connection.prepareStatement(query)

        try {
            int result = stm.executeUpdate()

            if (result == 0) {
                println "Falha ao deletar empresa ou empresa inexistente!"
            } else {
                println "Empresa com id - ${id} deletada com sucesso!"
            }
        } catch (SQLException e) {
            println e
        } finally {
            connection.close()
            stm.close()
        }
    }

    private static void printCompanies(ResultSet result) {
        while (result.next()) {
            String company = "id - " + result.getInt("id") + "\n" +
                    "nome - " +  result.getString("name") + "\n" +
                    "CNPJ - " + result.getString("cnpj") + "\n" +
                    "email - " + result.getString("email") + "\n" +
                    "descrição - " + result.getString("description") + "\n" +
                    "país - " + result.getString("country") + "\n" +
                    "CEP - " + result.getString("cep") + "\n" +
                    "senha - " + result.getString("password") + "\n"

            println company
        }
    }
}
