package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.common.CommonConstant;
import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.CVSubmission;
import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.enums.CandidateStatus;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.exception.CandidateNotFoundException;
import org.project.cvmanagement.exception.CandidateUpdateException;
import org.project.cvmanagement.exception.DuplicateCandidateException;
import org.project.cvmanagement.repository.CVRepository;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.repository.JobRepository;
import org.project.cvmanagement.repository.SubmissionRepository;
import org.project.cvmanagement.service.CandidateService;
import org.project.cvmanagement.util.CommonUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final CVRepository cvRepository;
    private final JobRepository jobRepository;
    private final SubmissionRepository submissionRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository, CVRepository cvRepository, JobRepository jobRepository, SubmissionRepository submissionRepository) {
        this.candidateRepository = candidateRepository;
        this.cvRepository = cvRepository;
        this.jobRepository = jobRepository;
        this.submissionRepository = submissionRepository;
    }

    @Override
    public void addCandidate(Candidate candidate) {
        // Validate input
        if (candidate == null) {
            throw new BusinessException(CommonConstant.NOT_NULL_CANDIDATE_ERROR_MESSAGE);
        }

        if (CommonUtil.isBlank(candidate.getId())) {
            throw new BusinessException(CommonConstant.REQUIRED_CANDIDATE_ERROR_MESSAGE);
        }
        // TODO: validate name, email
        if (!CommonUtil.isValidName(candidate.getFullName())) {
            throw new BusinessException("Invalid name.");
        }
        if (!CommonUtil.isValidEmail(candidate.getEmail())) {
            throw new BusinessException("Invalid email.");
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
        if (candidate == null) {
            throw new BusinessException(CommonConstant.NOT_NULL_CANDIDATE_ERROR_MESSAGE);
        }
        if (CommonUtil.isBlank(candidate.getId())) {
            throw new BusinessException(CommonConstant.REQUIRED_CANDIDATE_ERROR_MESSAGE);
        }
        if (!CommonUtil.isValidName(candidate.getFullName())) {
            throw new BusinessException("Invalid name.");
        }
        if (!CommonUtil.isValidEmail(candidate.getEmail())) {
            throw new BusinessException("Invalid email.");
        }

        Optional<Candidate> candidateOptional = candidateRepository.findById(candidate.getId());
        if (candidateOptional.isPresent()) {
            Candidate existingCandidate = candidateOptional.get();

            if (CandidateStatus.INACTIVE.equals(existingCandidate.getStatus())) {
                throw new CandidateUpdateException("Candidate is INACTIVE. Candidate can not be Updated. ");
            }
            existingCandidate.setFullName(candidate.getFullName());
            existingCandidate.setEmail(candidate.getEmail());
            candidateRepository.save(existingCandidate);
            System.out.println("Updating candidate successfully." + candidate.getId());
        } else {
            throw new CandidateNotFoundException("Candidate not found" + candidate.getId());
        }
    }

    @Override
    public void deactivateCandidate(String candidateId) {

        if (CommonUtil.isBlank(candidateId)) {
            throw new BusinessException(CommonConstant.REQUIRED_CANDIDATE_ERROR_MESSAGE);
        }
        Optional<Candidate> candidateOptional = candidateRepository.findById(candidateId);
        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            System.out.println("Deactivating id" + candidateId);
            candidate.setStatus(CandidateStatus.INACTIVE);
            candidateRepository.save(candidate);
            System.out.println("Deactivated id " + candidateId);
        } else {
            throw new CandidateNotFoundException("Candidate not found" + candidateId);
        }
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
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(() -> new BusinessException("Candidate not found"));
        System.out.println("candidate: " + candidate.getFullName());


        List<CV> cvList = cvRepository.findByCandidateId(candidateId);

        if (cvList.isEmpty()) {
            System.out.println("No CV found");
            return;
        }

        for (CV cv1 : cvList) {
            System.out.println("CV: " + cv1.getId() + " (" + cv1.getStatus() + ")");

            List<CVSubmission> submissions = submissionRepository.findByCvId(cv1.getId());

            for (CVSubmission sub : submissions) {
                String jobName = jobRepository.findById(sub.getJobPositionId()).map(Job::getTitle).orElse(sub.getJobPositionId());

                System.out.println("  - " + jobName + ": " + sub.getResult() + " (" + sub.getScore() + ")");
            }
        }
    }
}
