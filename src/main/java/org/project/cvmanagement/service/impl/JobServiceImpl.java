package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.domain.CVSubmission;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.enums.Level;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.repository.JobRepository;
import org.project.cvmanagement.repository.SubmissionRepository;
import org.project.cvmanagement.service.JobService;
import java.util.List;
import java.util.Set;

public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final SubmissionRepository submissionRepo;

    public JobServiceImpl(JobRepository jobRepository, SubmissionRepository submissionRepo) {
        this.jobRepository = jobRepository;
        this.submissionRepo = submissionRepo;
    }

    @Override
    public void addJob(Job job) {
        if (jobRepository.findById(job.getId()).isPresent()) {
            throw new BusinessException("job id " + job.getId() + " already exist");
        }

        jobRepository.save(job);
        System.out.println("job add successfully: " + job.getTitle());
    }

    @Override
    public Job getById(String jobId) {
        return null;
    }

    @Override
    public void updateJob(String jobId, String title, Level requiredLevel, Set<String> requiredSkills) {
        Job existingJob = jobRepository.findById(jobId).orElseThrow(() -> new BusinessException("Job not found with ID: " + jobId));

        existingJob.setTitle(title);
        existingJob.setRequiredLevel(requiredLevel);
        existingJob.setRequiredSkills(requiredSkills);

        jobRepository.save(existingJob);
    }

    @Override
    public void deleteJob(String jobId) {
        getById(jobId);

        List<CVSubmission> allSubmissions = submissionRepo.findAll();
        for (CVSubmission sub : allSubmissions) {
            if (sub.getJobPositionId().equals(jobId)) {
                throw new BusinessException("job cannot be deleted because there are already apply");
            }
        }
        jobRepository.deleteById(jobId);
        System.out.println("delete successful");
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}