package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.common.CommonConstant;
import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.enums.CandidateStatus;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.exception.CandidateNotFoundException;
import org.project.cvmanagement.exception.DuplicateCandidateException;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.service.CandidateService;
import org.project.cvmanagement.util.CommonUtil;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void addCandidate(Candidate candidate) {
        validateCandidateForCreate(candidate);

        boolean existed = candidateRepository.findById(candidate.getId()).isPresent();
        if (existed) {
            throw new DuplicateCandidateException(candidate.getId());
        }

        candidate.setStatus(CandidateStatus.ACTIVE);
        candidateRepository.save(candidate);
    }

    @Override
    public void updateCandidate(Candidate candidate) {
        if (candidate == null) {
            throw new BusinessException(CommonConstant.CANDIDATE_NULL);
        }
        if (CommonUtil.isBlank(candidate.getId())) {
            throw new BusinessException(CommonConstant.CANDIDATE_ID_REQUIRED);
        }

        Candidate existing = getById(candidate.getId()); // throws if not found

        // Only update information fields, keep status
        validateCandidateInfo(candidate);
        existing.setFullName(candidate.getFullName());
        existing.setEmail(candidate.getEmail());
        existing.setYearsOfExperience(candidate.getYearsOfExperience());

        candidateRepository.save(existing);
    }

    @Override
    public void deactivateCandidate(String candidateId) {
        Candidate existing = getById(candidateId);
        existing.setStatus(CandidateStatus.INACTIVE);
        candidateRepository.save(existing);
    }

    @Override
    public Candidate getById(String candidateId) {
        if (CommonUtil.isBlank(candidateId)) {
            throw new BusinessException(CommonConstant.CANDIDATE_ID_REQUIRED);
        }
        return candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException(candidateId));
    }

    @Override
    public List<Candidate> searchByName(String keyword) {
        if (CommonUtil.isBlank(keyword)) {
            return List.of();
        }

        final String normalized = keyword.trim().toLowerCase(Locale.ROOT);
        return candidateRepository.findAll().stream()
                .filter(Objects::nonNull)
                .filter(c -> !CommonUtil.isBlank(c.getFullName()))
                .filter(c -> c.getFullName().toLowerCase(Locale.ROOT).contains(normalized))
                .collect(Collectors.toList());
    }

    @Override
    public List<Candidate> listActiveCandidates() {
        return candidateRepository.findAll().stream()
                .filter(Objects::nonNull)
                .filter(c -> c.getStatus() == CandidateStatus.ACTIVE)
                .collect(Collectors.toList());
    }

    private void validateCandidateForCreate(Candidate candidate) {
        if (candidate == null) {
            throw new BusinessException(CommonConstant.CANDIDATE_NULL);
        }
        if (CommonUtil.isBlank(candidate.getId())) {
            throw new BusinessException(CommonConstant.CANDIDATE_ID_REQUIRED);
        }
        validateCandidateInfo(candidate);
    }

    private void validateCandidateInfo(Candidate candidate) {
        if (CommonUtil.isBlank(candidate.getFullName())) {
            throw new BusinessException(CommonConstant.CANDIDATE_NAME_REQUIRED);
        }
        if (CommonUtil.isBlank(candidate.getEmail())) {
            throw new BusinessException(CommonConstant.CANDIDATE_EMAIL_REQUIRED);
        }
        if (candidate.getYearsOfExperience() < 0) {
            throw new BusinessException(CommonConstant.CANDIDATE_YOE_INVALID);
        }
    }
}
