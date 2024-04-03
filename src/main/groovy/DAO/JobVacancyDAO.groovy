package DAO

import Classes.JobVacancy

import java.sql.*

class JobVacancyDAO {
    ConnectionDAO connectionDAO =  new ConnectionDAO()

    void showJobVacancies(){
        Connection connection = connectionDAO.connection()

        String query = "SELECT jv.*, ARRAY_AGG(sk.name) skills FROM job_vacancies AS jv INNER JOIN " +
                "job_vacancies_skill AS jsk " +
                "ON jv.id = jsk.job_vacancy_id INNER JOIN " +
                "skill AS sk " +
                "ON jsk.skill_id = sk.id " +
                "GROUP BY jv.id;"

        PreparedStatement stm = connection.prepareStatement(query)

        try{
            ResultSet result = stm.executeQuery()

            while (result.next()) {
                String candidate = "id - " + result.getInt("id") + "\n" +
                        "nome - " +  result.getString("name") + "\n" +
                        "descrição - " + result.getString("description") + "\n" +
                        "estado - " + result.getString("state") + "\n" +
                        "cidade - " + result.getString("city") + "\n" +
                        "skills - " + result.getArray("skills") + "\n"

                println candidate
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
        }
    }

    static int insertJobVacancy(JobVacancy jobVacancy){
        int id = 0
        Connection connection = ConnectionDAO.connection()

        String query = "INSERT INTO job_vacancies (name, state, city, description, company_id)  " +
                "VALUES (?,?,?,?,?);"

        PreparedStatement stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)

        stm.setString(1, jobVacancy.name)
        stm.setString(2, jobVacancy.state)
        stm.setString(3, jobVacancy.city)
        stm.setString(4, jobVacancy.description)
        stm.setInt(5, jobVacancy.companyId)

        try {
            int result = stm.executeUpdate()
            try (ResultSet rs = stm.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt(1)
                }
            }
            if (result == 0) {
                println "Falha ao inserir vaga!"
            } else {
                println "Vaga ${jobVacancy.name} com id - ${id} inserido com sucesso!"
            }

        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }

        return id
    }

    static void insertSkillJobVacancy(int id, String skill){
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

        String query3 = "INSERT INTO job_vacancies_skill (job_vacancy_id, skill_id) VALUES (?,?);"
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

    static void deleteJobVacancy(int id){
        Connection connection = ConnectionDAO.connection()

        String query = "DELETE FROM job_vacancies WHERE id=${id};"

        PreparedStatement stm = connection.prepareStatement(query)

        try {
            int result = stm.executeUpdate()
            if (result == 0) {
                println "Falha ao deletar vaga ou vaga inexistente!"
            } else {
                println "Vaga com id - ${id} deletada com sucesso!"
            }
        } catch (SQLException e) {
            println e
        }
    }

    static void updateJobVacancy(JobVacancy jobVacancy, int id){
        Connection connection = ConnectionDAO.connection()

        String query = "UPDATE job_vacancies SET name = ?, state = ?, city = ?, " +
                "description = ?, company_id = ? " +
                "WHERE id = ${id};"

        PreparedStatement stm = connection.prepareStatement(query)

        stm.setString(1, jobVacancy.name)
        stm.setString(2, jobVacancy.state)
        stm.setString(3, jobVacancy.city)
        stm.setString(4, jobVacancy.description)
        stm.setInt(5, jobVacancy.companyId)

        try {
            int result = stm.executeUpdate()

            if (result == 0) {
                println "Falha ao atualizar vaga!"
            } else {
                println "Vaga atualizada com sucesso!"
            }

        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }
    }
}
