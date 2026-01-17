package org.project.cvmanagement.repository;


import org.project.cvmanagement.domain.Candidate;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T, ID> {
    void save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    List<Candidate> findByFullName(String fullName);
}
