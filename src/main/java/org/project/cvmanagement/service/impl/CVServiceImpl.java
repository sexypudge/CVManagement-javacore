package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.common.CommonConstant;
import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.enums.CVStatus;
import org.project.cvmanagement.enums.CandidateStatus;
import org.project.cvmanagement.enums.Level;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.exception.CandidateNotFoundException;
import org.project.cvmanagement.exception.DuplicateCVException;
import org.project.cvmanagement.repository.CVRepository;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.service.CVService;
import org.project.cvmanagement.util.CommonUtil;


public class CVServiceImpl implements CVService {
    public final CVRepository cvRepository;
    private final CandidateRepository candidateRepository;

    // Cập nhật Constructor để nhận cả 2 repository
    public CVServiceImpl(CVRepository cvRepository, CandidateRepository candidateRepository) {
        this.cvRepository = cvRepository;
        this.candidateRepository = candidateRepository;
    }
    @Override
    public void createCV(CV cv) {
        if (cv == null) {
            throw new BusinessException(CommonConstant.NOT_NULL_CV_ERROR_MESSAGE);
        }
        if (CommonUtil.isBlank(cv.getId())) {
            throw new BusinessException(CommonConstant.REQUIRED_CV_ERROR_MESSAGE);
        }
        Candidate candidate = candidateRepository.findById(cv.getCandidateId()).orElseThrow(()-> new CandidateNotFoundException(cv.getCandidateId()));
        if (CandidateStatus.INACTIVE.equals(candidate.getStatus())){
            throw new BusinessException("INACTIVE candidate. Activate candidate to create cv.");
        }
        System.out.println("Create new cv with cvId " + cv.getId());

        boolean existed = cvRepository.findById(cv.getId()).isPresent();
        if (existed) {
            System.err.println("Duplicate cv (same id)" + cv.getId());
            throw new DuplicateCVException(cv.getId());
        }
        cv.setLevel(Level.INTERN);
        cv.setStatus(CVStatus.DRAFT);

        cvRepository.save(cv);
        System.out.println("Successfully create cv.");
    }
    public void updateCV(CV cv){
        if (cv == null) {
            throw new BusinessException(CommonConstant.NOT_NULL_CV_ERROR_MESSAGE);
        }
        if (CommonUtil.isBlank(cv.getId())) {
            throw new BusinessException(CommonConstant.REQUIRED_CV_ERROR_MESSAGE);
        }
        if (cv.getStatus() != CVStatus.DRAFT) {
            throw new BusinessException("Update cv only in DRAFT status");
        }
    }


    @Override
    public void submitCV(String cvId) {
        if (CommonUtil.isBlank(cvId)) {
            throw new BusinessException(CommonConstant.REQUIRED_CV_ERROR_MESSAGE);
        }
        CV cv = cvRepository.findById(cvId).orElseThrow(() -> new BusinessException("CV not found."));

        if (cv.getStatus() != CVStatus.DRAFT) {
            throw new BusinessException("CV should be DRAFT before submit.");
        }
        cv.setStatus(CVStatus.SUBMITTED);
        cvRepository.save(cv);
    }


    @Override
    public CV getById(String cvId) {
        return null;
    }
}
