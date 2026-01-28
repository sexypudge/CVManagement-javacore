package org.project.cvmanagement.repository.impl;

import org.project.cvmanagement.domain.CVSubmission;
import org.project.cvmanagement.repository.SubmissionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SubmissionRepositoryImpl implements SubmissionRepository {
    private final Map<String, CVSubmission> storage = new HashMap<>();

    public boolean existedSub(String cvId, String jobId) {
        return storage.containsKey(cvId + jobId);
    }

    public List<CVSubmission> findByCvId(String cvId){
        return storage.values().stream().filter(submission -> cvId.equals(submission.getCvId())).collect(Collectors.toList());
    }
    @Override
    public void save(CVSubmission submission) {
        String key = submission.getCvId() + submission.getJobPositionId();
        storage.put(key, submission);
    }

    @Override
    public Optional<CVSubmission> findById(String s) {
        return Optional.empty();
    }

    @Override
    public List<CVSubmission> findAll() {
        return null;
    }

    @Override
    public void deleteById(String s) {

    }
}
