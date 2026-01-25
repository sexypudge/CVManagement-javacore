package org.project.cvmanagement.repository.impl;

import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.repository.JobRepository;

import java.util.*;

public class JobRepositoryImpl implements JobRepository {
    private final Map<String, Job> storage = new HashMap<>();

    @Override
    public void save(Job job) {
        storage.put(job.getId(), job);
    }

    @Override
    public Optional<Job> findById(String jobId) {
        return Optional.ofNullable(storage.get(jobId));
    }

    @Override
    public List<Job> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(String jobId) {
        if (storage.containsKey(jobId)) {
            storage.remove(jobId);
            System.out.println("Job has been removed");
        } else {
            System.err.println("Job not found");
        }
    }

    @Override
    public List<Candidate> findByFullName(String fullName) {
        return null;
    }

    @Override
    public List<CV> findByCandidateId(String candidateId) {
        return null;
    }
}
