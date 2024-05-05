import Services.CandidateService
import Interfaces.ICandidateDAO
import Models.Candidate

import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.*

class CandidateServiceTest {
    ICandidateDAO candidateDAO = mock(ICandidateDAO.class)
    CandidateService candidatesService = new CandidateService(candidateDAO)


    @Test
    void getAllCandidatesTest() {
        //given
        List<Candidate> candidates = new ArrayList<>()

        Candidate candidate = new Candidate()

        candidates.add(candidate)

        when(candidateDAO.selectAllCandidates()).thenReturn(candidates)
        //when
        List<Candidate> result = candidatesService.getAllCandidates()

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
        List<Candidate> result = candidatesService.getCandidatesWithSkills()

        //then
        assertEquals(candidates, result)
    }



    @Test
    void addCandidateTest() {
        //given
        Candidate candidate = new Candidate()
        when(candidateDAO.insertCandidate(candidate)).thenReturn(1)

        //when
        int result = candidatesService.addCandidate(candidate)

        //then
        assertEquals(1, result)
    }

    @Test
    void updateCandidateTest() {
        //given
        Candidate candidate = new Candidate()
        when(candidateDAO.updateCandidate(candidate, 1)).thenReturn(true)

        //when
        boolean result = candidatesService.updateCandidate(candidate, 1)

        //then
        assertEquals(true, result)
    }


    @Test
    void deleteCandidateTest() {
        //given
        when(candidateDAO.deleteCandidate(1)).thenReturn(true)

        //when
        boolean result = candidatesService.deleteCandidate(1)

        //then
        assertEquals(true, result)
    }
}
