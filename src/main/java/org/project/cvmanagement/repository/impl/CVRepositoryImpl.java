package org.project.cvmanagement.repository.impl;

import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.repository.CVRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class CVRepositoryImpl implements CVRepository {

    private final Map<String, CV> storage = new HashMap<>();

    @Override
    public void save(CV cv) {
        storage.put(cv.getId(), cv);
    }

    @Override
    public Optional<CV> findById(String cvId) {
        return Optional.ofNullable(storage.get(cvId));
    }

    @Override
    public List<CV> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(String s) {

    }
}
