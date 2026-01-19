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
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(String s) {
        storage.remove(s);

    }
}
