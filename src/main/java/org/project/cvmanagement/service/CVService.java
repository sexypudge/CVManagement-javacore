package main.java.org.project.cvmanagement.service;


import main.java.org.project.cvmanagement.domain.CV;

public interface CVService {

    void createCV(CV cv);

    void submitCV(String cvId);

    CV getById(String cvId);
}
