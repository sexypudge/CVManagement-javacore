package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.common.CommonConstant;
import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.enums.CandidateStatus;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.exception.DuplicateCandidateException;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.service.CandidateService;
import org.project.cvmanagement.util.CommonUtil;

import java.util.List;

public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        // TODO;
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

    }

    @Override
    public void deactivateCandidate(String candidateId) {

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
