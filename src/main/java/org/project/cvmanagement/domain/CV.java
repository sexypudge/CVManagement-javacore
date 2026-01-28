package org.project.cvmanagement.domain;

import org.project.cvmanagement.enums.CVStatus;
import org.project.cvmanagement.enums.Level;

import java.util.List;

public class CV {
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

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
