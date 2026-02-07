package org.project.cvmanagement.exception;

public class SubmissionNotFoundException extends BusinessException {
    public SubmissionNotFoundException(String cvId, String jobId) {
        super("Submission not found for cvId=" + cvId + ", jobId=" + jobId);
    }
}
