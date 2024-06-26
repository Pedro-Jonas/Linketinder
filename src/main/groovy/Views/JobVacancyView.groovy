package Views

import Services.JobVacancyService

import DAO.JobVacancyDAO
import Models.JobVacancy
import factories.IConnectionFactory
import factories.PostgresConnectionFactory

class JobVacancyView {
    IConnectionFactory connectionFactory = PostgresConnectionFactory.getInstance()
    JobVacancyDAO jobVacancyDAO = new JobVacancyDAO(connectionFactory)
    JobVacancyService jobVacanciesService = new JobVacancyService(jobVacancyDAO)


    Scanner sc = new Scanner(System.in)

    void showJobVacancies() {
        List<JobVacancy> vacancies = new ArrayList<>()

        try{
            vacancies = jobVacanciesService.getJobVacanciesWithSkills()
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
            id = jobVacanciesService.addJobVacancy(vacancy)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (id == 0) {
            println "Falha ao inserir vaga!"
        } else {
            println "Vaga com id - ${id} inserida com sucesso!"
        }

    }

    void updateJobVacancy() {
        boolean updateLines = false
        System.out.println "Digite o id da vaga que deseja atualizar"
        int id = sc.nextInt()

        JobVacancy vacancy = createNewJobVacancy()

        try {
            updateLines = jobVacanciesService.updateJobVacancy(vacancy, id)
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
            hasDelete = jobVacanciesService.deleteJobVacancy(id)
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