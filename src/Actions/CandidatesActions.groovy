package Actions

import Classes.Candidate

class CandidatesActions {

    ArrayList<Candidate> candidates = new ArrayList<>()

    void showCandidates(){
        candidates.each {println it}
    }

    int countCandidates(){
        return candidates.size()
    }

    void newCandidade(Candidate candidate) {
        println "Não implementado"
        println candidate
    }
}