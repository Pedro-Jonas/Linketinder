import Controllers.JobVacancyController
import DAO.JobVacancyDAO
import Views.CandidateView
import Views.CompanyView
import Views.JobVacancyView

class Menu {
    CandidateView candidateView = new CandidateView()
    CompanyView companyView = new CompanyView()

    JobVacancyView jobVacancyView = new JobVacancyView()
    JobVacancyDAO jobVacancyDAO = new JobVacancyDAO()
    JobVacancyController jobVacanciesActions = new  JobVacancyController(jobVacancyDAO)

    Scanner sc = new Scanner(System.in)

    String menu = """Digite a opção desejada: 
    1 para inserir um novo candidato ,
    2 para atualizar um candidato 
    3 para listar todos os candidatos
    4 para deletar um candidado   
    5 para inserir uma nova empresa
    6 para atualizar uma empresa 
    7 para Listar todas as empresas
    8 para deletar uma empresa
    9 inserir uma nova vaga
    10 para  atualizar uma vaga
    11 para listar todas as vagas
    12 para deletar uma vaga
    0 para encerrar
    __________________________________"""

    void Start() {
        println menu

        int op = sc.nextInt()

        while (op != 0) {
            switch (op){
                case 1:
                    candidateView.addCandidate()
                    break
                case 2:
                    candidateView.updateCandidate()
                    break
                case 3:
                    candidateView.showCandidates()
                    break
                case 4:
                    candidateView.deleteCandidate()
                    break
                case 5:
                    companyView.addCompany()
                    break
                case 6:
                    companyView.updateCompany()
                    break
                case 7:
                    companyView.showCompanies()
                    break
                case 8:
                    companyView.deleteCompany()
                    break
                case 9:
                    jobVacanciesActions.addJobVacancy()
                    break
                case 10:
                    jobVacanciesActions.updateJobVacancy()
                    break
                case 11:
                    jobVacancyView.showJobVacancies()
                    break
                case 12:
                    jobVacanciesActions.deleteJobVacancy()
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
