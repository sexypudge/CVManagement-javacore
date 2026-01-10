package org.project.cvmanagement.service;


public interface SubmissionService {

    void applyCV(String cvId, String jobId);

    void evaluateCV(String cvId, String jobId, double score);
}
