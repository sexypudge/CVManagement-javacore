package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.CVSubmission;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.enums.CVStatus;
import org.project.cvmanagement.enums.Result;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.repository.CVRepository;
import org.project.cvmanagement.repository.JobRepository;
import org.project.cvmanagement.repository.SubmissionRepository;
import org.project.cvmanagement.service.SubmissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final JobRepository jobRepository;
    private final CVRepository cvRepository;

    public SubmissionServiceImpl(SubmissionRepository submissionRepository, JobRepository jobRepository, CVRepository cvRepository) {
        this.submissionRepository = submissionRepository;
        this.jobRepository = jobRepository;
        this.cvRepository = cvRepository;
    }

    @Override
    public void applyCV(String cvId, String jobId) {
        if (submissionRepository.existedSub(cvId, jobId)) {
            throw new BusinessException("Have applied cv!");
        }
        CV cv = cvRepository.findById(cvId).orElseThrow(() -> new BusinessException("CV not found"));
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new BusinessException("job not found"));
        if (cv.getStatus() != CVStatus.SUBMITTED) {
            throw new BusinessException("DRAFT CV can not apply Job");
        }

        double score = scoreSubmission(cv, job);

        evaluateCV(cvId, jobId, score);
    }

    private double scoreSubmission(CV cv, Job job) {
        double score = 0;
        if (cv.getLevel() == job.getRequiredLevel()) {
            score += 5;
        }
        Set<String> jobSkills = job.getRequiredSkills();
        List<String> cvSkills = cv.getSkills();
        if (jobSkills != null && !jobSkills.isEmpty()) {
            double match = jobSkills.stream().filter(jSkill -> cvSkills.stream().anyMatch(cSkill -> cSkill.equalsIgnoreCase(jSkill))).count();
            score += match / jobSkills.size() * 5;
        }
        return score;
    }

    @Override
    public void evaluateCV(String cvId, String jobId, double score) {
        CVSubmission submission = new CVSubmission(cvId, jobId, score);
        submission.setCvId(cvId);
        submission.setJobPositionId(jobId);
        submission.setScore(score);

        if (score >= 5) {
            submission.setResult(Result.PASS);
        } else {
            submission.setResult(Result.FAIL);
        }
        submissionRepository.save(submission);
    }
}
