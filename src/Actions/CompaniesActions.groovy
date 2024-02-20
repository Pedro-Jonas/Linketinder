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

    Company c2 = new Company(
            name: "BioGenetic Innovations",
            email: "bioGeneticinnovations@gmail.com",
            cnpj: 12312312392,
            county: "Brasil",
            state: "Ceará",
            cep: 54324236,
            description: "Uma empresa de biotecnologia focada em pesquisa e desenvolvimento de terapias genéticas avançadas")

    Company c3 = new Company(
            name: "LunaLux Technologies",
            email: "lunaLuxtechnologies@gmail.com",
            cnpj: 12312312392,
            county: "Brasil",
            state: "Brasil",
            cep: 76324236,
            description: "Uma empresa pioneira em tecnologia de energia renovável")

    Company c4 = new Company(
            name: "AeroSky Dynamics",
            email: "aeroskygynamics@gmail.com",
            cnpj: 12312312392,
            county: "Brasil",
            state: "Rio de Janeiro",
            cep: 34324236,
            description: "Uma empresa de engenharia aeroespacial especializada no design e fabricação de drones avançados")

    Company c5 = new Company(
            name: "QuantumLeap Industries",
            email: "quantumleapindustries@gmail.com",
            cnpj: 12312312392,
            county: "Brasil",
            state: "Rio Grande do Sul",
            cep: 29324236,
            description: "Uma empresa de pesquisa em ciência da computação quântica")

    ArrayList<Company> companies = new ArrayList<>([c1, c2, c3, c4, c5])

    void showCompanies(){
        companies.each {println it}
    }

}
