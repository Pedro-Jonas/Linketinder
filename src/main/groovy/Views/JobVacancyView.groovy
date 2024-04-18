package Views

import Controllers.JobVacancyController
import DAO.JobVacancyDAO
import Models.JobVacancy

class JobVacancyView {

    JobVacancyDAO jobVacancyDAO = new JobVacancyDAO()
    JobVacancyController jobVacanciesController = new JobVacancyController(jobVacancyDAO)

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

    private static void printJobVacancies(List<JobVacancy> vacancies) {
        vacancies.each {vacancy ->
            println vacancy
        }
    }
}