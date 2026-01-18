package org.project.cvmanagement.service;

import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.enums.Level;

import java.util.List;
import java.util.Set;


public interface JobService {

    void addJob(Job job);

    Job getById(String jobId);
    List<Job> getAllJobs();
    void updateJob(String jobId, String title, Level requiredLevel, Set<String> requiredSkills);
    void deleteJob(String jobId);
}

