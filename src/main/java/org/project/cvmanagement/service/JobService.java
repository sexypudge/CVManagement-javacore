package main.java.org.project.cvmanagement.service;

import main.java.org.project.cvmanagement.domain.Job;


public interface JobService {

    void addJob(Job job);

    Job getById(String jobId);
}

