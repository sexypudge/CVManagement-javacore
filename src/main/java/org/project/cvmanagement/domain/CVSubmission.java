package org.project.cvmanagement.domain;

import org.project.cvmanagement.enums.Result;

public class CVSubmission {
    // TODO: fields
    // cvId, jobPositionId, score, result
    private String cvId;
    private String jobPostionId;
    private double score;
    private Result result;
    public CVSubmission(String cvId, String jobPostionId,double score, Result result){
        this.cvId=cvId;
        this.jobPostionId=jobPostionId;
        this.score=score;
        this.result=result;
    }
    public String getCvId(){
        return cvId;
    }
    public String getJobPostionId(){
        return jobPostionId;
    }
    public void setScore(double score){
        this.score=score;
    }
    public void setResult(Result result){
        this.result=result;
    }
    @Override
    public String toString(){
        return "CV's ID: "+cvId+", Job's ID: "+jobPostionId+", Score: "+score+", Result: "+result;
    }

}
