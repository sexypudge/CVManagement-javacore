package org.project.cvmanagement.service;


import org.project.cvmanagement.domain.Candidate;

import java.util.List;

public interface CandidateService {

    void addCandidate(Candidate candidate);

    void updateCandidate(Candidate candidate);

    void deactivateCandidate(String candidateId);

    Candidate getById(String candidateId);

    List<Candidate> searchByName(String keyword);
    void showCandidateReport(String candidateId);
}

