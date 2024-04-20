package DAO

import Interfaces.ICandidateDAO
import Interfaces.IConnectionDB
import Models.Candidate

import java.sql.*
import java.text.SimpleDateFormat

class CandidateDAO implements ICandidateDAO {

    IConnectionDB connectionDB

    CandidateDAO(IConnectionDB connectionDB) {
        this.connectionDB = connectionDB
    }

    @Override
    int insertCandidate(Candidate candidate) {
        Connection connection = null
        PreparedStatement stm = null
        int id = 0

        String insertCandidate = "INSERT INTO candidates (first_name, last_name, date_of_Birth," +
                " email, cpf, country, cep, description, password)  " +
                "VALUES (?,?,?,?,?,?,?,?,?);"

        Date dateOf_birth = new Date(candidate.dateOfBirth.getTime())

        try {
            connection = connectionDB.connection()
            stm = connection.prepareStatement(insertCandidate, Statement.RETURN_GENERATED_KEYS)

            stm.setString(1, candidate.firstName)
            stm.setString(2, candidate.lastName)
            stm.setDate(3, dateOf_birth)
            stm.setString(4, candidate.email)
            stm.setString(5, candidate.cpf)
            stm.setString(6, candidate.country)
            stm.setString(7, candidate.cep)
            stm.setString(8, candidate.description)
            stm.setString(9, candidate.password)

            stm.executeUpdate()
            ResultSet rs = stm.getGeneratedKeys()

            if (rs.next()) {
                id = rs.getInt(1)
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }

        return id
    }

    @Override
    List<Candidate> selectCandidates() {
        Connection connection = null
        PreparedStatement stm = null
        List<Candidate> candidates = new ArrayList<>()

        String selectCandidatesWithSkills = "SELECT cd.*, ARRAY_AGG(sk.name) skills FROM candidates AS cd INNER JOIN " +
                "candidate_skill AS ck " +
                "ON cd.id = ck.candidate_id INNER JOIN " +
                "skill AS sk " +
                "ON ck.skill_id = sk.id " +
                "GROUP BY cd.id;"

        try{
            connection = connectionDB.connection()
            stm = connection.prepareStatement(selectCandidatesWithSkills)

            ResultSet result = stm.executeQuery()

            candidates = this.listCandidates(result)
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }

        return  candidates
    }

    @Override
    boolean updateCandidate(Candidate candidate, int id) {
        Connection connection = null
        PreparedStatement stm = null
        boolean updateLines = false

        String query = "UPDATE candidates SET first_name = ?, last_name = ?, date_of_Birth = ?, " +
                "email = ?, cpf = ?, country = ?, cep = ?, description = ?, password = ? " +
                "WHERE id = ?;"

        Date dateOf_birth = new Date(candidate.dateOfBirth.getTime())

        try {
            connection = ConnectionPostgresDB.connection()
            stm = connection.prepareStatement(query)

            stm.setString(1, candidate.firstName)
            stm.setString(2, candidate.lastName)
            stm.setDate(3, dateOf_birth)
            stm.setString(4, candidate.email)
            stm.setString(5, candidate.cpf)
            stm.setString(6, candidate.country)
            stm.setString(7, candidate.cep)
            stm.setString(8, candidate.description)
            stm.setString(9, candidate.password)
            stm.setInt(9, id)

            int result = stm.executeUpdate()

            if (result != 0) {
                updateLines = true
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }

        return updateLines
    }

    @Override
    boolean deleteCandidate(int id) {
        Connection connection = null
        PreparedStatement stm = null
        boolean hasDelete = false

        String deleteCandidate = "DELETE FROM candidates WHERE id= ?;"

        try {
            connection = connectionDB.connection()
            stm = connection.prepareStatement(deleteCandidate)

            stm.setInt(1, id)

            int result = stm.executeUpdate()

            if (result != 0) {
                hasDelete = true
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            connection.close()
            stm.close()
        }

        return hasDelete
    }


    @Override
    List<Candidate> listCandidates(ResultSet result) {
        List<Candidate> candidates = new ArrayList<>()

        while (result.next()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
            Date dateOfBirth = new Date(sdf.parse(result.getString("date_of_birth")).time)

            Array array = result.getArray("skills");
            String[] skills = (String[]) array.getArray();

            Candidate candidate = new Candidate()

            candidate.setId(result.getInt("id"))
            candidate.setFirstName(result.getString("first_name"))
            candidate.setLastName(result.getString("last_name"))
            candidate.setDateOfBirth(dateOfBirth)
            candidate.setEmail(result.getString("email"))
            candidate.setCpf(result.getString("cpf"))
            candidate.setCountry(result.getString("country"))
            candidate.setCep(result.getString("cep"))
            candidate.setDescription(result.getString("description"))
            candidate.setPassword(result.getString("password"))
            candidate.setSkills(skills)

            candidates.add(candidate)
        }

        return candidates
    }
}