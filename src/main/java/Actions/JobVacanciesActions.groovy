package Actions

import Classes.JobVacancy
import DAO.JobVacancyDAO


class JobVacanciesActions {

    JobVacancyDAO jobVacancyDAO = new JobVacancyDAO()

    Scanner sc = new Scanner(System.in)

    void showJobVacancies(){
        jobVacancyDAO.showJobVacancies()
    }
}