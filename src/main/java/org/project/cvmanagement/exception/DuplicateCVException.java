package org.project.cvmanagement.exception;

public class DuplicateCVException extends BusinessException {
    public DuplicateCVException(String id) {
        super("CV already exists with id" + id);
    }
}
