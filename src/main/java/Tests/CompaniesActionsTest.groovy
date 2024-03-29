package Tests

import Actions.CompaniesActions
import Classes.Company
import org.junit.jupiter.api.*

import static org.junit.jupiter.api.Assertions.assertEquals

class CompaniesActionsTest {
    private static CompaniesActions companiesActions

    @BeforeAll
    static void beforeAll() {
        companiesActions = new CompaniesActions()
    }

    @AfterAll
    static void afterAll() {
        companiesActions = null
    }

    @Test
    void addNewCompany(){
        Company c1 = new Company(
                name: "TechNova Solutions",
                email: "technovasolutions@gmail.com",
                cnpj: 12312312392,
                county: "Brasil",
                state: "São Paulo",
                cep: 31324236,
                description: "Uma empresa líder em desenvolvimento de software especializada em soluções inovadoras")

        int oldSize = companiesActions.countCompanies()

        companiesActions.insertNewCompany(c1)

        int currentSize = companiesActions.countCompanies()

        assertEquals(oldSize+1 , currentSize)
    }
}
