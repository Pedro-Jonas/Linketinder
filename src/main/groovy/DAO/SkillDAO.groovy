package DAO


import Interfaces.ISkillDAO
import Models.Skill
import factories.IConnectionFactory

import java.sql.*

class SkillDAO implements ISkillDAO {
    IConnectionFactory connectionDB

    SkillDAO(IConnectionFactory connectionDB){
        this.connectionDB = connectionDB
    }

    @Override
    int insertSkill(String skillName) {
        PreparedStatement stm = null
        int skillId = 0

        String insertSkill = "INSERT INTO skill (name) VALUES(?);"

        try {
            Connection connection = connectionDB.getConnection()
            stm = connection.prepareStatement(insertSkill, Statement.RETURN_GENERATED_KEYS)

            stm.setString(1, skillName)

            stm.execute()
            ResultSet rs = stm.getGeneratedKeys()

            if (rs.next()) {
                skillId = rs.getInt(1)
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            stm.close()
        }

        return skillId
    }

    @Override
    int insertSkillCandidate(int candidateId, String skillName) {
        PreparedStatement stm = null

        int  skillId = this.selectSkillByName(skillName)

        if(skillId == 0) {
            skillId = this.insertSkill(skillName)
        }

        String insertSkillCandidate = "INSERT INTO candidate_skill (candidate_id, skill_id) VALUES (?,?);"

        try {
            Connection connection = connectionDB.getConnection()
            stm = connection.prepareStatement(insertSkillCandidate)

            stm.setInt(1, candidateId)
            stm.setInt(2, skillId)

            stm.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            stm.close()
        }

        return skillId
    }

    @Override
    int insertSkillJobVacancy(int jobVacancyId, String skillName) {
        PreparedStatement stm = null

        int skillId = this.selectSkillByName(skillName)

        if (skillId == 0){
            skillId = this.insertSkill(skillName)
        }

        String insertSkillJobVacancy = "INSERT INTO job_vacancies_skill (job_vacancy_id, skill_id) VALUES (?,?);"

        try {
            Connection connection = connectionDB.getConnection()
            stm = connection.prepareStatement(insertSkillJobVacancy)

            stm.setInt(1, jobVacancyId)
            stm.setInt(2, skillId)

            stm.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            stm.close()
        }

        return skillId
    }

    @Override
    int selectSkillByName(String skill) {
        PreparedStatement stm = null
        int skillId = 0

        String selectSkillByName = "SELECT * FROM skill sk WHERE sk.name = ?;"

        try {
            Connection connection = connectionDB.getConnection()
            stm = connection.prepareStatement(selectSkillByName)

            stm.setString(1, skill)

            ResultSet result = stm.executeQuery()

            while (result.next()) {
                skillId = result.getInt("id")
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            stm.close()
        }
        return skillId
    }

    @Override
    List<Skill> selectSkills() {
        PreparedStatement stm = null
        List<Skill> skills = new ArrayList<>()

        String selectSkillByName = "SELECT * FROM skill;"

        try {
            Connection connection = connectionDB.getConnection()
            stm = connection.prepareStatement(selectSkillByName)

            ResultSet result = stm.executeQuery()

            skills = this.listSkills(result)
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            stm.close()
        }

        return  skills
    }

    @Override
    List<Skill> listSkills(ResultSet result) {
        List<Skill> skills = new ArrayList<>()
        while (result.next()) {
            Skill skill = new Skill()

            skill.setId(result.getInt("id"))
            skill.setName(result.getString("name"))

            skills.add(skill)
        }

        return skills
    }
}
