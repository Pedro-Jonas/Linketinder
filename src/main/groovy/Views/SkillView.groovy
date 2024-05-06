package Views

import Models.Skill
import Services.SkillService

import DAO.SkillDAO
import factories.IConnectionFactory
import factories.PostgresConnectionFactory

class SkillView {
    IConnectionFactory connectionDB = PostgresConnectionFactory.getInstance()
    SkillDAO skillDAO = new SkillDAO(connectionDB)
    SkillService skillService = new SkillService(skillDAO)

    void addSkillsCandidate() {
        Scanner sc = new Scanner(System.in)

        System.out.println "Digite a id do candidato"
        int candidateId = sc.nextInt()

        int skillId = 0

        try {
            Skill skill = createNewSkill()
            skillId = skillService.addSkillCandidate(skill, candidateId)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (skillId != 0) {
            println "Skill inserida com sucesso!"
        } else {
            println "Falha ao inserir skill!"
        }
    }

    void addSkillsJobVacancy() {
        Scanner sc = new Scanner(System.in)

        System.out.println "Digite a id da vaga"
        int jobVacancyId = sc.nextInt()

        int skillId = 0

        try {
            Skill skill = createNewSkill()
            skillId = skillService.addSkillJobVacancy(skill, jobVacancyId)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (skillId != 0) {
            println "Skill inserida com sucesso!"
        } else {
            println "Falha ao inserir skill!"
        }
    }

    private static Skill createNewSkill() {
        Scanner sc = new Scanner(System.in)

        System.out.println "Digite a sua habilidade"
        String skillName = sc.nextLine()

        Skill skill = new Skill()

        try {
            skill.setName(skillName)
        } catch (Exception e) {
            e.printStackTrace()
        }

        return  skill
    }
}
