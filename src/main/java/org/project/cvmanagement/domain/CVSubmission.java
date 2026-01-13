package org.project.cvmanagement.domain;

import org.project.cvmanagement.enums.Result;

public class CVSubmission {
    // TODO: fields
    // cvId, jobPositionId, score, result
    private String cvId;
    private String jobPositionId;
    private Double score;
    private Result result;

    public CVSubmission(String cvId, String jobPositionId, Double score, Result result) {
        this.cvId = cvId;
        this.jobPositionId = jobPositionId;
        this.score = score;
        this.result = result;
    }

    public String getCvId() {
        return cvId;
    }

    public String getJobPositionId() {
        return jobPositionId;
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
}
