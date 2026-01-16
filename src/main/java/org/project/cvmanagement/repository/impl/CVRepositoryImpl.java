package org.project.cvmanagement.repository.impl;

import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.repository.CVRepository;

import java.util.*;
import java.util.stream.Collectors;

public class CVRepositoryImpl implements CVRepository {
    private final Map<String, CV> storage = new HashMap<>();

    @Override
    public void save(CV cv) {
        storage.put(cv.getId(), cv);
    }

    @Override
    public Optional<CV> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<CV> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<CV> findByCandidateId(String candidateId) {
        return storage.values().stream()
                .filter(cv -> cv.getCandidateId().equals(candidateId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        storage.remove(id);
    }
}
