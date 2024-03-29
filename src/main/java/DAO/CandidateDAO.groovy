package DAO

import Classes.Candidate

import java.sql.*
import java.text.SimpleDateFormat

class CandidateDAO {
    ConnectionDAO connectionDAO =  new ConnectionDAO()

    void showCandidates() {
        Connection connection = connectionDAO.connection()

        String query = "SELECT cd.*, ARRAY_AGG(sk.name) skills FROM candidates AS cd INNER JOIN " +
                "candidate_skill AS ck " +
                "on cd.id = ck.candidate_id INNER JOIN " +
                "skill AS sk " +
                "ON ck.skill_id = sk.id " +
                "GROUP BY cd.id;"

        PreparedStatement stm = connection.prepareStatement(query)

        try{
            ResultSet result = stm.executeQuery()

            while (result.next()) {
                String candidate = "id - " + result.getInt("id") + "\n" +
                        "nome - " +  result.getString("frist_name") + "\n" +
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
        } finally {
            connection.close()
        }
    }

    static void insertCandidate(Candidate candidate){
        Connection connection = ConnectionDAO.connection()

        String query = "INSERT INTO candidates (frist_name, last_name, date_of_Birth," +
                " email, cpf, country, cep, description, password)  " +
                "VALUES (?,?,?,?,?,?,?,?,?);"

        PreparedStatement stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);


        Date dateOf_birth = new Date(candidate.dateOfBirth.getTime())

        stm.setString(1, candidate.fristName)
        stm.setString(2, candidate.lastName)
        stm.setDate(3, dateOf_birth)
        stm.setString(4, candidate.email)
        stm.setString(5, candidate.cpf)
        stm.setString(6, candidate.country)
        stm.setString(7, candidate.cep)
        stm.setString(8, candidate.description)
        stm.setString(9, candidate.password)

        try {
            int result = stm.executeUpdate()
            int id = 0
            try (ResultSet rs = stm.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
            if (result == 0) {
                println "Falha ao inserir candidato!"
            } else {
                println "Candidado ${candidate.fristName} com id - ${id} inserido com sucesso!"
            }

        } catch (SQLException e) {
            println e
        }
    }

    static void deleteCandidate(int id){
        Connection connection = ConnectionDAO.connection()

        String query = "DELETE FROM candidates WHERE id=${id};"

        PreparedStatement stm = connection.prepareStatement(query);

        try {
            int result = stm.executeUpdate();
            if (result == 0) {
                println "Falha ao deletar candidato ou candidato inexistente!"
            } else {
                println "Candidado com id - ${id} deletado com sucesso!"
            }
        } catch (SQLException e) {
            println e
        }
    }


}
