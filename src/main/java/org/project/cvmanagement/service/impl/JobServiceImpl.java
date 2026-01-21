package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.common.CommonConstant;
import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.enums.CandidateStatus;
import org.project.cvmanagement.enums.Level;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.exception.DuplicateCandidateException;
import org.project.cvmanagement.exception.DuplicateJobException;
import org.project.cvmanagement.repository.JobRepository;
import org.project.cvmanagement.service.JobService;
import org.project.cvmanagement.util.CommonUtil;

import java.util.Optional;

public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    //thêm addJob
    @Override
    public void addJob(Job job) {

        if (job == null) {
            throw new BusinessException(CommonConstant.NOT_NULL_JOB_ERROR_MESSAGE);
        }

        if (CommonUtil.isBlank(job.getId())) {
            throw new BusinessException(CommonConstant.REQUIRED_JOB_ERROR_MESSAGE);
        }

        System.out.println("Adding job with jobId: " + job.getId());

        boolean existed = jobRepository.findById(job.getId()).isPresent();
        if (existed) {
            System.err.println("Duplicated job " + job.getId());
            throw new DuplicateJobException(job.getId());
        }
        if (job.getRequiredLevel() == null) {
            job.setRequiredLevel(Level.FRESHER);
        }
        if (job.getRequiredSkills() != null && !job.getRequiredSkills().isEmpty()) {
            job.setRequiredSkills(job.getRequiredSkills());
        }
        jobRepository.save(job);
        System.out.println("Successfully added job: " + job.getId());
    }

    @Override
    public void updateJob(Job job) {
        if (job == null) {
            throw new BusinessException(CommonConstant.NOT_NULL_JOB_ERROR_MESSAGE);
        }

        Optional<Job> jobOptional = jobRepository.findById(job.getId());
        if (!jobOptional.isPresent()) {
            throw new BusinessException("Job not found to update.");
        }
        Job jobExisting = jobOptional.get();
        if (!CommonUtil.isBlank(job.getTitle())) {
            jobExisting.setTitle(job.getTitle().trim());
        }
        if (job.getRequiredLevel() != null) {
            jobExisting.setRequiredLevel(job.getRequiredLevel());
        }
        if (job.getRequiredSkills() != null) {
            jobExisting.setRequiredSkills(job.getRequiredSkills());
        }
        jobRepository.save(job);
        System.out.println("Successfully updated job: " + job.getId());
    }

    @Override
    public Job getById(String jobId) {
        return null;
    }
}
