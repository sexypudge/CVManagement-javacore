package org.project.cvmanagement.repository.impl;

import org.project.cvmanagement.domain.CVSubmission;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.enums.Result;
import org.project.cvmanagement.repository.SubmissionRepository;

import java.util.*;

public class SubmissionRepositoryImpl implements SubmissionRepository {





    private final Map<String, List<CVSubmission>> storage = new HashMap<>();

    @Override
    public void save(CVSubmission cvSubmission) {
        storage
                .computeIfAbsent(cvSubmission.getCvId(), k -> new ArrayList<>())
                .add(cvSubmission);
    }


    @Override
    public Optional<List<CVSubmission>> findById(String cvId) {
        return Optional.ofNullable(storage.get(cvId));
    }





}
