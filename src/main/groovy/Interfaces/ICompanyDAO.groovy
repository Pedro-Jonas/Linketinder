package Interfaces

import Models.Company

import java.sql.ResultSet

interface ICompanyDAO {
    List<Company> selectCompanies()
    int insertCompany(Company company)
    boolean updateCompany(Company company, int id)
    boolean deleteCompany(int id)
    List<Company> listCompanies(ResultSet result)
}
