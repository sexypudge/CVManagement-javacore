package main.java.org.project.cvmanagement.exception;

public class CandidateNotFoundException extends BusinessException {
    public CandidateNotFoundException(String id) {
        super("Candidate not found: " + id);
    }
}
