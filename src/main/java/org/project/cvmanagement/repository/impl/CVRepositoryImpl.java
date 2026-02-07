package org.project.cvmanagement.repository.impl;

import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.repository.CVRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CVRepositoryImpl implements CVRepository {

    private final Map<String, CV> storage = new HashMap<>();

    @Override
    public void save(CV entity) {
        storage.put(entity.getId(), entity);
    }

    @Override
    public Optional<CV> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<CV> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public void deleteById(String id) {
        storage.remove(id);
    }

    @Override
    public List<CV> findByCandidateId(String candidateId) {
        return storage.values().stream()
                .filter(cv -> cv != null && candidateId != null && candidateId.equals(cv.getCandidateId()))
                .collect(Collectors.toList());
    }
}
