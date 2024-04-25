package Interfaces

interface ISkillDAO {
    int insertSkill(String skill)
    int selectSkillByName(String skill)
    int insertSkillCandidate(int id, String skill)
    int insertSkillJobVacancy(int id, String skill)
}