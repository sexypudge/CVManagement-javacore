package org.project.cvmanagement.exception;

public class CVNotSubmittedException extends BusinessException {
    public CVNotSubmittedException(String cvId) {
        super("CV must be SUBMITTED to apply: " + cvId);
    }
}
