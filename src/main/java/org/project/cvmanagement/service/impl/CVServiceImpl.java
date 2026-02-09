package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.common.CommonConstant;
import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.enums.CVStatus;
import org.project.cvmanagement.enums.CandidateStatus;
import org.project.cvmanagement.enums.Level;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.exception.CVNotFoundException;
import org.project.cvmanagement.exception.CandidateInactiveException;
import org.project.cvmanagement.exception.CandidateNotFoundException;
import org.project.cvmanagement.exception.DuplicateCVException;
import org.project.cvmanagement.repository.CVRepository;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.service.CVService;
import org.project.cvmanagement.util.CommonUtil;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CVServiceImpl implements CVService {

    private final CVRepository cvRepository;
    private final CandidateRepository candidateRepository;

    public CVServiceImpl(CVRepository cvRepository, CandidateRepository candidateRepository) {
        this.cvRepository = cvRepository;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void createCV(CV cv) {
        validateCvForCreate(cv);

        boolean existed = cvRepository.findById(cv.getId()).isPresent();
        if (existed) {
            throw new DuplicateCVException(cv.getId());
        }

        Candidate candidate = candidateRepository.findById(cv.getCandidateId())
                .orElseThrow(() -> new CandidateNotFoundException(cv.getCandidateId()));

        if (candidate.getStatus() == CandidateStatus.INACTIVE) {
            throw new CandidateInactiveException(candidate.getId());
        }

        cv.setStatus(CVStatus.DRAFT);
        cvRepository.save(cv);
    }

    @Override
    public void updateSkillAndLevel(String cvId, Set<String> skills, Level level) {
        CV existing = getById(cvId);

        if (skills == null || skills.isEmpty()) {
            throw new BusinessException(CommonConstant.CV_SKILLS_REQUIRED);
        }
        if (level == null) {
            throw new BusinessException(CommonConstant.CV_LEVEL_REQUIRED);
        }

        existing.setSkills(skills);
        existing.setLevel(level);
        cvRepository.save(existing);
    }

    @Override
    public List<CV> listByCandidateId(String candidateId) {
        if (CommonUtil.isBlank(candidateId)) {
            throw new BusinessException(CommonConstant.CV_CANDIDATE_ID_REQUIRED);
        }
        return cvRepository.findByCandidateId(candidateId).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public void submitCV(String cvId) {
        CV existing = getById(cvId);
        if (existing.getStatus() != CVStatus.DRAFT) {
            throw new BusinessException("Only DRAFT CV can be submitted");
        }
        existing.setStatus(CVStatus.SUBMITTED);
        cvRepository.save(existing);
    }

    @Override
    public CV getById(String cvId) {
        if (CommonUtil.isBlank(cvId)) {
            throw new BusinessException(CommonConstant.CV_ID_REQUIRED);
        }
        return cvRepository.findById(cvId)
                .orElseThrow(() -> new CVNotFoundException(cvId));
    }

    private void validateCvForCreate(CV cv) {
        if (cv == null) {
            throw new BusinessException(CommonConstant.CV_NULL);
        }
        if (CommonUtil.isBlank(cv.getId())) {
            throw new BusinessException(CommonConstant.CV_ID_REQUIRED);
        }
        if (CommonUtil.isBlank(cv.getCandidateId())) {
            throw new BusinessException(CommonConstant.CV_CANDIDATE_ID_REQUIRED);
        }
        if (cv.getSkills() == null || cv.getSkills().isEmpty()) {
            throw new BusinessException(CommonConstant.CV_SKILLS_REQUIRED);
        }
        if (cv.getLevel() == null) {
            throw new BusinessException(CommonConstant.CV_LEVEL_REQUIRED);
        }
    }
}
