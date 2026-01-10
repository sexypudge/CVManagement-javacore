package org.project.cvmanagement.exception;


public class DuplicateCandidateException extends BusinessException {
    public DuplicateCandidateException(String id) {
        super("Candidate already exists with id: " + id);
    }
}
