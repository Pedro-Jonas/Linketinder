package Actions

import Classes.Candidate

class CandidatesActions {

    Candidate c1 = new Candidate(
            name: "Paulo",
            email: "paulo@gmail.com",
            cpf: 12312312392,age: 22,
            state: "São Paulo", cep: 31324236,
            description: "Olá, sou um profissional muito dedicado e aprendo facilmente novas tecnologias")

    Candidate c2 = new Candidate(
            name: "João",
            email: "joao@gmail.com",
            cpf: 12312312392,
            age: 30, state: "Ceará",
            cep: 54324236,
            description: "Olá, sou um profissional muito eficiente")

    Candidate c3 = new Candidate(
            name: "Diego",
            email: "diego@gmail.com",
            cpf: 12312312392,
            age: 19,
            state: "Bahia",
            cep: 76324236,
            description: "Olá, sou um profissional que é atento aos detalhes")

    Candidate c4 = new Candidate(
            name: "Maria",
            email: "maria@gmail.com",
            cpf: 12312312392,
            age: 22,
            state: "Rio de Janeiro",
            cep: 34324236,
            description: "Olá, sou uma profissional que tem espírito de liderança")

    Candidate c5 = new Candidate(
            name: "Vanessa",
            email: "vanessa@gmail.com",
            cpf: 12312312392,
            age: 28,
            state: "Rio Grande do Sul",
            cep: 29324236,
            description: "Olá, sou uma profissional que trabalha muito bem em grupo")

    ArrayList<Candidate> candidates = new ArrayList<>([c1, c2, c3, c4, c5])

    void showCandidates(){
        candidates.each {println it}
    }

}