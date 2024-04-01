package Actions

import Classes.JobVacancy
import DAO.JobVacancyDAO


class JobVacanciesActions {

    JobVacancyDAO jobVacancyDAO = new JobVacancyDAO()

    Scanner sc = new Scanner(System.in)

    void showJobVacancies(){
        jobVacancyDAO.showJobVacancies()
    }

    void newJobVacancy(){
        int id = 0
        System.out.println "Digite o id da empresa"
        String companyIdString = sc.nextLine()

        System.out.println "Digite o nome da vaga"
        String name = sc.nextLine()

        System.out.println "Digite o seu estado"
        String state = sc.nextLine()

        System.out.println "Digite a sua cidade"
        String city = sc.nextLine()

        System.out.println "Digite a sua descrição"
        String description = sc.nextLine()

        int companyId = Integer.parseInt(companyIdString)

        try {
            JobVacancy jobVacancy = new JobVacancy(
                    name: name,
                    state: state,
                    city: city,
                    description: description,
                    companyId: companyId
            )

            id = jobVacancyDAO.insertJobVacancy(jobVacancy)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (id != 0){
            this.addSkillJobVacancy(id)
        }
    }

    void addSkillJobVacancy(int id){
        String text = "Digite a opção desejada: \n" +
                "1 para inserir mais uma habilidade \n" +
                "0 para parar a inserção \n"

        int op = 1;

        while (op != 0){
            Scanner sc = new Scanner(System.in)
            switch (op){
                case 1:
                    System.out.println "Digite a sua habilidade"
                    String skill = sc.nextLine()

                    try{
                        jobVacancyDAO.insertSkillJobVacancy(id, skill)
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

    void deleteJobVacancy(){
        Scanner sc = new Scanner(System.in)
        System.out.println "Digite o id da vaga que deseja deletar"
        int id = sc.nextInt()
        try{
            jobVacancyDAO.deleteJobVacancy(id)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}