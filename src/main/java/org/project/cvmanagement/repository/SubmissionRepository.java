package org.project.cvmanagement.repository;

import org.project.cvmanagement.domain.CVSubmission;

import java.util.List;
import java.util.Optional;

public interface SubmissionRepository extends BaseRepository<CVSubmission, String> {
    Optional<CVSubmission> findByCvIdAndJobId(String cvId, String jobId);

    List<CVSubmission> findByJobId(String jobId);

    List<CVSubmission> findByCvId(String cvId);
}
