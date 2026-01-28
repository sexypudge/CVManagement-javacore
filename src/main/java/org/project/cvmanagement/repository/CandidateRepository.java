package org.project.cvmanagement.repository;


import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.Candidate;

import java.util.List;

public interface CandidateRepository extends BaseRepository<Candidate, String> {
    List<Candidate> findByFullName(String fullName);

}

