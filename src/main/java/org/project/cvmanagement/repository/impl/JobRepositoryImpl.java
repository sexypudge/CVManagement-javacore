package org.project.cvmanagement.repository.impl;

import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.repository.JobRepository;
import java.util.*;

public class JobRepositoryImpl implements JobRepository {
    private final Map<String, Job> storage = new HashMap<>();

    @Override
    public void save(Job entity) {
        storage.put(entity.getId(), entity);
    }

    @Override
    public Optional<Job> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Job> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(String id) {
        storage.remove(id);
    }
}