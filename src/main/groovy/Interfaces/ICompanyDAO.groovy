package Interfaces

import Models.Company

import java.sql.ResultSet

interface ICompanyDAO {
    int insertCompany(Company company)
    List<Company> selectCompanies()
    boolean updateCompany(Company company, int id)
    boolean deleteCompany(int id)
    List<Company> listCompanies(ResultSet result)
}
