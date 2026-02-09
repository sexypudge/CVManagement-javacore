package org.project.cvmanagement.service;

import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.enums.Level;

import java.util.List;
import java.util.Set;

public interface CVService {

    void createCV(CV cv);

    void updateSkillAndLevel(String cvId, Set<String> skills, Level level);

    List<CV> listByCandidateId(String candidateId);

    void submitCV(String cvId);

    CV getById(String cvId);
}
