package org.project.cvmanagement.service;


import org.project.cvmanagement.domain.CV;

public interface CVService {

    void createCV(CV cv);

    void submitCV(String cvId);

    CV getById(String cvId);
    void updateCV(CV cv);
}
