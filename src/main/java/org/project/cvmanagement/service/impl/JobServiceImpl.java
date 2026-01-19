package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.common.CommonConstant;
import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.enums.CandidateStatus;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.exception.DuplicateCandidateException;
import org.project.cvmanagement.repository.JobRepository;
import org.project.cvmanagement.service.JobService;
import org.project.cvmanagement.util.CommonUtil;

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
            throw new DuplicateCandidateException(job.getId());
        }
        job.setRequiredLevel(job.getRequiredLevel());
        job.setRequiredSkills(job.getRequiredSkills());

        jobRepository.save(job);
        System.out.println("Successfully Added job: " + job.getId());
    }

    @Override
    public Job getById(String jobId) {
        return null;
    }
}
