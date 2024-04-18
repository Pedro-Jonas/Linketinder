import Controllers.CandidateController
import Interfaces.ICandidateDAO
import Models.Candidate

import java.text.SimpleDateFormat

import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.*

class CandidateControllerTest {

    ICandidateDAO candidateDAO = mock(ICandidateDAO.class)
    CandidateController candidatesController = new CandidateController(candidateDAO)

    @Test
    void getCandidatesTest() {
        //given
        List<Candidate> candidates = new ArrayList<>()

        Candidate candidate = new Candidate()

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy")

        candidate.setFirstName("Candidato1")
        candidate.setLastName("Candidato1")
        candidate.setDateOfBirth(format.parse("12/12/1999"))
        candidate.setEmail("candidato1@gmail.com")
        candidate.setCpf("111.111.111-11")
        candidate.setCountry("País1")
        candidate.setCep("11111-111")
        candidate.setDescription("Descrição")
        candidate.setPassword("senha1111")

        candidates.add(candidate)

        when(candidateDAO.selectCandidates()).thenReturn(candidates)
        //when
        List<Candidate> result = candidatesController.getCandidates()

        //then
        assertEquals(candidates, result)
    }

    @Test
    void addCandidateTest() {
        //given
        Candidate candidate = new Candidate()
        when(candidateDAO.insertCandidate(candidate)).thenReturn(1)

        //when
        int result = candidatesController.addCandidate(candidate)

        //then
        assertEquals(1, result)
    }

    @Test
    void updateCandidateTest() {
        //given
        Candidate candidate = new Candidate()
        when(candidateDAO.updateCandidate(candidate, 1)).thenReturn(true)

        //when
        boolean result = candidatesController.updateCandidate(candidate, 1)

        //then
        assertEquals(true, result)
    }


    @Test
    void deleteCandidateTest() {
        //given
        when(candidateDAO.deleteCandidate(1)).thenReturn(true)

        //when
        boolean result = candidatesController.deleteCandidate(1)

        //then
        assertEquals(true, result)
    }
}
