package Interfaces

import Models.Candidate

import java.sql.ResultSet

interface ICandidateDAO {
    int insertCandidate(Candidate candidate)
    List<Candidate> selectCandidates()
    boolean updateCandidate(Candidate candidate, int id)
    boolean deleteCandidate(int id)
    List<Candidate> listCandidates(ResultSet result)
}