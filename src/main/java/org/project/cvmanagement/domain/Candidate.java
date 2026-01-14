package org.project.cvmanagement.domain;

import org.project.cvmanagement.enums.CandidateStatus;

public class Candidate {
    // TODO: fields
    // id, fullName, email, yearsOfExperience, status

    // TODO: constructora
    // TODO: getters/setters

    private String id;
    private String fullName;
    private String email;
    private int yearsOfExperience;
    private CandidateStatus status;

    public Candidate(String id,
                     String fullName,
                     String email,
                     int yearsOfExperience,
                     CandidateStatus status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.yearsOfExperience = yearsOfExperience;
        this.status = status;
    }

    // ===== Getters / Setters =====

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public CandidateStatus getStatus() {
        return status;
    }

    public void setStatus(CandidateStatus status) {
        this.status = status;
    }
}
