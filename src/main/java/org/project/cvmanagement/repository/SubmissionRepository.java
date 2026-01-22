package org.project.cvmanagement.repository;

import org.project.cvmanagement.domain.CVSubmission;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.enums.Result;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SubmissionRepository {
    void save(CVSubmission cvSubmission) ;
    Optional<List<CVSubmission>> findById(String cvId);



}
