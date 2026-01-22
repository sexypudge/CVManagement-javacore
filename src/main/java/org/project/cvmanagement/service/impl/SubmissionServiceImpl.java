package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.domain.*;
import org.project.cvmanagement.enums.*;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.repository.*;
import org.project.cvmanagement.service.SubmissionService;

public class SubmissionServiceImpl implements SubmissionService {
    private final CVRepository cvRepo;
    private final JobRepository jobRepo;
    private final SubmissionRepository submissionRepo;

    public SubmissionServiceImpl(CVRepository cvRepo, JobRepository jobRepo, SubmissionRepository submissionRepo) {
        this.cvRepo = cvRepo;
        this.jobRepo = jobRepo;
        this.submissionRepo = submissionRepo;
    }
    @Override
    public void submitCV(String cvId) {
        CV cv = cvRepo.findById(cvId)
                .orElseThrow(() -> new BusinessException("cv does not exit"));

        if (cv.getStatus() == CVStatus.SUBMITTED) {
            throw new BusinessException("this cv was submitted previously..");
        }
        cv.setStatus(CVStatus.SUBMITTED);
        cvRepo.save(cv);

        System.out.println(" cv " + cvId + " submit sucessful .you can apply for job");
    }
    @Override
    public void applyCV(String cvId, String jobId) {
        CV cv = cvRepo.findById(cvId).orElseThrow(() -> new BusinessException("cv does not exist."));
        jobRepo.findById(jobId).orElseThrow(() -> new BusinessException("job does not exist."));

        if (cv.getStatus() != org.project.cvmanagement.enums.CVStatus.SUBMITTED) {
            throw new BusinessException("only cv in the SUBMITTED status will be considered for application");
        }

        boolean isDuplicate = submissionRepo.findAll().stream()
                .anyMatch(s -> s.getCvId().equals(cvId) && s.getJobPositionId().equals(jobId));
        if (isDuplicate) {
            throw new BusinessException("this cv has already been applied for this job");
        }

        CVSubmission submission = new CVSubmission(cvId, jobId, 0.0, org.project.cvmanagement.enums.Result.PENDING);
        submissionRepo.save(submission);
        System.out.println("cv application was successful ");
    }

    @Override
    public void evaluateCV(String cvId, String jobId, double score) {
    }
}