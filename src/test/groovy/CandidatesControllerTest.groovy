import Controllers.CandidateController
import Interfaces.ICandidateDAO
import Models.Candidate

import java.text.SimpleDateFormat

import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.*

class CandidatesControllerTest {

    ICandidateDAO candidateDAO = mock(ICandidateDAO.class)
    CandidateController candidatesController = new CandidateController(candidateDAO)
    Candidate candidate = new Candidate()

    @Test
    void getCandidates() {
        //given
        List<Candidate> candidates = new ArrayList<>()

        SimpleDateFormat format = new SimpleDateFormat('dd/MM/yyyy')

        candidate.setFirstName('Pedro')
        candidate.setLastName('Araújo')
        candidate.setDateOfBirth(format.parse('11/11/1999'))
        candidate.setEmail('jonas@gmail.com')
        candidate.setCpf('111.111.111-11')
        candidate.setCountry('Brasil')
        candidate.setCep('11111-111')
        candidate.setDescription('Essa é a descrição')
        candidate.setPassword('jonas1111')

        candidates.add(candidate)

        when(candidateDAO.selectCandidates()).thenReturn(candidates)

        //when
        List<Candidate> result = candidatesController.getCandidates()

        //then
        assertEquals(candidates, result)
    }

    @Test
    void addCandidate() {
        //given
        when(candidateDAO.insertCandidate(candidate)).thenReturn(1)

        //when
        int result = candidatesController.addCandidate(candidate)

        //then
        assertEquals(1, result)
    }

    @Test
    void deleteCandidate() {
        //given
        when(candidateDAO.deleteCandidate(1)).thenReturn(true)

        //when
        boolean result = candidatesController.deleteCandidate(1)

        //then
        assertEquals(true, result)
    }
}
