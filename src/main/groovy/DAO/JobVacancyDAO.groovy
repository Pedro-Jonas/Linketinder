package DAO


import Interfaces.IJobVacancyDAO
import Models.JobVacancy
import factories.IConnectionFactory

import java.sql.*

class JobVacancyDAO implements IJobVacancyDAO{
    IConnectionFactory connectionDB

    JobVacancyDAO(IConnectionFactory connectionDB) {
        this.connectionDB = connectionDB
    }

    @Override
    int insertJobVacancy(JobVacancy jobVacancy) {
        PreparedStatement stm = null
        int id = 0

        String insertNewJobVacancy = "INSERT INTO job_vacancies (name, state, city, description, company_id)  " +
                "VALUES (?,?,?,?,?);"

        try {
            Connection connection = connectionDB.getConnection()
            stm = connection.prepareStatement(insertNewJobVacancy, Statement.RETURN_GENERATED_KEYS)

            stm.setString(1, jobVacancy.name)
            stm.setString(2, jobVacancy.state)
            stm.setString(3, jobVacancy.city)
            stm.setString(4, jobVacancy.description)
            stm.setInt(5, jobVacancy.companyId)

            stm.executeUpdate()
            ResultSet rs = stm.getGeneratedKeys()

            if (rs.next()) {
                id = rs.getInt(1)
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }

        return id
    }

    @Override
    List<JobVacancy> selectJobVacancies() {
        PreparedStatement stm = null
        List<JobVacancy> vacancies = new ArrayList<>()

        String selectJobVacanciesWithSkills = "SELECT jv.*, ARRAY_AGG(sk.name) skills FROM job_vacancies AS jv INNER JOIN " +
                "job_vacancies_skill AS jsk " +
                "ON jv.id = jsk.job_vacancy_id INNER JOIN " +
                "skill AS sk " +
                "ON jsk.skill_id = sk.id " +
                "GROUP BY jv.id;"

        try{
            Connection connection = connectionDB.getConnection()
            stm = connection.prepareStatement(selectJobVacanciesWithSkills)

            ResultSet result = stm.executeQuery()

            vacancies = listJobVacancies(result)
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            stm.close()
        }

        return vacancies
    }

    @Override
    boolean updateJobVacancy(JobVacancy jobVacancy, int id) {
        PreparedStatement stm = null
        boolean updateLines = false

        String updateJobVacancyById = "UPDATE job_vacancies SET name = ?, state = ?, city = ?, " +
                "description = ?, company_id = ? " +
                "WHERE id = ?;"

        try {
            Connection connection = connectionDB.getConnection()
            stm = connection.prepareStatement(updateJobVacancyById)

            stm.setString(1, jobVacancy.name)
            stm.setString(2, jobVacancy.state)
            stm.setString(3, jobVacancy.city)
            stm.setString(4, jobVacancy.description)
            stm.setInt(5, jobVacancy.companyId)
            stm.setInt(6, id)

            int result = stm.executeUpdate()

            if (result != 0) {
                updateLines = true
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            stm.close()
        }

        return updateLines
    }

    @Override
    boolean deleteJobVacancy(int id) {
        PreparedStatement stm = null
        boolean hasDelete = false

        String deleteJobVacancyById = "DELETE FROM job_vacancies WHERE id = ?;"

        try {
            Connection connection = connectionDB.getConnection()
            stm = connection.prepareStatement(deleteJobVacancyById)

            stm.setInt(1, id)

            int result = stm.executeUpdate()

            if (result != 0) {
                hasDelete = true
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            stm.close()
        }

        return hasDelete
    }

    @Override
    List<JobVacancy> listJobVacancies(ResultSet result) {
        List<JobVacancy> vacancies = new ArrayList<>()

        while (result.next()) {
            JobVacancy vacancy = new JobVacancy()

            Array array = result.getArray("skills");
            String[] skills = (String[]) array.getArray();

            vacancy.setId(result.getInt("id"))
            vacancy.setCompanyId(result.getInt("company_id"))
            vacancy.setName(result.getString("name"))
            vacancy.setDescription(result.getString("description"))
            vacancy.setState(result.getString("state"))
            vacancy.setCity(result.getString("city"))
            vacancy.setSkills(skills)

            vacancies.add(vacancy)
        }

        return vacancies
    }
}
