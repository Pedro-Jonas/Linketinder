package Controllers

import DAO.JobVacancyDAO
import Models.JobVacancy
import Services.JobVacancyService
import com.google.gson.Gson
import factories.IConnectionFactory
import factories.PostgresConnectionFactory

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/jobVacancies/*")
class jobVacanciesController extends HttpServlet {
    private IConnectionFactory connectionDB = PostgresConnectionFactory.getInstance()
    private JobVacancyDAO jobVacancyDAO = new JobVacancyDAO(connectionDB)
    private JobVacancyService jobVacancyService = new JobVacancyService(jobVacancyDAO)

    private Gson gson = new Gson()

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null | "/" == pathInfo) {
            try {
                List<JobVacancy> jobVacancies = jobVacancyService.getAllJobVacancies();
                String jsonJobVacancies = gson.toJson(jobVacancies)

                response.setContentType("application/json")
                response.getWriter().write(jsonJobVacancies)
                response.setStatus(HttpServletResponse.SC_OK)
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
            }
        } else if ("/withSkills" == pathInfo) {
            try {
                List<JobVacancy> jobVacancies = jobVacancyService.getJobVacanciesWithSkills();
                String jsonJobVacancies = gson.toJson(jobVacancies)

                response.setContentType("application/json")
                response.getWriter().write(jsonJobVacancies)
                response.setStatus(HttpServletResponse.SC_OK)
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
            }
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

            JobVacancy newJobVacancy = gson.fromJson(requestBody.toString(), JobVacancy.class)

            int id = jobVacancyService.addJobVacancy(newJobVacancy)

            if (id == 0) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE)
            }

            response.setStatus(HttpServletResponse.SC_CREATED)
            response.getWriter().write("Vaga com id - ${id} criada com sucesso!")
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }
}
