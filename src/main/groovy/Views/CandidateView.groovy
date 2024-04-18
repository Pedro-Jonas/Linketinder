package Views

import Controllers.CandidatesController
import DAO.CandidateDAO
import Models.Candidate

import java.text.SimpleDateFormat

class CandidateView {

    CandidateDAO candidateDAO = new CandidateDAO()
    CandidatesController candidatesController = new CandidatesController(candidateDAO)
    Scanner sc = new Scanner(System.in)

    void showCandidates() {
        List<Candidate> candidates = new ArrayList<>()

        try {
            candidates = candidatesController.getCandidates()
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (candidates == null){
            println 'Não foi possivel mostrar candidatos!'
        } else {
            printCandidates(candidates)
        }
    }

    void addCandidate() {
        int id = 0

        Candidate candidate = createNewCandidate()

        try {
            id = candidatesController.addCandidate(candidate)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (id == 0) {
            println "Falha ao inserir candidato!"
        } else {
            addSkillCandidate(id)
            println "Candidado com id - ${id} inserido com sucesso!"
        }
    }

    private void addSkillCandidate(int id) {
        List<String> skills = new ArrayList<>()

        String text = "Digite a opção desejada: \n" +
                "1 para inserir mais uma habilidade \n" +
                "0 para parar a inserção \n"

        int op = 1;

        while (op != 0) {
            Scanner sc = new Scanner(System.in)

            switch (op){
                case 1:
                    System.out.println "Digite a sua habilidade"
                    String skill = sc.nextLine()

                    skills.add(skill)
                    break
                default:
                    println "Digite uma opção válida"
                    break
            }
            println text
            op = sc.nextInt()
        }

        candidatesController.addSkillCandidate(skills, id)
    }

    void updateCandidate() {
        boolean updateLines = false
        System.out.println "Digite o id do candidato que deseja atualizar"
        int id = sc.nextInt()

        Candidate candidate = createNewCandidate()

        try {
            updateLines = candidatesController.updateCandidate(candidate, id)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (updateLines) {
            println "Candidado com id - ${id} atualizado com sucesso!"
        } else {
            println "Falha ao atualizar candidato!"
        }
    }

    void deleteCandidate() {
        boolean hasDelete = false

        System.out.println "Digite o id do candidato que deseja deletar"
        int id = sc.nextInt()

        try {
            hasDelete = candidatesController.deleteCandidate(id)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (hasDelete) {
            println "Candidado deletado com sucesso!"
        } else {
            println "Falha ao deletar candidato!"
        }
    }

    private static void printCandidates(List<Candidate> candidates) {
       candidates.each {candidate ->
           println candidate
       }
    }

    private static Candidate createNewCandidate() {
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
            candidate.setFirstName(firstName)
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
