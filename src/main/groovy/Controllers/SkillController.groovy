package Controllers

import DAO.SkillDAO
import Models.Skill
import Services.SkillService
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import factories.IConnectionFactory
import factories.PostgresConnectionFactory

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet("/skills/*")
class SkillController extends HttpServlet {
    private IConnectionFactory connectionDB = PostgresConnectionFactory.getInstance()
    private SkillDAO skillDAO = new SkillDAO(connectionDB)
    private SkillService skillService = new SkillService(skillDAO)

    private Gson gson = new Gson()

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Skill> skills = skillService.getSkills()
            String jsonCompanies = gson.toJson(skills)

            response.setContentType("application/json")
            response.getWriter().write(jsonCompanies)
            response.setStatus(HttpServletResponse.SC_OK)
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo()

        BufferedReader reader = request.getReader()
        StringBuilder requestBody = new StringBuilder()
        String line
        while ((line = reader.readLine()) != null) {
            requestBody.append(line)
        }

        if (pathInfo =="/candidates") {
            try {
                JsonObject jsonObject = JsonParser.parseString(requestBody.toString()).getAsJsonObject()
                int candidateId = jsonObject.get("candidateId").getAsInt()
                String name = jsonObject.get("name").getAsString()

                Skill skill = new Skill()
                skill.setName(name)

                int skillId = skillService.addSkillCandidate(skill, candidateId)

                if (skillId == 0) {
                    response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE)
                }

                response.setStatus(HttpServletResponse.SC_CREATED)
                response.getWriter().write("Skill criada com sucesso!")
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
            }
        } else if (pathInfo == "/companies") {
            try {
                JsonObject jsonObject = JsonParser.parseString(requestBody.toString()).getAsJsonObject()
                int jobVacancyId = jsonObject.get("jobVacancyId").getAsInt()
                String name = jsonObject.get("name").getAsString()

                Skill skill = new Skill()
                skill.setName(name)

                int skillId = skillService.addSkillJobVacancy(skill, jobVacancyId)

                if (skillId == 0) {
                    response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE)
                }

                response.setStatus(HttpServletResponse.SC_CREATED)
                response.getWriter().write("Skill criada com sucesso!")
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
            }
        }
    }
}
