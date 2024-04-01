import Actions.CandidatesActions
import Actions.CompaniesActions

class Menu {

    CandidatesActions candidatesActions = new CandidatesActions()
    CompaniesActions companiesActions = new CompaniesActions()

    Scanner sc = new Scanner(System.in)

    String menu = """Digite a opção desejada: 
    1 para inserir um novo candidato 
    2 para atualizar um candidato 
    3 para listar todos os candidatos
    4 para deletar um candidado   
    5 para inserir uma nova empresa
    6 para atualizar uma empresa 
    7 para Listar todas as empresas
    8 para deletar uma empresa
    0 para encerrar
    __________________________________"""

    void Start() {
        println menu

        int op = sc.nextInt()

        while (op != 0) {
            switch (op){
                case 1:
                    candidatesActions.newCandidate()
                    break
                case 2:
                    candidatesActions.updateCandidate()
                    break
                case 3:
                    candidatesActions.showCandidates()
                    break
                case 4:
                    candidatesActions.deleteCandidate()
                    break
                case 5:
                    companiesActions.newCompany()
                    break
                case 6:
                    companiesActions.updateCompany()
                    break
                case 7:
                    companiesActions.showCompanies()
                    break
                case 8:
                    companiesActions.deleteCompany()
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

