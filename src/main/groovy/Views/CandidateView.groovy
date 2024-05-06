package Views

import Services.CandidateService
import DAO.CandidateDAO

import Models.Candidate
import factories.IConnectionFactory
import factories.PostgresConnectionFactory

import java.text.SimpleDateFormat

class CandidateView {
    IConnectionFactory connectionDB = PostgresConnectionFactory.getInstance()
    CandidateDAO candidateDAO = new CandidateDAO(connectionDB)
    CandidateService candidatesService = new CandidateService(candidateDAO)

    Scanner sc = new Scanner(System.in)

    void showCandidates() {
        List<Candidate> candidates = new ArrayList<>()

        try {
            candidates = candidatesService.getCandidatesWithSkills()
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (candidates == null){
            println "Não foi possivel mostrar candidatos!"
        } else {
            printCandidates(candidates)
        }
    }

    void addCandidate() {
        int id = 0

        Candidate candidate = createNewCandidate()

        try {
            id = candidatesService.addCandidate(candidate)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (id == 0) {
            println "Falha ao inserir candidato!"
        } else {
            println "Candidado com id - ${id} inserido com sucesso!"
        }
    }

    void updateCandidate() {
        boolean updateLines = false
        System.out.println "Digite o id do candidato que deseja atualizar"
        int id = sc.nextInt()

        Candidate candidate = createNewCandidate()

        try {
            updateLines = candidatesService.updateCandidate(candidate, id)
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
            hasDelete = candidatesService.deleteCandidate(id)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (hasDelete) {
            println "Candidado com id - ${id} deletado com sucesso!"
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
