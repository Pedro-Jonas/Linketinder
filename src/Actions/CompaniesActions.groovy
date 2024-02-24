package Actions

import Classes.Company

class CompaniesActions {

    ArrayList<Company> companies = new ArrayList<>()

    void showCompanies(){
        companies.each {println it}
    }

    int countCompanies() {
        return 0
    }


    void insertNewCompany(Company company) {
        println company
    }
}
