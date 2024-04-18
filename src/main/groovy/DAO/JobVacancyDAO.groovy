package DAO

import Interfaces.IJobVacancyDAO
import Models.JobVacancy

import java.sql.*

class JobVacancyDAO implements IJobVacancyDAO{
    ConnectionDB connectionDAO =  new ConnectionDB()
    SkillDAO skillDAO = new SkillDAO()

    @Override
    List<JobVacancy> selectJobVacancies() {
        Connection connection = null
        PreparedStatement stm = null
        List<JobVacancy> vacancies = new ArrayList<>()

        String selectJobVacanciesWithSkills = "SELECT jv.*, ARRAY_AGG(sk.name) skills FROM job_vacancies AS jv INNER JOIN " +
                "job_vacancies_skill AS jsk " +
                "ON jv.id = jsk.job_vacancy_id INNER JOIN " +
                "skill AS sk " +
                "ON jsk.skill_id = sk.id " +
                "GROUP BY jv.id;"

        try{
            connection = connectionDAO.connection()
            stm = connection.prepareStatement(selectJobVacanciesWithSkills)

            ResultSet result = stm.executeQuery()

            vacancies = listJobVacancies(result)
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }

        return vacancies
    }

    @Override
    int insertJobVacancy(JobVacancy jobVacancy) {
        Connection connection = null
        PreparedStatement stm = null
        int id = 0

        String insertJobVacancy = "INSERT INTO job_vacancies (name, state, city, description, company_id)  " +
                "VALUES (?,?,?,?,?);"

        try {
            connection = ConnectionDB.connection()
            stm = connection.prepareStatement(insertJobVacancy, Statement.RETURN_GENERATED_KEYS)

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
    int insertSkillJobVacancy(int id, String skill){
        Connection connection = null
        PreparedStatement stm = null

        int skillId = skillDAO.selectSkillForName(skill)

        if (skillId == 0){
            skillId = skillDAO.insertSkill(skill)
        }

        String insertSkillJobVacancy = "INSERT INTO job_vacancies_skill (job_vacancy_id, skill_id) VALUES (?,?);"

        try {
            connection = ConnectionDB.connection()
            stm = connection.prepareStatement(insertSkillJobVacancy)

            stm.setInt(1, id)
            stm.setInt(2, skillId)

            stm.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }

        return skillId
    }

    @Override
    boolean updateJobVacancy(JobVacancy jobVacancy, int id) {
        Connection connection = null
        PreparedStatement stm = null
        boolean updateLines = false

        String updateJobVacancy = "UPDATE job_vacancies SET name = ?, state = ?, city = ?, " +
                "description = ?, company_id = ? " +
                "WHERE id = ?;"

        try {
            connection = ConnectionDB.connection()
            stm = connection.prepareStatement(updateJobVacancy)

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
            connection.close()
            stm.close()
        }

        return updateLines
    }

    @Override
    boolean deleteJobVacancy(int id) {
        Connection connection = null
        PreparedStatement stm = null
        boolean hasDelete = false

        String deleteJobVacancy = "DELETE FROM job_vacancies WHERE id = ?;"

        try {
            connection = ConnectionDB.connection()
            stm = connection.prepareStatement(deleteJobVacancy)

            stm.setInt(1, id)

            int result = stm.executeUpdate()

            if (result != 0) {
                hasDelete = true
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
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
