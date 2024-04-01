package DAO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

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
}
