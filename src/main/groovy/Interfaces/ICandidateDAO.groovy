package Interfaces

import Models.Candidate

import java.sql.ResultSet

interface ICandidateDAO {
    List<Candidate> selectCandidates()
    int insertCandidate(Candidate candidate)
    int insertSkillCandidate(int id, String skill)
    boolean updateCandidate(Candidate candidate, int id)
    boolean deleteCandidate(int id)
    List<Candidate> listCandidates(ResultSet result)
}