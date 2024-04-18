package Controllers


import Models.Candidate
import DAO.CandidateDAO

class CandidateController {

    CandidateDAO candidateDAO

    CandidateController(CandidateDAO candidateDAO) {
        this.candidateDAO = candidateDAO
    }

    List<Candidate> getCandidates() {
        List<Candidate> candidates = new ArrayList<>()

        try{
            candidates = candidateDAO.selectCandidates()
        } catch (Exception e) {
            e.printStackTrace()
        }

        return candidates
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

    void addSkillCandidate(List<String> skills, int id) {
        skills.each { skill ->
            try {
                candidateDAO.insertSkillCandidate(id, skill)
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
    }
}