package org.project.cvmanagement.repository.impl;

import org.project.cvmanagement.domain.CVSubmission;
import org.project.cvmanagement.repository.SubmissionRepository;

import java.util.HashMap;
import java.util.Map;

public class SubmissionRepositoryImpl implements SubmissionRepository {
    private final Map<String, String> storage = new HashMap<>();
    @Override
    public void save(String cvId, String jobId){
        storage.put(cvId,jobId);
    }
}
