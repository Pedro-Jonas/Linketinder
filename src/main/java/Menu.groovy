import Actions.CandidatesActions
import Actions.CompaniesActions
import DAO.CandidateDAO

class Menu {

    CandidateDAO candidateDAO = new  CandidateDAO()
    CandidatesActions candidatesActions = new CandidatesActions()
    CompaniesActions companiesActions = new CompaniesActions()

    Scanner sc = new Scanner(System.in)

    String menu = """Digite a opção desejada: 
    1 para listar todos os candidatos
    2 para Listar todas as empresas
    3 para inserir um novo candidato
    4 para inserir uma nova empresa
    0 para encerrar
    __________________________________"""

    void Start() {
        println menu

        int op = sc.nextInt()

        while (op != 0) {
            switch (op){
                case 1:
                    candidateDAO.showCandidates()
                    break
                case 2:
                    companiesActions.showCompanies()
                    break
                case 3:
                    candidatesActions.newCandidate()
                    break
                case 4:
                    companiesActions.newCompany()
                    break
                default:
                    println "Digite uma opção válida"
                    break
            }

            println menu

            op = sc.nextInt()
        }
    }

}

