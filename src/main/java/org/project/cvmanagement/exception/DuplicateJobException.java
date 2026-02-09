package org.project.cvmanagement.exception;

public class DuplicateJobException extends BusinessException {
    public DuplicateJobException(String id) {
        super("Duplicated job: " + id);
    }
}
