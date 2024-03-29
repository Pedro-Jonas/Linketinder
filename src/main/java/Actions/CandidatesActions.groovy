package Actions

import Classes.Candidate

class CandidatesActions {

    ArrayList<Candidate> candidates = new ArrayList<>()
    Scanner sc = new Scanner(System.in)

    void showCandidates(){
        candidates.each {println it}
    }

    int countCandidates(){
        return candidates.size()
    }

    void insertNewCandidate(Candidate candidate){
        candidates.add(candidate)
    }

    void newCandidate(){
        System.out.println "Digite o seu nome"
        String name = sc.nextLine()

        System.out.println "Digite o seu email"
        String email = sc.nextLine()

        System.out.println "Digite o seu CPF"
        String cpf = sc.nextLine()

        System.out.println "Digite o sua idade"
        int age = sc.nextInt()

        System.out.println "Digite o seu estado"
        sc.nextLine()
        String state = sc.nextLine()

        System.out.println "Digite o seu CEP"
        String cep = sc.nextLine()

        System.out.println "Digite a sua descrição"
        String description = sc.nextLine()

        try{
            Candidate candidate = new Candidate(
                    name: name,
                    email: email,
                    cpf: cpf,
                    age: age,
                    state: state,
                    cep: cep,
                    description: description)
            insertNewCandidate(candidate)
        } catch (e) {
            println e
        }
    }
}