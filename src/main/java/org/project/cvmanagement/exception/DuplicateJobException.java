package org.project.cvmanagement.exception;

public class DuplicateJobException extends BusinessException {
    public DuplicateJobException(String id) {
        super("CV already exists with id" + id);
    }
}
