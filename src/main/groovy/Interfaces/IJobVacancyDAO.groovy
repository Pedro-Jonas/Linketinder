package Interfaces

import Models.Company
import Models.JobVacancy

import java.sql.ResultSet

interface IJobVacancyDAO {
    int insertJobVacancy(JobVacancy jobVacancy)
    List<JobVacancy> selectJobVacancies()
    boolean updateJobVacancy(JobVacancy jobVacancy, int id)
    boolean deleteJobVacancy(int id)
    List<Company> listJobVacancies(ResultSet result)
}