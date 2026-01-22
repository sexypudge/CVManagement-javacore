package org.project.cvmanagement.domain;

import org.project.cvmanagement.enums.Result;

public class CVSubmission {
    public CVSubmission(String cvId, String jobPositionId, double score, Result result) {
        this.cvId = cvId;
        this.jobPositionId = jobPositionId;
        this.score = score;
        this.result = result;
    }

    // TODO: fields
    // cvId, jobPositionId, score, result
    private String cvId;
    private String jobPositionId;
    private double score;
    private Result result;

    public String getCvId() {
        return cvId;
    }

    public void setCvId(String cvId) {
        this.cvId = cvId;
    }

    public String getJobPositionId() {
        return jobPositionId;
    }

    public void setJobPositionId(String jobPositionId) {
        this.jobPositionId = jobPositionId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
