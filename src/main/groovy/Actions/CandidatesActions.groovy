package Actions

import Classes.Candidate
import DAO.CandidateDAO

import java.text.SimpleDateFormat

class CandidatesActions {

    CandidateDAO candidateDAO = new  CandidateDAO()

    void showCandidates() {
        try{
            candidateDAO.showCandidates()
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    void newCandidate(){
        int id = 0

        Candidate candidate = createCandidate()

        try{
            id = candidateDAO.insertCandidate(candidate)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (id != 0){
            this.addSkillCandidate(id)
        }
    }

    void deleteCandidate() {
        Scanner sc = new Scanner(System.in)
        System.out.println "Digite o id do candidato que deseja deletar"
        int id = sc.nextInt()

        try{
            candidateDAO.deleteCandidate(id);
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    void addSkillCandidate(int id) {
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

                    try {
                        candidateDAO.insertSkillCandidate(id, skill)
                    } catch (Exception e) {
                        e.printStackTrace()
                    }

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

    private Candidate createCandidate() {
        Scanner sc = new Scanner(System.in)

        System.out.println "Digite o seu nome"
        String firstName = sc.nextLine()

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

        Candidate candidate = new Candidate()

        try{
            candidate.setFristName(firstName)
            candidate.setLastName(lastName)
            candidate.setDateOfBirth(dateOfBirth)
            candidate.setEmail(email)
            candidate.setCpf(cpf)
            candidate.setCountry(country)
            candidate.setCep(cep)
            candidate.setDescription(description)
            candidate.setPassword(password)
        } catch (Exception e) {
            e.printStackTrace()
        }

        return candidate
    }
}