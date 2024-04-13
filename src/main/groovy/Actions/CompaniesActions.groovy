package Actions

import Classes.Company
import DAO.CompanyDAO

class CompaniesActions {

    CompanyDAO companyDAO = new CompanyDAO()

    Scanner sc = new Scanner(System.in)

    void showCompanies(){
        try{
            companyDAO.showCompanies()
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    void addCompany(){
        Company company = creatNewCompany()

        try{
            companyDAO.insertCompany(company)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    void deleteCompany() {

        System.out.println "Digite o id do empresa que deseja deletar"
        int id = sc.nextInt()

        try{
            companyDAO.deleteCompany(id)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    void updateCompany() {

        System.out.println "Digite o id da empresa que deseja atualizar"
        int id = sc.nextInt()

        Company company = creatNewCompany()

        try{
            companyDAO.updateCompany(company, id)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    private static Company creatNewCompany() {
        Scanner sc = new Scanner(System.in)

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

        Company company = new Company()

        try{
            company.setName(name)
            company.setEmail(email)
            company.setCnpj(cnpj)
            company.setCountry(county)
            company.setCep(cep)
            company.setDescription(description)
            company.setPassword(password)
        } catch (Exception e) {
            e.printStackTrace()
        }

        return company
    }
}
