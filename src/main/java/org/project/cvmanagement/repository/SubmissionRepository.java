package org.project.cvmanagement.repository;

import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.CVSubmission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubmissionRepository {
    private final Map<String, CVSubmission> storage = new HashMap<>();

    public void save(CVSubmission submission) {
        String key = submission.getCvId() + submission.getJobPositionId();
        storage.put(key, submission);
    }

    public boolean existedSub(String cvId, String jobId) {
        return storage.containsKey(cvId + jobId);
    }
}
