import Controllers.JobVacancyController
import Interfaces.IJobVacancyDAO
import Models.JobVacancy

import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.*

class JobVacancyControllerTest {
    IJobVacancyDAO jobVacancyDAO = mock(IJobVacancyDAO.class)
    JobVacancyController jobVacancyController = new JobVacancyController(jobVacancyDAO)

    @Test
    void getCompaniesTest() {
        //given
        List<JobVacancy> jobVacancies = new ArrayList<>()
        JobVacancy jobVacancy = new JobVacancy()

        jobVacancy.setCompanyId(1)
        jobVacancy.setName("Empresa1")
        jobVacancy.setDescription("Descrição")
        jobVacancy.setState("Estado1")
        jobVacancy.setCity("Cidade1")

        jobVacancies.add(jobVacancy)

        when(jobVacancyDAO.selectJobVacancies()).thenReturn(jobVacancies)
        //when
        List<JobVacancy> result = jobVacancyController.getJobVacancies()

        //then
        assertEquals(jobVacancies, result)
    }

    @Test
    void addCompanyTest() {
        //given
        JobVacancy jobVacancy = new JobVacancy()
        when(jobVacancyDAO.insertJobVacancy(jobVacancy)).thenReturn(1)

        //when
        int result = jobVacancyController.addJobVacancy(jobVacancy)

        //then
        assertEquals(1, result)
    }

    @Test
    void updateCompanyTest() {
        //given
        JobVacancy jobVacancy = new JobVacancy()
        when(jobVacancyDAO.updateJobVacancy(jobVacancy, 1)).thenReturn(true)

        //when
        boolean result = jobVacancyController.updateJobVacancy(jobVacancy, 1)

        //then
        assertEquals(true, result)
    }


    @Test
    void deleteCompanyTest() {
        //given
        when(jobVacancyDAO.deleteJobVacancy(1)).thenReturn(true)

        //when
        boolean result = jobVacancyController.deleteJobVacancy(1)

        //then
        assertEquals(true, result)
    }
}
