package DAO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class SkillDAO {
    ConnectionDAO connectionDAO =  new ConnectionDAO()

    static int selectSkillForName(String skill) {
        Connection connection = ConnectionDAO.connection()

        String query1 = "SELECT * FROM skill sk WHERE sk.name = '${skill}'";
        PreparedStatement stm = connection.prepareStatement(query1)

        int skill_id = 0

        try {
            ResultSet result = stm.executeQuery()

            while (result.next()) {
                skill_id = result.getInt("id")
            }

        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }
        return skill_id
    }

    static int insertSkill(String skill) {
        Connection connection = ConnectionDAO.connection()
        String query2 = "INSERT INTO skill (name) VALUES(?);"
        PreparedStatement stm = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS)

        stm.setString(1, skill)

        int skill_id = 0

        try {
            stm.execute()
            ResultSet rs = stm.getGeneratedKeys()

            if (rs.next()) {
                skill_id = rs.getInt(1)
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }

        return skill_id
    }
}
