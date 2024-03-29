package DAO

import java.sql.*

class CandidateDAO {
    ConnectionDAO connectionDAO =  new ConnectionDAO()

    void showCandidates() {

        Connection connection = connectionDAO.connection()
        Statement stm = connection.createStatement()
        String query = "SELECT cd.*, ARRAY_AGG(sk.name) skills FROM candidates AS cd INNER JOIN " +
                "candidate_skill AS ck " +
                "on cd.id = ck.candidate_id INNER JOIN " +
                "skill AS sk " +
                "ON ck.skill_id = sk.id " +
                "GROUP BY cd.id;"

        try{
            ResultSet result = stm.executeQuery(query)
            while (result.next()) {

                String candidate = "nome - " +  result.getString("frist_name") + "\n" +
                        "sobrenome - " + result.getString("last_name") + "\n" +
                        "data de nascimento - " + result.getString("date_of_birth") + "\n" +
                        "email - " + result.getString("email") + "\n" +
                        "cpf - " + result.getString("cpf") + "\n" +
                        "país - " + result.getString("country") + "\n" +
                        "CEP - " + result.getString("cep") + "\n" +
                        "descrição - " + result.getString("description") + "\n" +
                        "senha - " + result.getString("password") + "\n" +
                        "skills - " + result.getArray("skills") + "\n"

                println candidate
            }
        } catch (SQLException e) {
            e.printStackTrace()
        }

        connection.close()
    }
}
