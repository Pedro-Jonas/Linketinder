import Services.CompanyService
import Interfaces.ICompanyDAO
import Models.Company

import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.*

class CompanyServiceTest {
    ICompanyDAO companyDAO = mock(ICompanyDAO.class)
    CompanyService companyService = new CompanyService(companyDAO)

    @Test
    void addCompanyTest() {
        //given
        Company company = new Company()
        when(companyDAO.insertCompany(company)).thenReturn(1)

        //when
        int result = companyService.addCompany(company)

        //then
        assertEquals(1, result)
    }

    @Test
    void getCompaniesTest() {
        //given
        List<Company> companies = new ArrayList<>()

        Company company = new Company()

        companies.add(company)

        when(companyDAO.selectCompanies()).thenReturn(companies)
        //when
        List<Company> result = companyService.getCompanies()

        //then
        assertEquals(companies, result)
    }


    @Test
    void updateCompanyTest() {
        //given
        Company company = new Company()
        when(companyDAO.updateCompany(company, 1)).thenReturn(true)

        //when
        boolean result = companyService.updateCompany(company, 1)

        //then
        assertEquals(true, result)
    }


    @Test
    void deleteCompanyTest() {
        //given
        when(companyDAO.deleteCompany(1)).thenReturn(true)

        //when
        boolean result = companyService.deleteCompany(1)

        //then
        assertEquals(true, result)
    }
}
