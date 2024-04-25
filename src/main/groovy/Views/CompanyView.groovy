package Views

import Controllers.CompanyController
import DAO.CompanyDAO

import Models.Company
import factories.IConnectionFactory
import factories.PostgresConnectionFactory

class CompanyView {
    IConnectionFactory connectionFactory = PostgresConnectionFactory.getInstance()
    CompanyDAO companyDAO = new CompanyDAO(connectionFactory)
    CompanyController companiesController = new CompanyController(companyDAO)

    Scanner sc = new Scanner(System.in)

    void showCompanies() {
        List<Company> companies = new ArrayList<>()

        try {
            companies = companiesController.getCompanies()
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (companies == null) {
            println 'Não foi possivel mostrar empresas!'
        } else {
            printCompanies(companies)
        }
    }

    void addCompany() {
        int id = 0

        Company company = createNewCompany()

        try {
            id = companiesController.addCompany(company)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (id == 0) {
            println "Falha ao inserir empresa!"
        } else {
            println "Empresa com id - ${id} inserida com sucesso!"
        }
    }

    void updateCompany() {
        boolean updateLines = false

        System.out.println "Digite o id da empresa que deseja atualizar"
        int id = sc.nextInt()

        Company company = createNewCompany()

        try {
            updateLines = companiesController.updateCompany(company, id)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (updateLines) {
            println "Empresa com id - ${id} atualizada com sucesso!"
        } else {
            println "Falha ao atualizar empresa!"
        }
    }

    void deleteCompany() {
        boolean hasDelete = false

        System.out.println "Digite o id da empresa que deseja deletar"
        int id = sc.nextInt()

        try {
            hasDelete = companiesController.deleteCompany(id)
        } catch (Exception e) {
            e.printStackTrace()
        }

        if (hasDelete) {
            println "empresa com id - ${id} deletada com sucesso!"
        } else {
            println "Falha ao deletar empresa!"
        }
    }

    private static void printCompanies(List<Company> companies) {
        companies.each {company ->
            println company
        }
    }

    private static Company createNewCompany() {
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
