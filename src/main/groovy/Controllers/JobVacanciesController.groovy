package Controllers

import Models.JobVacancy
import DAO.JobVacancyDAO

class JobVacanciesController {

    JobVacancyDAO jobVacancyDAO = new JobVacancyDAO()

    Scanner sc = new Scanner(System.in)

    void showJobVacancies() {

        try {
            jobVacancyDAO.showJobVacancies()
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    void addJobVacancy() {

        int id = 0

        JobVacancy jobVacancy = createNewJobVacancy()

        try {
            id = jobVacancyDAO.insertJobVacancy(jobVacancy)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (id != 0){
            this.addSkillJobVacancy(id)
        }
    }

    void deleteJobVacancy(){

        System.out.println "Digite o id da vaga que deseja deletar"
        int id = sc.nextInt()

        try{
            jobVacancyDAO.deleteJobVacancy(id)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    void updateJobVacancy() {

        System.out.println "Digite o id da vaga que deseja atualizar"
        String idString = sc.nextLine()

        int id = Integer.parseInt(idString)

        JobVacancy jobVacancy = createNewJobVacancy()

        try{
            jobVacancyDAO.updateJobVacancy(jobVacancy, id)
        } catch (Exception e) {
            println e
        }
    }

    private JobVacancy createNewJobVacancy() {

        System.out.println "Digite o id da empresa"
        String companyIdString = sc.nextLine()
        int companyId = Integer.parseInt(companyIdString)

        System.out.println "Digite o nome da vaga"
        String name = sc.nextLine()

        System.out.println "Digite o seu estado"
        String state = sc.nextLine()

        System.out.println "Digite a sua cidade"
        String city = sc.nextLine()

        System.out.println "Digite a sua descrição"
        String description = sc.nextLine()

        JobVacancy jobVacancy = new JobVacancy()

        try{
            jobVacancy.setCompanyId(companyId)
            jobVacancy.setName(name)
            jobVacancy.setState(state)
            jobVacancy.setCity(city)
            jobVacancy.setDescription(description)
        } catch (Exception e) {
            e.printStackTrace()
        }

        return jobVacancy
    }

    private void addSkillJobVacancy(int id){
        String text = "Digite a opção desejada: \n" +
                "1 para inserir mais uma habilidade \n" +
                "0 para parar a inserção \n"

        int op = 1;

        while (op != 0){
            Scanner sc = new Scanner(System.in)
            switch (op){
                case 1:
                    System.out.println "Digite a sua habilidade"
                    String skill = sc.nextLine()

                    try{
                        jobVacancyDAO.insertSkillJobVacancy(id, skill)
                    } catch (Exception e) {
                        e.printStackTrace()
                    }

                    break
                default:
                    println "Digite uma opção válida"
                    break
            }
            println text
            op = sc.nextInt()
        }
    }
}