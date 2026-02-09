package org.project.cvmanagement.service.impl;
import java.util.Set;
import java.util.HashSet;

import org.project.cvmanagement.common.CommonConstant;
import org.project.cvmanagement.domain.*;
import org.project.cvmanagement.enums.*;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.exception.CVNotFoundException;
import org.project.cvmanagement.exception.CVNotSubmittedException;
import org.project.cvmanagement.exception.DuplicateApplicationException;
import org.project.cvmanagement.exception.EvaluationFinalizedException;
import org.project.cvmanagement.exception.JobNotFoundException;
import org.project.cvmanagement.exception.SubmissionNotFoundException;
import org.project.cvmanagement.repository.*;
import org.project.cvmanagement.service.SubmissionService;
import org.project.cvmanagement.util.CommonUtil;

public class SubmissionServiceImpl implements SubmissionService {

    private final CVRepository cvRepository;
    private final JobRepository jobRepository;
    private final SubmissionRepository submissionRepository;

    public SubmissionServiceImpl(CVRepository cvRepository, JobRepository jobRepository, SubmissionRepository submissionRepository) {
        this.cvRepository = cvRepository;
        this.jobRepository = jobRepository;
        this.submissionRepository = submissionRepository;
    }

    @Override
    public void applyCV(String cvId, String jobId) {
        if (CommonUtil.isBlank(cvId)) {
            throw new BusinessException(CommonConstant.CV_ID_REQUIRED);
        }
        if (CommonUtil.isBlank(jobId)) {
            throw new BusinessException(CommonConstant.JOB_ID_REQUIRED);
        }

        CV cv = cvRepository.findById(cvId).orElseThrow(() -> new CVNotFoundException(cvId));
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new JobNotFoundException(jobId));


        if (cv.getStatus() != CVStatus.SUBMITTED) {
            throw new CVNotSubmittedException(cvId);
        }


        if (submissionRepository.findByCvIdAndJobId(cvId, jobId).isPresent()) {
            throw new DuplicateApplicationException(cvId, jobId);
        }

        boolean skillMatch = isSkillMatch(cv.getSkills(), job.getRequiredSkills());
        boolean levelMatch = isLevelMatch(cv.getLevel(), job.getRequiredLevel());

        String submissionId = CommonConstant.submissionId(cvId, jobId);
        CVSubmission submission = new CVSubmission(
                submissionId,
                cvId,
                jobId,
                skillMatch,
                levelMatch,
                null,
                Result.PENDING,
                CVStatus.SUBMITTED.name()
        );

        submissionRepository.save(submission);
    }

    @Override
    public void evaluateCV(String cvId, String jobId, double score) {
        if (score < 0 || score > 10) {
            throw new BusinessException(CommonConstant.SCORE_OUT_OF_RANGE);
        }
        if (CommonUtil.isBlank(cvId)) {
            throw new BusinessException(CommonConstant.CV_ID_REQUIRED);
        }
        if (CommonUtil.isBlank(jobId)) {
            throw new BusinessException(CommonConstant.JOB_ID_REQUIRED);
        }

        String submissionId = CommonConstant.submissionId(cvId, jobId);
        CVSubmission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new SubmissionNotFoundException(cvId, jobId));

        CV cv = cvRepository.findById(cvId).orElseThrow(() -> new CVNotFoundException(cvId));


        if (submission.getStatus().equals(CVStatus.APPROVED.name()) || submission.getStatus().equals(CVStatus.REJECTED.name())) {
            throw new EvaluationFinalizedException("Cannot evaluate because submission is already finalized: " + submissionId);
        }

        submission.setScore(score);
        boolean pass = score >= CommonConstant.PASS_SCORE_THRESHOLD;
        submission.setResult(pass ? Result.PASS : Result.FAIL);
        submission.setStatus(pass ? CVStatus.APPROVED.name() : CVStatus.REJECTED.name());
        submissionRepository.save(submission);


        cv.setStatus(pass ? CVStatus.APPROVED : CVStatus.REJECTED);
        cvRepository.save(cv);
    }

    private boolean isSkillMatch(Set<String> cvSkills, Set<String> requiredSkills) {
        if (requiredSkills == null || cvSkills == null) {
            return false;
        }
        return cvSkills.containsAll(requiredSkills);
    }

    private boolean isLevelMatch(Level cvLevel, Level requiredLevel) {
        if (cvLevel == null || requiredLevel == null) {
            return false;
        }
        return cvLevel.ordinal() >= requiredLevel.ordinal();
    }
}
