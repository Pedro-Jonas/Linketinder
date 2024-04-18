package Controllers

import Interfaces.IJobVacancyDAO
import Models.JobVacancy

class JobVacancyController {

    IJobVacancyDAO jobVacancyDAO

    JobVacancyController(IJobVacancyDAO jobVacancyDAO) {
        this.jobVacancyDAO = jobVacancyDAO
    }

    List<JobVacancy> getJobVacancies() {
        List<JobVacancy> vacancies = new ArrayList<>()

        try {
            vacancies = jobVacancyDAO.selectJobVacancies()
        } catch (Exception e) {
            e.printStackTrace()
        }

        return vacancies
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

    boolean updateJobVacancy(JobVacancy jobVacancy, int id) {
        boolean updateLines = false

        try{
            updateLines = jobVacancyDAO.updateJobVacancy(jobVacancy, id)
        } catch (Exception e) {
            println e
        }

        return updateLines
    }

    void addSkillJobVacancy(List<String> skills, int id) {
        skills.each { skill ->
            try {
                jobVacancyDAO.insertSkillJobVacancy(id, skill)
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
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