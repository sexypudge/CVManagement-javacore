package org.project.cvmanagement.service;

import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.enums.Level;

import java.util.List;

public interface CVService {
    void createCV(CV cv);

    void updateCV(String cvId, List<String> skills, Level level);

    void submitCV(String cvId);

    List<CV> getCVsByCandidate(String candidateId);
}