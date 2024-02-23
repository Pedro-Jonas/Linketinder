package Actions

import Classes.Candidate

class CandidatesActions {

    Candidate c1 = new Candidate(
            name: "Paulo",
            email: "paulo@gmail.com",
            cpf: 12312312392,age: 22,
            state: "São Paulo", cep: 31324236,
            description: "Olá, sou um profissional muito dedicado e aprendo facilmente novas tecnologias")

    ArrayList<Candidate> candidates = new ArrayList<>([c1])

    void showCandidates(){
        candidates.each {println it}
    }

}