package Services

import Interfaces.IJobVacancyDAO
import Models.JobVacancy

class JobVacancyService {
    IJobVacancyDAO jobVacancyDAO

    JobVacancyService(IJobVacancyDAO jobVacancyDAO) {
        this.jobVacancyDAO = jobVacancyDAO
    }

    int addJobVacancy(JobVacancy vacancy) {
        int id = 0

        try {
            id = jobVacancyDAO.insertJobVacancy(vacancy)
        } catch (Exception e) {
            e.printStackTrace()
        }

        return id
    }

    List<JobVacancy> getAllJobVacancies() {
        List<JobVacancy> vacancies = new ArrayList<>()

        try {
            vacancies = jobVacancyDAO.selectAllJobVacancies()
        } catch (Exception e) {
            e.printStackTrace()
        }

        return vacancies
    }

    List<JobVacancy> getJobVacanciesWithSkills() {
        List<JobVacancy> vacancies = new ArrayList<>()

        try {
            vacancies = jobVacancyDAO.selectJobVacanciesWithSkills()
        } catch (Exception e) {
            e.printStackTrace()
        }

        return vacancies
    }

    boolean updateJobVacancy(JobVacancy jobVacancy, int id) {
        boolean updateLines = false

        try{
            updateLines = jobVacancyDAO.updateJobVacancy(jobVacancy, id)
        } catch (Exception e) {
            println e
        }

        return updateLines
    }

    boolean deleteJobVacancy(int id){
        boolean hasDelete = false

        try{
            hasDelete = jobVacancyDAO.deleteJobVacancy(id)
        } catch (Exception e) {
            e.printStackTrace()
        }

        return hasDelete
    }
}