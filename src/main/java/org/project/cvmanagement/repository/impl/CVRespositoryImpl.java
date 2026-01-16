package org.project.cvmanagement.repository.impl;

import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.repository.CVRespository;
import org.project.cvmanagement.domain.CV;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CVRespositoryImpl implements CVRespository {
    private final Map<String, CV> storage = new HashMap<>(); // mô phỏng database bằng 1 HashMap

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
        storage.remove(s);

    }

}
