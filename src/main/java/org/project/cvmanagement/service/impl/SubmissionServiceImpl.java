package org.project.cvmanagement.service.impl;
import java.util.Set;  // Đảm bảo đã import Set
import java.util.HashSet;  // Đảm bảo đã import HashSet
import org.project.cvmanagement.enums.Level;  // Đảm bảo đã import Level

import org.project.cvmanagement.common.CommonConstant;
import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.CVSubmission;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.enums.CVStatus;
import org.project.cvmanagement.enums.Result;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.exception.CVNotFoundException;
import org.project.cvmanagement.exception.CVNotSubmittedException;
import org.project.cvmanagement.exception.DuplicateApplicationException;
import org.project.cvmanagement.exception.EvaluationFinalizedException;
import org.project.cvmanagement.exception.JobNotFoundException;
import org.project.cvmanagement.exception.SubmissionNotFoundException;
import org.project.cvmanagement.repository.CVRepository;
import org.project.cvmanagement.repository.JobRepository;
import org.project.cvmanagement.repository.SubmissionRepository;
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

        // Rule: CV chỉ apply khi status = SUBMITTED
        if (cv.getStatus() != CVStatus.SUBMITTED) {
            throw new CVNotSubmittedException(cvId);
        }

        // Rule: không apply trùng job
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
                CVStatus.SUBMITTED.name()  // Sử dụng CVStatus.SUBMITTED thay vì chuỗi "SUBMITTED"
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

        // Rule: không cho sửa khi đã APPROVED / REJECTED
        if (submission.getStatus().equals(CVStatus.APPROVED.name()) || submission.getStatus().equals(CVStatus.REJECTED.name())) {
            throw new EvaluationFinalizedException("Cannot evaluate because submission is already finalized: " + submissionId);
        }

        submission.setScore(score);
        boolean pass = score >= CommonConstant.PASS_SCORE_THRESHOLD;
        submission.setResult(pass ? Result.PASS : Result.FAIL);
        submission.setStatus(pass ? CVStatus.APPROVED.name() : CVStatus.REJECTED.name());  // Sử dụng CVStatus.APPROVED và CVStatus.REJECTED
        submissionRepository.save(submission);

        // Set CV status based on result
        cv.setStatus(pass ? CVStatus.APPROVED : CVStatus.REJECTED);  // Set CV status using CVStatus enum
        cvRepository.save(cv);
    }

    private boolean isSkillMatch(Set<String> cvSkills, Set<String> requiredSkills) {
        if (requiredSkills == null || cvSkills == null) {
            return false;  // Tránh NullPointerException
        }
        return cvSkills.containsAll(requiredSkills);  // Sử dụng containsAll để kiểm tra skill match
    }

    private boolean isLevelMatch(Level cvLevel, Level requiredLevel) {
        if (cvLevel == null || requiredLevel == null) {
            return false;  // Tránh NullPointerException
        }
        return cvLevel.ordinal() >= requiredLevel.ordinal();  // So sánh ordinal để kiểm tra level match
    }
}
