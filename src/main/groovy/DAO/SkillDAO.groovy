package DAO

import Interfaces.IConnectionDB
import Interfaces.ISkillDAO

import java.sql.*

class SkillDAO implements ISkillDAO {

    IConnectionDB connectionDB

    SkillDAO(IConnectionDB connectionDB){
        this.connectionDB = connectionDB
    }

    @Override
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

    @Override
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

    @Override
    int insertSkillCandidate(int id, String skill) {
        Connection connection = null
        PreparedStatement stm = null

        int  skillId = this.selectSkillForName(skill)

        if(skillId == 0) {
            skillId = this.insertSkill(skill)
        }

        String insertSkillCandidate = "INSERT INTO candidate_skill (candidate_id, skill_id) VALUES (?,?);"

        try {
            connection = connectionDB.connection()
            stm = connection.prepareStatement(insertSkillCandidate)

            stm.setInt(1, id)
            stm.setInt(2, skillId)

            stm.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }

        return skillId
    }

    @Override
    int insertSkillJobVacancy(int id, String skill) {
        Connection connection = null
        PreparedStatement stm = null

        int skillId = this.selectSkillForName(skill)

        if (skillId == 0){
            skillId = this.insertSkill(skill)
        }

        String insertSkillJobVacancy = "INSERT INTO job_vacancies_skill (job_vacancy_id, skill_id) VALUES (?,?);"

        try {
            connection = connectionDB.connection()
            stm = connection.prepareStatement(insertSkillJobVacancy)

            stm.setInt(1, id)
            stm.setInt(2, skillId)

            stm.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }

        return skillId
    }
}
