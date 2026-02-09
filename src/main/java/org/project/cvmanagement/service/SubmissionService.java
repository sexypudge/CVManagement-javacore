package org.project.cvmanagement.service;

import org.project.cvmanagement.domain.CVSubmission;

public interface SubmissionService {
    void applyCV(String cvId, String jobId);

    void evaluateCV(String cvId, String jobId, double score);
}
