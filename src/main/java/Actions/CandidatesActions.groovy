package Actions

import Classes.Candidate
import DAO.CandidateDAO

import java.text.SimpleDateFormat

class CandidatesActions {

    CandidateDAO candidateDAO = new  CandidateDAO()

    Scanner sc = new Scanner(System.in)

    void showCandidates() {
        candidateDAO.showCandidates()
    }

    void newCandidate(){
        System.out.println "Digite o seu nome"
        String fristName = sc.nextLine()

        System.out.println "Digite o seu sobrenome"
        String lastName = sc.nextLine()

        System.out.println "Digite a sua data de nascimento"
        String dateOfBirthString = sc.nextLine()

        System.out.println "Digite o seu email"
        String email = sc.nextLine()

        System.out.println "Digite o seu CPF"
        String cpf = sc.nextLine()

        System.out.println "Digite o seu país"
        String country = sc.nextLine()

        System.out.println "Digite o seu CEP"
        String cep = sc.nextLine()

        System.out.println "Digite a sua descrição"
        String description = sc.nextLine()

        System.out.println "Digite a sua senha"
        String password = sc.nextLine()

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

        Date dateOfBirth = sdf.parse(dateOfBirthString)

        try{
            Candidate candidate = new Candidate(
                    fristName: fristName,
                    lastName: lastName,
                    dateOfBirth: dateOfBirth,
                    email: email,
                    cpf: cpf,
                    country: country,
                    cep: cep,
                    description: description,
                    password: password)
            candidateDAO.insertCandidate(candidate)
        } catch (e) {
            println e
        }
        
    }

    void deleteCandidate() {
        System.out.println "Digite o id do candidato que deseja deletar"
        int id = sc.nextInt()
        candidateDAO.deleteCandidate(id);
    }
}