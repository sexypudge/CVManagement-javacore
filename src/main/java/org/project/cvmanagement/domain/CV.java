package org.project.cvmanagement.domain;

import org.project.cvmanagement.enums.CVStatus;
import org.project.cvmanagement.enums.Level;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CV {
    private String id;
    private String candidateId;
    private Set<String> skills;
    private Level level;
    private CVStatus status;

    public CV(String id, String candidateId, Set<String> skills, Level level, CVStatus status) {
        this.id = id;
        this.candidateId = candidateId;
        this.skills = (skills == null) ? new HashSet<>() : new HashSet<>(skills);
        this.level = level;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public Set<String> getSkills() {
        return Collections.unmodifiableSet(skills);
    }

    public void setSkills(Set<String> skills) {
        this.skills = (skills == null) ? new HashSet<>() : new HashSet<>(skills);
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
