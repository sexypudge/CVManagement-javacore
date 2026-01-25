package org.project.cvmanagement.service;

import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.Job;


public interface JobService {

    void addJob(Job job);

    void updateJob(Job job);

    void deleteJob(String jobId, String cvId);

    Job getById(String jobId);
}

