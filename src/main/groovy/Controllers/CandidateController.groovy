package Controllers

import Interfaces.ICandidateDAO
import Models.Candidate

class CandidateController {
    ICandidateDAO candidateDAO

    CandidateController(ICandidateDAO candidateDAO) {
        this.candidateDAO = candidateDAO
    }

    int addCandidate(Candidate candidate){
        int id = 0

        try{
            id = candidateDAO.insertCandidate(candidate)
        } catch (Exception e) {
            e.printStackTrace()
        }

        return id
    }

    List<Candidate> getCandidatesWithSkills() {
        List<Candidate> candidates = new ArrayList<>()

        try{
            candidates = candidateDAO.selectCandidatesWithSkills()
        } catch (Exception e) {
            e.printStackTrace()
        }

        return candidates
    }

    List<Candidate> getAllCandidates() {
        List<Candidate> candidates = new ArrayList<>()

        try{
            candidates = candidateDAO.selectAllCandidates()
        } catch (Exception e) {
            e.printStackTrace()
        }

        return candidates
    }

    boolean updateCandidate(Candidate candidate, int id) {
        boolean updateLines = false

        try{
            updateLines = candidateDAO.updateCandidate(candidate, id)
        } catch (Exception e) {
            e.printStackTrace()
        }

        return updateLines
    }

    boolean deleteCandidate(int id) {
        boolean hasDelete = false

        try{
            hasDelete = candidateDAO.deleteCandidate(id);
        } catch (Exception e) {
            e.printStackTrace()
        }

        return hasDelete
    }
}