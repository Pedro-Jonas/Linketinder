package Views

import Controllers.JobVacancyController
import DAO.ConnectionPostgresDB
import DAO.JobVacancyDAO
import Models.JobVacancy

class JobVacancyView {

    ConnectionPostgresDB connectionDB = new ConnectionPostgresDB()
    JobVacancyDAO jobVacancyDAO = new JobVacancyDAO(connectionDB)
    JobVacancyController jobVacanciesController = new JobVacancyController(jobVacancyDAO)

    SkillView skillView = new SkillView()

    Scanner sc = new Scanner(System.in)

    void showJobVacancies() {
        List<JobVacancy> vacancies = new ArrayList<>()

        try{
            vacancies = jobVacanciesController.getJobVacancies()
        } catch (Exception e) {
            e.fillInStackTrace()
        }

        if(vacancies == null) {
           println "Não foi possível mostrar vagas!"
        } else {
            printJobVacancies(vacancies)
        }
    }

    void addJobVacancy() {
        int id = 0

        JobVacancy vacancy = createNewJobVacancy()

        try {
            id = jobVacanciesController.addJobVacancy(vacancy)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (id == 0) {
            println "Falha ao inserir vaga!"
        } else {
            skillView.createSkillsJobVacancy(id)
            println "Vaga com id - ${id} inserida com sucesso!"
        }

    }

    void updateJobVacancy() {
        boolean updateLines = false
        System.out.println "Digite o id da vaga que deseja atualizar"
        int id = sc.nextInt()

        JobVacancy vacancy = createNewJobVacancy()

        try {
            updateLines = jobVacanciesController.updateJobVacancy(vacancy, id)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (updateLines) {
            println "Vaga com id - ${id} atualizada com sucesso!"
        } else {
            println "Falha ao atualizar vaga!"
        }
    }

    void deleteJobVacancy() {
        boolean hasDelete = false

        System.out.println "Digite o id da vaga que deseja deletar"
        int id = sc.nextInt()

        try {
            hasDelete = jobVacanciesController.deleteJobVacancy(id)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (hasDelete) {
            println "Vaga com id - ${id} deletado com sucesso!"
        } else {
            println "Falha ao deletar vaga!"
        }
    }

    private static void printJobVacancies(List<JobVacancy> vacancies) {
        vacancies.each {vacancy ->
            println vacancy
        }
    }

    private static JobVacancy createNewJobVacancy() {
        Scanner sc = new Scanner(System.in)

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
            jobVacancy.setName(name)
            jobVacancy.setCompanyId(companyId)
            jobVacancy.setState(state)
            jobVacancy.setCity(city)
            jobVacancy.setDescription(description)
        } catch (Exception e) {
            e.printStackTrace()
        }

        return jobVacancy
    }
}