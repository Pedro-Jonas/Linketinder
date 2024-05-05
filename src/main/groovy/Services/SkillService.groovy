package Services

import Interfaces.ISkillDAO

class SkillService {
    ISkillDAO skillDAO

    SkillService(ISkillDAO skillDAO){
        this.skillDAO = skillDAO
    }

    List<Integer> addSkillCandidate(List<String> skills, int id) {
         List<Integer> ids = new ArrayList<>()

        skills.each { skill ->
            try {
                int skillId = skillDAO.insertSkillCandidate(id, skill)
                ids.add(skillId)

            } catch (Exception e) {
                e.printStackTrace()
            }
        }

        return ids
    }

    List<Integer> addSkillJobVacancy(List<String> skills, int id) {
        List<Integer> ids = new ArrayList<>()

        skills.each { skill ->
            try {
                int skillId = skillDAO.insertSkillJobVacancy(id, skill)
                ids.add(skillId)
            } catch (Exception e) {
                e.printStackTrace()
            }
        }

        return ids
    }
}
