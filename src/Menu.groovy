import Actions.CandidatesActions
import Actions.CompaniesActions

class Menu {

    CandidatesActions candidatesActions = new CandidatesActions()
    CompaniesActions companiesActions = new CompaniesActions()

    Scanner sc = new Scanner(System.in)

    String menu = """Digite a opção desejada: 
    1 para listar todos os Candidatos
    2 para Listas todas as empresas
    0 para encerrar
    __________________________________"""

    void Start(){
        println menu

        int op = sc.nextInt()

        while (op != 0) {
            switch (op){
                case 1:
                    candidatesActions.showCandidates()
                    break
                case 2:
                    companiesActions.showCompanies()
                    break
                default:
                    println("Digite uma opção válida")
                    break
            }

            println menu

            op = sc.nextInt()
        }
    }
}
