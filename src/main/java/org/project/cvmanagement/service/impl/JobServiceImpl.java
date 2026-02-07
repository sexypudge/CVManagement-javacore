package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.common.CommonConstant;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.enums.Level;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.exception.DuplicateJobException;
import org.project.cvmanagement.exception.JobHasApplicationsException;
import org.project.cvmanagement.exception.JobNotFoundException;
import org.project.cvmanagement.repository.JobRepository;
import org.project.cvmanagement.repository.SubmissionRepository;
import org.project.cvmanagement.service.JobService;
import org.project.cvmanagement.util.CommonUtil;

import java.util.List;
import java.util.Set;

public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final SubmissionRepository submissionRepository;

    public JobServiceImpl(JobRepository jobRepository, SubmissionRepository submissionRepository) {
        this.jobRepository = jobRepository;
        this.submissionRepository = submissionRepository;
    }

    @Override
    public void addJob(Job job) {
        validateJobForCreate(job);
        if (jobRepository.findById(job.getId()).isPresent()) {
            throw new DuplicateJobException(job.getId());
        }
        jobRepository.save(job);
    }

    @Override
    public void updateJobRequirements(String jobId, String title, Level requiredLevel, Set<String> requiredSkills) {
        Job existing = getById(jobId);

        if (CommonUtil.isBlank(title)) {
            throw new BusinessException(CommonConstant.JOB_TITLE_REQUIRED);
        }
        if (requiredLevel == null) {
            throw new BusinessException(CommonConstant.JOB_REQUIRED_LEVEL_REQUIRED);
        }
        if (requiredSkills == null || requiredSkills.isEmpty()) {
            throw new BusinessException(CommonConstant.JOB_REQUIRED_SKILLS_REQUIRED);
        }

        existing.setTitle(title);
        existing.setRequiredLevel(requiredLevel);
        existing.setRequiredSkills(requiredSkills);
        jobRepository.save(existing);
    }

    @Override
    public void deleteJob(String jobId) {
        Job existing = getById(jobId);
        boolean hasApplications = !submissionRepository.findByJobId(existing.getId()).isEmpty();
        if (hasApplications) {
            throw new JobHasApplicationsException(existing.getId());
        }
        jobRepository.deleteById(existing.getId());
    }

    @Override
    public Job getById(String jobId) {
        if (CommonUtil.isBlank(jobId)) {
            throw new BusinessException(CommonConstant.JOB_ID_REQUIRED);
        }
        return jobRepository.findById(jobId).orElseThrow(() -> new JobNotFoundException(jobId));
    }

    @Override
    public List<Job> listAll() {
        return jobRepository.findAll();
    }

    private void validateJobForCreate(Job job) {
        if (job == null) {
            throw new BusinessException(CommonConstant.JOB_NULL);
        }
        if (CommonUtil.isBlank(job.getId())) {
            throw new BusinessException(CommonConstant.JOB_ID_REQUIRED);
        }
        if (CommonUtil.isBlank(job.getTitle())) {
            throw new BusinessException(CommonConstant.JOB_TITLE_REQUIRED);
        }
        if (job.getRequiredLevel() == null) {
            throw new BusinessException(CommonConstant.JOB_REQUIRED_LEVEL_REQUIRED);
        }
        if (job.getRequiredSkills() == null || job.getRequiredSkills().isEmpty()) {
            throw new BusinessException(CommonConstant.JOB_REQUIRED_SKILLS_REQUIRED);
        }
    }
}
