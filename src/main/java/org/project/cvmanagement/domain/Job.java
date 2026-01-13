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
}
