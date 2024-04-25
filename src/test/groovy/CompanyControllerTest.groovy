import Controllers.CompanyController
import Interfaces.ICompanyDAO
import Models.Company

import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.*

class CompanyControllerTest {
    ICompanyDAO companyDAO = mock(ICompanyDAO.class)
    CompanyController companyController = new CompanyController(companyDAO)

    @Test
    void getCompaniesTest() {
        //given
        List<Company> companies = new ArrayList<>()
        Company company = new Company()

        company.setName("Empresa1")
        company.setEmail("empresa1@gmail.com")
        company.setCnpj("11.111.111/1111-11")
        company.setCountry("país1")
        company.setCep("11111-111")
        company.setDescription("Descrição1")
        company.setPassword("senha1")

        companies.add(company)

        when(companyDAO.selectCompanies()).thenReturn(companies)
        //when
        List<Company> result = companyController.getCompanies()

        //then
        assertEquals(companies, result)
    }

    @Test
    void addCompanyTest() {
        //given
        Company company = new Company()
        when(companyDAO.insertCompany(company)).thenReturn(1)

        //when
        int result = companyController.addCompany(company)

        //then
        assertEquals(1, result)
    }

    @Test
    void updateCompanyTest() {
        //given
        Company company = new Company()
        when(companyDAO.updateCompany(company, 1)).thenReturn(true)

        //when
        boolean result = companyController.updateCompany(company, 1)

        //then
        assertEquals(true, result)
    }


    @Test
    void deleteCompanyTest() {
        //given
        when(companyDAO.deleteCompany(1)).thenReturn(true)

        //when
        boolean result = companyController.deleteCompany(1)

        //then
        assertEquals(true, result)
    }
}
