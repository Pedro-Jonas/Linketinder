package DAO

import Classes.Company

import java.sql.*

class CompanieDAO {
    ConnectionDAO connectionDAO =  new ConnectionDAO()

    void showCompanies() {
        Connection connection = connectionDAO.connection()

        String query = "SELECT * FROM companies;"

        PreparedStatement stm = connection.prepareStatement(query)

        try{
            ResultSet result = stm.executeQuery()

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
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
        }
    }

    void insertCompanie(Company companie){
        int id = 0
        Connection connection = ConnectionDAO.connection()

        String query = "INSERT INTO companies (name, cnpj, email," +
                " description, country, cep, password)  " +
                "VALUES (?,?,?,?,?,?,?);"

        PreparedStatement stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)

        stm.setString(1, companie.name)
        stm.setString(2, companie.cnpj)
        stm.setString(3, companie.email)
        stm.setString(4, companie.description)
        stm.setString(5, companie.country)
        stm.setString(6, companie.cep)
        stm.setString(7, companie.password)

        try {
            int result = stm.executeUpdate()
            try (ResultSet rs = stm.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt(1)
                }
            }
            if (result == 0) {
                println "Falha ao inserir empresa!"
            } else {
                println "Empresa ${companie.name} com id - ${id} inserida com sucesso!"
            }

        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }
    }
}
