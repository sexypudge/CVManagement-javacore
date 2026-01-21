package org.project.cvmanagement.exception;

public class CandidateInvalidException extends BusinessException{
    public CandidateInvalidException(String id){super("Your candidate is invalid to create CV");}
}
