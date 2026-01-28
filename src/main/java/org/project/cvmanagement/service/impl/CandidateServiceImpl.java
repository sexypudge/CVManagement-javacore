package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.common.CommonConstant;
import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.CVSubmission;
import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.enums.CandidateStatus;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.exception.DuplicateCandidateException;
import org.project.cvmanagement.repository.CVRepository;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.repository.JobRepository;
import org.project.cvmanagement.repository.SubmissionRepository;
import org.project.cvmanagement.service.CandidateService;
import org.project.cvmanagement.util.CommonUtil;

import java.util.List;
import java.util.stream.Collectors;

public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final CVRepository cvRepository;
    private final JobRepository jobRepository;
    private final SubmissionRepository submissionRepo;


    public CandidateServiceImpl(CandidateRepository candidateRepository, CVRepository cvRepository, JobRepository jobRepository, SubmissionRepository submissionRepo) {
        this.candidateRepository = candidateRepository;
        this.cvRepository = cvRepository;
        this.jobRepository = jobRepository;
        this.submissionRepo = submissionRepo;
    }

    @Override
    public void addCandidate(Candidate candidate) {
        // validate input
        if (candidate == null) {
            throw new BusinessException(CommonConstant.NOT_NULL_CANDIDATE_ERROR_MESSAGE);
        }

        if (CommonUtil.isBlank(candidate.getId())) {
            throw new BusinessException(CommonConstant.REQUIRED_CANDIDATE_ERROR_MESSAGE);
        }
        if (CommonUtil.isBlank(candidate.getFullName())) {
            throw new BusinessException(CommonConstant.REQUIRED_CANDIDATE_ERROR_MESSAGE);
        }
        if (CommonUtil.isBlank(candidate.getEmail())) {
            throw new BusinessException(CommonConstant.REQUIRED_CANDIDATE_ERROR_MESSAGE);
        }

        System.out.println("Adding candidate with candidateId: " + candidate.getId());
        // Check duplicate
        boolean existed = candidateRepository.findById(candidate.getId()).isPresent();
        if (existed) {
            System.err.println("Duplicated candidate " + candidate.getId());
            throw new DuplicateCandidateException(candidate.getId());
        }

        // Set default status
        candidate.setStatus(CandidateStatus.ACTIVE);

        // Save
        candidateRepository.save(candidate);
        System.out.println("Successfully Added candidate with candidateId: " + candidate.getId());
    }

    @Override
    public void updateCandidate(Candidate candidate) {
        if (candidate == null || CommonUtil.isBlank(candidate.getId())) {
            throw new BusinessException("Invalid candidate data");

        }

        Candidate existed = candidateRepository.findById(candidate.getId()).orElseThrow(() -> new RuntimeException("Candidate not found"));


        // kt null


        existed.setFullName(candidate.getFullName());
        existed.setEmail(candidate.getEmail());
        existed.setYearsOfExperience(candidate.getYearsOfExperience());

        candidateRepository.save(existed);

        System.out.println("update successful: " + candidate.getId());
    }

    @Override
    public void deactivateCandidate(String candidateId) {

        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not found"));

        candidate.setStatus(CandidateStatus.INACTIVE);
        candidateRepository.save(candidate);

        System.out.println("deactivated candidate with candidateId: " + candidateId);

    }

    @Override
    public Candidate getById(String candidateId) {
        return null;
    }

    @Override
    public List<Candidate> searchByName(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return candidateRepository.findAll();
        }
        return candidateRepository.findByFullName(keyword);
    }

    @Override
    public void showCandidateReport(String candidateId) {

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new BusinessException("Candidate not found: " + candidateId));

        System.out.println("candidate: " + candidate.getFullName());

        List<CV> candidateCVs = cvRepository.findAll().stream()
                .filter(cv -> cv.getCandidateId().equals(candidateId))
                .collect(Collectors.toList());

        if (candidateCVs.isEmpty()) {
            System.out.println("  (No cv found for this candidate)");
            return;
        }

        candidateCVs.forEach(cv -> {
            System.out.println("CV: " + cv.getId() + " (" + cv.getStatus() + ")");

            List<CVSubmission> submissions = submissionRepo.findAll().stream()
                    .filter(sub -> sub.getCvId().equals(cv.getId()))
                    .collect(Collectors.toList());

            if (submissions.isEmpty()) {
                System.out.println(" no jobs applied yet.");
            } else {
                submissions.forEach(sub -> {
                    String jobTitle = jobRepository.findById(sub.getJobPositionId())
                            .map(Job::getTitle)
                            .orElse("Unknown Job");

                    System.out.println("  - " + jobTitle + ": " + sub.getResult() + " (" + sub.getScore() + ")");
                });
            }
        });
    }
}
