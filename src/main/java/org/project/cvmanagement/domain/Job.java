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
    public Job(String id,String title,Level requiredLevel, Set<String> requiredSkills){
        this.id = id;
        this.title=title;
        this.requiredLevel=requiredLevel;
        this.requiredSkills=requiredSkills;
    }
    public String getId(){
        return id;
    }

    @Override
    public String toString() {
        return "Job {" +
                "id='" + id + '\'' +
                ", Title='" + title + '\'' +
                ", level required='" + requiredLevel +'\''+
                ", requiredSkills='" + requiredSkills+ '}';
    }
}
