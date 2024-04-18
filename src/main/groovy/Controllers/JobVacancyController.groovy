package Controllers

import Models.JobVacancy
import DAO.JobVacancyDAO

class JobVacancyController {

    JobVacancyDAO jobVacancyDAO

    JobVacancyController(JobVacancyDAO jobVacancyDAO) {
        this.jobVacancyDAO = jobVacancyDAO
    }

    Scanner sc = new Scanner(System.in)

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