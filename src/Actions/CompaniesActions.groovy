package Actions

import Classes.Company

class CompaniesActions {

    ArrayList<Company> companies = new ArrayList<>()

    void showCompanies(){
        companies.each {println it}
    }

    int countCompanies() {
        companies.size()
    }

    void insertNewCompany(Company company) {
        companies.add(company)
    }
}
