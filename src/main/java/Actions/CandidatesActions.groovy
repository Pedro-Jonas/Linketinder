package Actions

import Classes.Candidate
import DAO.CandidateDAO

import java.text.SimpleDateFormat

class CandidatesActions {

    CandidateDAO candidateDAO = new  CandidateDAO()

    void showCandidates() {
        candidateDAO.showCandidates()
    }

    void newCandidate(){
        int id = 0
        Scanner sc = new Scanner(System.in)

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
                    password: password
            )

            id = candidateDAO.insertCandidate(candidate)
        } catch (Exception e) {
            println e
        }

        if (id != 0){
            this.addSkillCandidate(id)
        }
    }

    void deleteCandidate() {
        Scanner sc = new Scanner(System.in)
        System.out.println "Digite o id do candidato que deseja deletar"
        int id = sc.nextInt()
        candidateDAO.deleteCandidate(id);
    }

    void addSkillCandidate(int id){
        String text = "Digite a opção desejada: \n" +
        "1 para inserir mais uma habilidade \n" +
        "0 para parar a inserção \n"

        int op = 1;

        while (op != 0){
            Scanner sc = new Scanner(System.in)
            switch (op){
                case 1:
                    System.out.println "Digite o sua habilidade"
                    String skill = sc.nextLine()

                    candidateDAO.insertSkillCandidate(id, skill)
                    break
                default:
                    println "Digite uma opção válida"
                    break
            }
            println text
            op = sc.nextInt()
        }
    }

    void updateCandidate(){
        Scanner sc = new Scanner(System.in)

        System.out.println "Digite o id do candidato que deseja atualizar"
        String idString = sc.nextLine()

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

        int id = Integer.parseInt(idString)

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
                    password: password
            )
            candidateDAO.updateCandidate(candidate, id)
        } catch (Exception e) {
            println e
        }
    }
}