package DAO

import java.sql.*

class SkillDAO {
    ConnectionDB connectionDB =  new ConnectionDB()

    int selectSkillForName(String skill) {
        Connection connection = null
        PreparedStatement stm = null
        int skillId = 0

        String selectSkillForName = "SELECT * FROM skill sk WHERE sk.name = ?";

        try {
            connection = connectionDB.connection()
            stm = connection.prepareStatement(selectSkillForName)

            stm.setString(1, skill)

            ResultSet result = stm.executeQuery()

            while (result.next()) {
                skillId = result.getInt("id")
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }
        return skillId
    }

    int insertSkill(String skill) {
        Connection connection = null
        PreparedStatement stm = null
        int skillId = 0

        String insertSkill = "INSERT INTO skill (name) VALUES(?);"

        try {
            connection = connectionDB.connection()
            stm = connection.prepareStatement(insertSkill, Statement.RETURN_GENERATED_KEYS)

            stm.setString(1, skill)

            stm.execute()
            ResultSet rs = stm.getGeneratedKeys()

            if (rs.next()) {
                skillId = rs.getInt(1)
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }

        return skillId
    }
}
