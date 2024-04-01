package Actions

import Classes.Company
import DAO.CompanieDAO

class CompaniesActions {

    CompanieDAO companieDAO = new CompanieDAO()

    Scanner sc = new Scanner(System.in)

    void showCompanies(){
        companieDAO.showCompanies()
    }

    void newCompany(){
        System.out.println "Digite o nome da empresa"
        String name = sc.nextLine()

        System.out.println "Digite o seu email corporativo"
        String email = sc.nextLine()

        System.out.println "Digite o seu CNPJ"
        String cnpj = sc.nextLine()

        System.out.println "Digite o seu país"
        String county = sc.nextLine()

        System.out.println "Digite o seu CEP"
        String cep = sc.nextLine()

        System.out.println "Digite a sua descrição"
        String description = sc.nextLine()

        System.out.println "Digite a sua senha"
        String password = sc.nextLine()

        try{
            Company company = new Company(
                    name: name,
                    email: email,
                    cnpj: cnpj,
                    country: county,
                    cep: cep,
                    description: description,
                    password: password)

            companieDAO.insertCompanie(company)
        } catch (e) {
            println e
        }
    }
}
