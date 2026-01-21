package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.common.CommonConstant;
import org.project.cvmanagement.enums.CVStatus;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.repository.JobRepository;
import org.project.cvmanagement.service.JobService;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.util.CommonUtil;


public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository){
        this.jobRepository=jobRepository;
    }
    @Override
    public void addJob(Job job){
        if (job == null) {
            throw new BusinessException(CommonConstant.NOT_NULL_JOB_ERROR_MESSAGE);
        }

        if (CommonUtil.isBlank(job.getId())) {
            throw new BusinessException(CommonConstant.REQUIRED_JOB_ERROR_MESSAGE);
        }


        jobRepository.save(job);
        System.out.println("Successfully created CV: "+job.getId());

    }
    @Override
    public Job getById(String jobId){
        return null;
    }


}
