package main.java.org.project.cvmanagement.domain;

import main.java.org.project.cvmanagement.enums.CVStatus;
import main.java.org.project.cvmanagement.enums.Level;

import java.util.List;

public class CV {
    // TODO: fields
    // id, candidateId, skills, level, status

    // TODO: constructor
    // TODO: getters/setters

    private String id;
    private String candidatedId;
    private List<String> skills;
    private Level level;
    private CVStatus status;

    public CV(String id, String candidatedId, List<String> skills, Level level, CVStatus status) {
        this.id = id;
        this.candidatedId = candidatedId;
        this.skills = skills;
        this.level = level;
        this.status = status;
    }

    public String getId() {
        return this.id;
    }

    public String getCandidatedId() {
        return this.candidatedId;
    }

    public List<String> getSkills() {
        return this.skills;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setStatus(CVStatus status) {
        this.status = status;
    }

}
