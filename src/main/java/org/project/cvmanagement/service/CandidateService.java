package org.project.cvmanagement.service;


import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.enums.CandidateStatus;

import java.util.List;

public interface CandidateService {

    void addCandidate(Candidate candidate);

    void updateCandidate(Candidate candidate);

    void deactivateCandidate(String candidateId);

    Candidate getById(String candidateId);

    List<Candidate> searchByName(String keyword);
    List<Candidate> getAllCandidate();
    List<Candidate> searchByStatus();
}

