package org.project.cvmanagement.repository.impl;

import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.repository.CandidateRepository;

import java.util.*;

public class CandidateRepositoryImpl implements CandidateRepository {

    private final Map<String, Candidate> storage = new HashMap<>(); // mô phỏng database bằng 1 HashMap

    @Override
    public void save(Candidate candidate) {
        storage.put(candidate.getId(), candidate);
    }

    @Override
    public Optional<Candidate> findById(String candidateId) {
        return Optional.ofNullable(storage.get(candidateId));
    }

    @Override
    public List<Candidate> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public List<Candidate> findByFullName(String fullName) {
        List<Candidate> nameList = new ArrayList<>();
        for (Candidate candidate : storage.values()) {
            if (candidate.getFullName().toLowerCase().contains(fullName.toLowerCase())) {
                nameList.add(candidate);
            }
        }
        return nameList;
    }
}
