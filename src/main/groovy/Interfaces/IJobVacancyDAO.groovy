package Interfaces

import Models.JobVacancy

import java.sql.ResultSet

interface IJobVacancyDAO {
    int insertJobVacancy(JobVacancy jobVacancy)
    List<JobVacancy> selectAllJobVacancies()
    List<JobVacancy> selectJobVacanciesWithSkills()
    boolean updateJobVacancy(JobVacancy jobVacancy, int id)
    boolean deleteJobVacancy(int id)
    List<JobVacancy> listJobVacancies(ResultSet result)
    List<JobVacancy> listJobVacanciesWithSkills(ResultSet result)
}