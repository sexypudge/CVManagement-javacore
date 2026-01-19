package org.project.cvmanagement.exception;

public class JobNotFoundException extends BusinessException{
    public JobNotFoundException(String id) {super("The job id "+id+" is not found!");}
}
