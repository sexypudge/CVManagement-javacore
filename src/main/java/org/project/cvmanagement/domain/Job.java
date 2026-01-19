package org.project.cvmanagement.domain;

import org.project.cvmanagement.enums.Level;

import java.util.Set;

public class Job {
    // TODO: fields
    // id, title, requiredLevel, requiredSkills
    private String id;
    private String title;
    private Level requiredLevel;
    private Set<String> requiredSkills;
    public Job(String id, String title, Level requiredLevel, Set<String> requiredSkills) {
        this.id = id;
        this.title = title;
        this.requiredLevel = requiredLevel;
        this.requiredSkills = requiredSkills;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Level getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(Level requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public Set<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

}
