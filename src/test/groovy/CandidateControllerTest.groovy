import Controllers.CandidateController
import Interfaces.ICandidateDAO
import Models.Candidate

import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.*

class CandidateControllerTest {
    ICandidateDAO candidateDAO = mock(ICandidateDAO.class)
    CandidateController candidatesController = new CandidateController(candidateDAO)


    @Test
    void getAllCandidates() {
        //given
        List<Candidate> candidates = new ArrayList<>()

        Candidate candidate = new Candidate()

        candidates.add(candidate)

        when(candidateDAO.selectAllCandidates()).thenReturn(candidates)
        //when
        List<Candidate> result = candidatesController.getAllCandidates()

        //then
        assertEquals(candidates, result)
    }

    @Test
    void getCandidatesWithSkillsTest() {
        //given
        List<Candidate> candidates = new ArrayList<>()

        Candidate candidate = new Candidate()

        candidates.add(candidate)

        when(candidateDAO.selectCandidatesWithSkills()).thenReturn(candidates)
        //when
        List<Candidate> result = candidatesController.getCandidatesWithSkills()

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
