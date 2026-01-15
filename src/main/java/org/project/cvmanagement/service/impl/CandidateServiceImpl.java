package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.common.CommonConstant;
import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.enums.CandidateStatus;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.exception.CandidateNotFoundException;
import org.project.cvmanagement.exception.CandidateUpdateException;
import org.project.cvmanagement.exception.DuplicateCandidateException;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.service.CandidateService;
import org.project.cvmanagement.util.CommonUtil;

import java.util.List;
import java.util.Optional;

public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
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
        return List.of();
    }
}
