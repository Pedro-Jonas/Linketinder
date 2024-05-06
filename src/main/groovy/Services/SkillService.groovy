package Services

import Interfaces.ISkillDAO
import Models.Skill

class SkillService {
    ISkillDAO skillDAO

    SkillService(ISkillDAO skillDAO){
        this.skillDAO = skillDAO
    }

    int addSkillCandidate(Skill skill, int candidateId) {
         int skillId = 0


        try {
            skillId = skillDAO.insertSkillCandidate(candidateId, skill.name)

        } catch (Exception e) {
            e.printStackTrace()
        }

        return skillId
    }

    int addSkillJobVacancy(Skill skill, int jobVacancyId) {
        int skillId = 0

        try {
            skillId = skillDAO.insertSkillJobVacancy(jobVacancyId, skill.name)
        } catch (Exception e) {
                e.printStackTrace()
        }

        return skillId
    }

    List<Skill> getSkills() {
        List<Skill> skills = new ArrayList<>()

        try{
            skills = skillDAO.selectSkills()
        } catch (Exception e) {
            e.printStackTrace()
        }

        return skills
    }
}
