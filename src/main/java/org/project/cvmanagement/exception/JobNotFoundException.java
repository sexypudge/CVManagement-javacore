package org.project.cvmanagement.exception;

public class JobNotFoundException extends BusinessException {
    public JobNotFoundException(String id) {
        super("Job not found: " + id);
    }
}
