package org.project.cvmanagement.exception;

public class JobHasApplicationsException extends BusinessException {
    public JobHasApplicationsException(String jobId) {
        super("Cannot delete job because it already has CV applications: " + jobId);
    }
}
