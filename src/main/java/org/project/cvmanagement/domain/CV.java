package org.project.cvmanagement.domain;

import org.project.cvmanagement.enums.CVStatus;
import org.project.cvmanagement.enums.Level;

import java.util.List;

public class CV {
    // TODO: fields
    // id, candidateId, skills, level, status
    private String id;
    private String candidateId;
    private List<String> skills;
    private Level level;
    private CVStatus status;

    public CV(String id, String candidateId, List<String> skills, Level level, CVStatus status) {
        this.id = id;
        this.candidateId = candidateId;
        this.skills = skills;
        this.level = level;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public CVStatus getStatus() {
        return status;
    }

    public void setStatus(CVStatus status) {
        this.status = status;
    }
    // TODO: constructor
    // TODO: getters/setters
}
