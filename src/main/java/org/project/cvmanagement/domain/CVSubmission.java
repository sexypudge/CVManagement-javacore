package org.project.cvmanagement.domain;

import org.project.cvmanagement.enums.Result;

public class CVSubmission {
    private String id;
    private String cvId;
    private String jobId;
    private boolean skillMatch;
    private boolean levelMatch;
    private Double score;
    private Result result;
    private String status;

    public CVSubmission(String id, String cvId, String jobId, boolean skillMatch, boolean levelMatch, Double score, Result result, String status) {
        this.id = id;
        this.cvId = cvId;
        this.jobId = jobId;
        this.skillMatch = skillMatch;
        this.levelMatch = levelMatch;
        this.score = score;
        this.result = result;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getCvId() {
        return cvId;
    }

    public String getJobId() {
        return jobId;
    }

    public boolean isSkillMatch() {
        return skillMatch;
    }

    public boolean isLevelMatch() {
        return levelMatch;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
