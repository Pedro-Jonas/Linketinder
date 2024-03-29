package Actions

import Classes.Company

class CompaniesActions {

    ArrayList<Company> companies = new ArrayList<>()
    Scanner sc = new Scanner(System.in)

    void showCompanies(){
        companies.each {println it}
    }

    int countCompanies() {
        companies.size()
    }

    void insertNewCompany(Company company) {
        companies.add(company)
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

        System.out.println "Digite o seu estado"
        String state = sc.nextLine()

        System.out.println "Digite o seu CEP"
        String cep = sc.nextLine()

        System.out.println "Digite a sua descrição"
        String description = sc.nextLine()

        try{
            Company company = new Company(
                    name: name,
                    email: email,
                    cnpj: cnpj,
                    county: county,
                    state: state,
                    cep: cep,
                    description: description)
            insertNewCompany(company)
        } catch (e) {
            println e
        }
    }
}
