package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.repository.SubmissionRepository;
import org.project.cvmanagement.service.SubmissionService;

public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;
    public SubmissionServiceImpl(SubmissionRepository submissionRepository){
        this.submissionRepository=submissionRepository;
    }

    @Override
    public void applyCV(String cvId, String jobId) {
        submissionRepository.save(cvId,jobId);
        System.out.println("Successfully applied!!!");

    }

    @Override
    public void evaluateCV(String cvId, String jobId, double score) {

    }
}
