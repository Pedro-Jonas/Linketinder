package Interfaces

import Models.Skill

import java.sql.ResultSet

interface ISkillDAO {
    int insertSkill(String skill)
    int insertSkillCandidate(int candidateId, String skill)
    int insertSkillJobVacancy(int jobVacancyId, String skill)
    int selectSkillByName(String skill)
    List<Skill> selectSkills()
    List<Skill> listSkills(ResultSet result)
}