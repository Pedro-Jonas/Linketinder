package DAO

import Classes.Candidate

import java.sql.*

class CandidateDAO {
    ConnectionDAO connectionDAO =  new ConnectionDAO()

    void showCandidates() {
        Connection connection = connectionDAO.connection()

        String query = "SELECT cd.*, ARRAY_AGG(sk.name) skills FROM candidates AS cd INNER JOIN " +
                "candidate_skill AS ck " +
                "ON cd.id = ck.candidate_id INNER JOIN " +
                "skill AS sk " +
                "ON ck.skill_id = sk.id " +
                "GROUP BY cd.id;"

        PreparedStatement stm = connection.prepareStatement(query)

        try{

            ResultSet result = stm.executeQuery()
            printCandidates(result)

        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
        }
    }

    static int insertCandidate(Candidate candidate) {
        int id = 0
        Connection connection = ConnectionDAO.connection()

        String query = "INSERT INTO candidates (first_name, last_name, date_of_Birth," +
                " email, cpf, country, cep, description, password)  " +
                "VALUES (?,?,?,?,?,?,?,?,?);"

        PreparedStatement stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)

        Date dateOf_birth = new Date(candidate.dateOfBirth.getTime())

        stm.setString(1, candidate.firstName)
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

            ResultSet rs = stm.getGeneratedKeys()

            if (rs.next()) {
                id = rs.getInt(1)
            }

            if (result == 0) {
                println "Falha ao inserir candidato!"
            } else {
                println "Candidado ${candidate.firstName} com id - ${id} inserido com sucesso!"
            }

        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }

        return id
    }

    static void insertSkillCandidate(int id, String skill) {
        Connection connection = ConnectionDAO.connection()

        String query1 = "SELECT * FROM skill sk WHERE sk.name = '${skill}'";
        PreparedStatement stm1 = connection.prepareStatement(query1)

        String query2 = "INSERT INTO skill (name) VALUES(?);"
        PreparedStatement stm2 = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS)
        int skill_id = 0

        stm2.setString(1,skill)

        try {
            ResultSet result = stm1.executeQuery()

            int count = 0
            while (result.next()) {
                skill_id = result.getInt("id")
                count++
            }
            if (count == 0) {
                stm2.executeUpdate()
                try (ResultSet rs = stm2.getGeneratedKeys()) {
                    if (rs.next()) {
                        skill_id = rs.getInt(1)
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace()
        }

        String query3 = "INSERT INTO candidate_skill (candidate_id, skill_id) VALUES (?,?);"
        PreparedStatement stm3 = connection.prepareStatement(query3)

        stm3.setInt(1, id)
        stm3.setInt(2, skill_id)
        try {
            stm3.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm1.close()
            stm2.close()
            stm3.close()
        }
    }

    static void deleteCandidate(int id) {
        Connection connection = ConnectionDAO.connection()

        String query = "DELETE FROM candidates WHERE id=${id};"

        PreparedStatement stm = connection.prepareStatement(query)

        try {
            int result = stm.executeUpdate()

            if (result == 0) {
                println "Falha ao deletar candidato ou candidato inexistente!"
            } else {
                println "Candidado com id - ${id} deletado com sucesso!"
            }
        } catch (SQLException e) {
            println e
        } finally {
            connection.close()
            stm.close()
        }
    }

    static void updateCandidate(Candidate candidate, int id) {
        Connection connection = ConnectionDAO.connection()

        String query = "UPDATE candidates SET frist_name = ?, last_name = ?, date_of_Birth = ?, " +
                "email = ?, cpf = ?, country = ?, cep = ?, description = ?, password = ? " +
                "WHERE id = ${id};"

        PreparedStatement stm = connection.prepareStatement(query)

        Date dateOf_birth = new Date(candidate.dateOfBirth.getTime())

        stm.setString(1, candidate.firstName)
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

            if (result == 0) {
                println "Falha ao atualizar candidato!"
            } else {
                println "Candidado atualizado com sucesso!"
            }

        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }
    }

    static void printCandidates(ResultSet result) {
        while (result.next()) {
            String candidate = "id - " + result.getInt("id") + "\n" +
                    "nome - " +  result.getString("first_name") + "\n" +
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
    }
}
