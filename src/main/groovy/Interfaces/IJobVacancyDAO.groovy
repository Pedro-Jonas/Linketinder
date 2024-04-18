package Interfaces

import Models.Company
import Models.JobVacancy

import java.sql.ResultSet

interface IJobVacancyDAO {
    List<JobVacancy> selectJobVacancies()
    int insertJobVacancy(JobVacancy jobVacancy)
    int insertSkillJobVacancy(int id, String skill)
    boolean updateJobVacancy(JobVacancy jobVacancy, int id)
    boolean deleteJobVacancy(int id)
    List<Company> listJobVacancies(ResultSet result)
}