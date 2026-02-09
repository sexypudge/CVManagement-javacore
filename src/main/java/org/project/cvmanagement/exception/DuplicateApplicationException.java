package org.project.cvmanagement.exception;

public class DuplicateApplicationException extends BusinessException {
    public DuplicateApplicationException(String cvId, String jobId) {
        super("Duplicated application: cvId=" + cvId + ", jobId=" + jobId);
    }
}
