package org.project.cvmanagement.exception;

public class DuplicateCVException extends BusinessException {
    public DuplicateCVException(String id) {
        super("Duplicated CV: " + id);
    }
}
