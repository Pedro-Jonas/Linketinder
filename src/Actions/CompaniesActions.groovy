package Actions

import Classes.Company

class CompaniesActions {

    Company c1 = new Company(
            name: "TechNova Solutions",
            email: "technovasolutions@gmail.com",
            cnpj: 12312312392,
            county: "Brasil",
            state: "São Paulo",
            cep: 31324236,
            description: "Uma empresa líder em desenvolvimento de software especializada em soluções inovadoras")


    ArrayList<Company> companies = new ArrayList<>([c1])

    void showCompanies(){
        companies.each {println it}
    }

}
