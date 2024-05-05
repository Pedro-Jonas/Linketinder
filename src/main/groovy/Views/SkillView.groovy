package Views

import Services.SkillService

import DAO.SkillDAO
import factories.IConnectionFactory
import factories.PostgresConnectionFactory

class SkillView {
    IConnectionFactory connectionDB = PostgresConnectionFactory.getInstance()
    SkillDAO skillDAO = new SkillDAO(connectionDB)
    SkillService skillService = new SkillService(skillDAO)

    void createSkillsCandidate(int id) {
        List<String> skills = new ArrayList<>()
        List<Integer> ids = new ArrayList<>()

        try {
            skills = ListSkillsJobVacancy()
            ids = skillService.addSkillCandidate(skills, id)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (skills.size() == ids.size() && ids != null) {
            println "Skills inseridas com sucesso!"
        } else {
            println "Falha ao inserir skills!"
        }
    }

    void createSkillsJobVacancy(int id) {
        List<String> skills = new ArrayList<>()
        List<Integer> ids = new ArrayList<>()

        try {
            skills = ListSkillsJobVacancy()
            ids = skillService.addSkillJobVacancy(skills, id)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (skills.size() == ids.size() && ids != null) {
            println "Skills inseridas com sucesso!"
        } else {
            println "Falha ao inserir skills!"
        }
    }

    private static List<String> ListSkillsJobVacancy() {
        List<String> skills = new ArrayList<>()

        String text = "Digite a opção desejada: \n" +
                "1 para inserir mais uma habilidade \n" +
                "0 para parar a inserção \n"

        int op = 1;


        while (op != 0) {
            Scanner sc = new Scanner(System.in)

            switch (op){
                case 1:
                    System.out.println "Digite a sua habilidade"
                    String skill = sc.nextLine()

                    skills.add(skill)
                    break
                default:
                    println "Digite uma opção válida"
                    break
            }
            println text
            op = sc.nextInt()
        }

        return  skills
    }
}
