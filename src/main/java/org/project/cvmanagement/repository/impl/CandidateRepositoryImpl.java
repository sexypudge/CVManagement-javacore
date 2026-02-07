package org.project.cvmanagement.repository.impl;

import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.repository.CandidateRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CandidateRepositoryImpl implements CandidateRepository {

    private final Map<String, Candidate> storage = new HashMap<>();

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
        return List.copyOf(storage.values());
    }

    @Override
    public void deleteById(String id) {
        storage.remove(id);
    }
}
