package org.project.cvmanagement.domain;

import org.project.cvmanagement.enums.CVStatus;
import org.project.cvmanagement.enums.Level;

import java.util.List;

public class CV {
    // TODO: fields
    // id, candidateId, skills, level, status

    // TODO: constructor
    // TODO: getters/setters
    private  String id;
    private  String candidateId;
    private List<String> skills;
    private Level level;
    private CVStatus status;

    public CV(String id,String candidateId, List<String> skills,Level level, CVStatus status ){
        this.id = id;
        this.candidateId=candidateId;
        this.skills= skills;
        this.level = level;
        this.status=status;
    }
    public String getId(){return id;}
    public String getCandidateId(){return candidateId;}
    public void setId(String id){this.id=id;}
    public void setCandidateId(String candidateId) {this.candidateId = candidateId;}
    public List<String> getSkills(){return skills;}
    public void setSkills(List<String> skills){this.skills=skills;}
    public Level getLevel(){return level;}
    public void setLevel(Level level) {this.level = level;}
    public CVStatus getStatus() {return status;}
    public void setStatus(CVStatus status) {this.status = status;}
    @Override
    public String toString() {
        return "CV {" +
                "id='" + id + '\'' +
                ", Candidate Id='" + candidateId + '\'' +
                ", skills='" + skills + '\'' +
                ", level=" + level +
                ", status=" + (status != null ? status : "N/A") +
                '}';
    }
}
