package Actions

import Classes.Company
import DAO.CompanyDAO

class CompaniesActions {

    CompanyDAO companieDAO = new CompanyDAO()

    Scanner sc = new Scanner(System.in)

    void showCompanies(){
        try{
            companieDAO.showCompanies()
        } catch (Exception e) {
            e.printStackTrace()
        }
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

            companieDAO.insertCompany(company)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    void deleteCompany() {
        Scanner sc = new Scanner(System.in)
        System.out.println "Digite o id do empresa que deseja deletar"
        int id = sc.nextInt()

        try{
            companieDAO.deleteCompany(id)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    void updateCompany(){

        System.out.println "Digite o id da empresa que deseja atualizar"
        String idString = sc.nextLine()

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

        int id = Integer.parseInt(idString)

        try{
            Company company = new Company(
                    name: name,
                    email: email,
                    cnpj: cnpj,
                    country: county,
                    cep: cep,
                    description: description,
                    password: password)

            companieDAO.updateCompany(company, id)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
