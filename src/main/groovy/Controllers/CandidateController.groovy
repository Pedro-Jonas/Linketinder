package Controllers

import Services.CandidateService
import DAO.CandidateDAO
import Models.Candidate
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import factories.IConnectionFactory
import factories.PostgresConnectionFactory

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.text.SimpleDateFormat


@WebServlet("/candidates/*")
class CandidateController extends HttpServlet {
    private IConnectionFactory connectionDB = PostgresConnectionFactory.getInstance()
    private CandidateDAO candidateDAO = new CandidateDAO(connectionDB)
    private CandidateService candidateService = new CandidateService(candidateDAO)

    private Gson gson = new Gson()
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null | "/" == pathInfo) {
            try {
                List<Candidate> candidates = candidateService.getAllCandidates();
                String jsonCandidates = gson.toJson(candidates)

                response.setContentType("application/json")
                response.getWriter().write(jsonCandidates)
                response.setStatus(HttpServletResponse.SC_OK)
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
            }
        } else if ("/withSkills" == pathInfo) {
            try {
                List<Candidate> candidates = candidateService.getCandidatesWithSkills()
                String jsonCandidates = gson.toJson(candidates)

                response.setContentType("application/json")
                response.getWriter().write(jsonCandidates)
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

            JsonObject jsonObject = JsonParser.parseString(requestBody.toString()).getAsJsonObject()

            String dateOfBirthString = jsonObject.get("dateOfBirth").getAsString()
            Date dateOfBirth = sdf.parse(dateOfBirthString)
            jsonObject.addProperty("dateOfBirth", dateOfBirth.toString())

            Candidate newCandidate = gson.fromJson(requestBody.toString(), Candidate.class)

            int id = candidateService.addCandidate(newCandidate)

            if (id == 0) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE)
            }

            response.setStatus(HttpServletResponse.SC_CREATED)
            response.getWriter().write("Candidato com id - ${id} criado com sucesso!")
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao processar a solicitação: " + e.getMessage())
        }
    }
}
