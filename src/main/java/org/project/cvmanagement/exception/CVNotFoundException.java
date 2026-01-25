package org.project.cvmanagement.exception;

public class CVNotFoundException extends BusinessException{
    public CVNotFoundException(String id){super("CV is not existed!!!");}
}
