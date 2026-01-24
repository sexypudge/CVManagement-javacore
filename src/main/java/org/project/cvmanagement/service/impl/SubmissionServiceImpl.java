package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.domain.CVSubmission;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.enums.Result;
import org.project.cvmanagement.repository.SubmissionRepository;
import org.project.cvmanagement.service.SubmissionService;
import org.project.cvmanagement.domain.CV;

import java.util.Scanner;


public class SubmissionServiceImpl implements SubmissionService {
    private Scanner sc;
    private final SubmissionRepository submissionRepository;
    public SubmissionServiceImpl(SubmissionRepository submissionRepository){
        this.submissionRepository=submissionRepository;
    }

    @Override
    public void applyCV(CVSubmission cvSubmission) {
        submissionRepository.save(cvSubmission);
        System.out.println("Successfully applied!!!");





}}
