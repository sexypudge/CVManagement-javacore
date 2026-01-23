package org.project.cvmanagement.repository.impl;

import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.Candidate;
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
    public Optional<CV> findById(String cvId) {
        return Optional.ofNullable(storage.get(cvId));
    }

    @Override
    public List<CV> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(String id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
            System.out.println("CV has been removed");
        } else {
            System.err.println("CV not found");
        }
    }

    @Override
    public List<Candidate> findByFullName(String fullName) {
        return null;
    }

    @Override
    public List<CV> findByCandidateId(String candidateId) {
        return storage.values().stream().filter(cv -> candidateId.equals(cv.getCandidateId())).collect(Collectors.toList());
    }
}
