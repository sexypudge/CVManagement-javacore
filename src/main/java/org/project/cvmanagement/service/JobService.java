package org.project.cvmanagement.service;

import org.project.cvmanagement.domain.Job;


public interface JobService {

    void addJob(Job job);
    void updateJob(Job job);

    Job getById(String jobId);
}

