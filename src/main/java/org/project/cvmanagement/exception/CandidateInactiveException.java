package org.project.cvmanagement.exception;

public class CandidateInactiveException extends BusinessException {
    public CandidateInactiveException(String candidateId) {
        super("Candidate is INACTIVE: " + candidateId);
    }
}
