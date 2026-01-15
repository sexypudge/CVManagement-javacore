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
        if (CommonUtil.isBlank(candidate.getFullName())) {
            throw new BusinessException(CommonConstant.REQUIRED_CANDIDATE_ERROR_MESSAGE);
        }
        if (CommonUtil.isBlank(candidate.getEmail())) {
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
        if (candidate == null || CommonUtil.isBlank(candidate.getId())) {
            throw new BusinessException("Invalid candidate data");
        }

        Candidate existed = candidateRepository.findById(candidate.getId()).orElseThrow(()-> new RuntimeException("Candidate not found"));


        // kt null


        existed.setFullName(candidate.getFullName());
        existed.setEmail(candidate.getEmail());
        existed.setYearsOfExperience(candidate.getYearsOfExperience());

        candidateRepository.save(existed);

        System.out.println("Update successful: " + candidate.getId());
    }

    @Override
    public void deactivateCandidate(String candidateId) {


        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(()-> new RuntimeException("Candidate not found"));

        candidate.setStatus(CandidateStatus.INACTIVE);
        candidateRepository.save(candidate);

        System.out.println("Deactivated candidate with candidateId: " + candidateId);


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
