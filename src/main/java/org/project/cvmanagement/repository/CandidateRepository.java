package org.project.cvmanagement.repository;


import org.project.cvmanagement.domain.Candidate;

import java.util.List;

public interface CandidateRepository extends BaseRepository<Candidate, String> {
    // TODO: additional query if needed

    List<Candidate> findByFullName(String fullName);

}

