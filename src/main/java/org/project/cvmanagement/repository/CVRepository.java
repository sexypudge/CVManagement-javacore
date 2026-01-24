package org.project.cvmanagement.repository;

import org.project.cvmanagement.domain.CV;

import java.util.List;

public interface CVRepository extends BaseRepository<CV, String> {
    List<CV> findByCandidateId(String candidateId);
}
