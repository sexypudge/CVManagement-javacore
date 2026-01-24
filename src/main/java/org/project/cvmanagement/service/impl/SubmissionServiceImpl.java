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
import java.util.Optional;
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

        cv.setStatus(CVStatus.SUBMITTED);
        cvRepository.save(cv);
        matchingSubmission(cv, job);
    }

    private void matchingSubmission(CV cv, Job job) {

        if (cv.getLevel() == job.getRequiredLevel()) {
            System.out.println("Level matched!");
        }
        Set<String> jobSkills = job.getRequiredSkills();
        List<String> cvSkills = cv.getSkills();
        if (jobSkills != null && !jobSkills.isEmpty()) {
            double matchRate = 0;
            long match = jobSkills.stream().filter(jSkill -> cvSkills.stream().anyMatch(cSkill -> cSkill.equalsIgnoreCase(jSkill))).count();
            matchRate += (double) match / jobSkills.size() * 100;

            System.out.println("Skills match: " + matchRate + "%");
        }

        CVSubmission submission = new CVSubmission(cv.getId(), job.getId(),null,null);
        submission.setCvId(cv.getId());
        submission.setJobPositionId(job.getId());

        submissionRepository.save(submission);
    }

    @Override
    public void evaluateCV(String cvId, String jobId, double score) {
        CVSubmission submission = new CVSubmission(cvId, jobId, score);
        submission.setCvId(cvId);
        submission.setJobPositionId(jobId);
        submission.setScore(score);

        if (score >= 5) {
            submission.setResult(Result.PASS);
            System.out.println("Pass to apply job");
        } else {
            submission.setResult(Result.FAIL);
            System.out.println("Fail to apply job");
        }
        submissionRepository.save(submission);
    }
}
