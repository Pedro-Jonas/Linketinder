package Controllers

import Interfaces.ICompanyDAO
import Models.Company

class CompanyController {

    ICompanyDAO companyDAO

    CompanyController(ICompanyDAO companyDAO) {
        this.companyDAO = companyDAO
    }

    List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>()

        try{
            companies = companyDAO.selectCompanies()
        } catch (Exception e) {
            e.printStackTrace()
        }

        return companies
    }

    int addCompany(Company company) {
        int id = 0

        try{
            id = companyDAO.insertCompany(company)
        } catch (Exception e) {
            e.printStackTrace()
        }

        return id
    }

    boolean updateCompany(Company company, int id) {
        boolean updateLines = false

        try{
            updateLines = companyDAO.updateCompany(company, id)
        } catch (Exception e) {
            e.printStackTrace()
        }

        return updateLines
    }

    boolean deleteCompany(int id) {
        boolean hasDelete = false

        try{
            hasDelete = companyDAO.deleteCompany(id)
        } catch (Exception e) {
            e.printStackTrace()
        }

        return hasDelete
    }
}