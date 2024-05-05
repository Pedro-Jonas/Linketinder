package Controllers

import DAO.CompanyDAO
import Models.Company
import Services.CompanyService

import com.google.gson.Gson
import factories.IConnectionFactory
import factories.PostgresConnectionFactory

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet("/companies")
class CompanyController extends HttpServlet {
    private IConnectionFactory connectionDB = PostgresConnectionFactory.getInstance()
    private CompanyDAO companyDAO = new CompanyDAO(connectionDB)
    private CompanyService companyService = new CompanyService(companyDAO)

    private Gson gson = new Gson()

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Company> companies = companyService.getCompanies()
            String jsonCompanies = gson.toJson(companies)

            response.setContentType("application/json")
            response.getWriter().write(jsonCompanies)
            response.setStatus(HttpServletResponse.SC_OK)
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            BufferedReader reader = request.getReader()
            StringBuilder requestBody = new StringBuilder()
            String line
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }

            Company newCompany = gson.fromJson(requestBody.toString(), Company.class)

            int id = companyService.addCompany(newCompany)

            if (id == 0) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE)
            }

            response.setStatus(HttpServletResponse.SC_CREATED)
            response.getWriter().write("Empresa com id - ${id} criada com sucesso!")
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }
}
