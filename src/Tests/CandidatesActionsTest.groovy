package Tests

import Actions.CandidatesActions
import Classes.Candidate
import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.*;

class CandidatesActionsTest {

    private static CandidatesActions candidatesActions

    @BeforeAll
    static void beforeAll() {
        candidatesActions = new CandidatesActions()
    }

    @AfterAll
    static void afterAll() {
        candidatesActions = null
    }

    @Test
    void addNewCandidate(){
        Candidate c1 = new Candidate(
                name: "Paulo",
                email: "paulo@gmail.com",
                cpf: "12312312392",
                age: 22,
                state: "São Paulo",
                cep: 31324236,
                description: "Olá, sou um profissional muito dedicado e aprendo facilmente novas tecnologias")

        int sizeOld = candidatesActions.countCandidates()

        candidatesActions.insertNewCandidate(c1)

        int sizeAtual = candidatesActions.countCandidates()

        assertEquals(sizeOld+1 , sizeAtual)
    }


}
