package org.project.cvmanagement.repository.impl;

import org.project.cvmanagement.domain.CVSubmission;
import org.project.cvmanagement.repository.SubmissionRepository;
import java.util.*;

public class SubmissionRepositoryImpl implements SubmissionRepository {
    private final Map<String, CVSubmission> storage = new HashMap<>();

    @Override
    public void save(CVSubmission entity) {
        // Lưu theo cặp cvId_jobId để tránh nộp trùng
        storage.put(entity.getCvId() + "_" + entity.getJobPositionId(), entity);
    }

    @Override
    public Optional<CVSubmission> findById(String id) { return Optional.ofNullable(storage.get(id)); }

    @Override
    public List<CVSubmission> findAll() { return new ArrayList<>(storage.values()); }

    @Override
    public void deleteById(String id) { storage.remove(id); }
}