package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.domain.*;
import org.project.cvmanagement.enums.*;
import org.project.cvmanagement.exception.*;
import org.project.cvmanagement.repository.*;
import org.project.cvmanagement.service.CVService;

import java.util.List;

public class CVServiceImpl implements CVService {
    private final CVRepository cvRepository;
    private final CandidateRepository candidateRepository;

    public CVServiceImpl(CVRepository cvRepository, CandidateRepository candidateRepository) {
        this.cvRepository = cvRepository;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void createCV(CV cv) {
        //  Kt candidate có tồn tại khoong
        Candidate candidate = candidateRepository.findById(cv.getCandidateId())
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found"));

        // kt candidate đã active chaw
        if (candidate.getStatus() == CandidateStatus.INACTIVE) {
            throw new BusinessException("Cannot create CV for INACTIVE candidate");
        }
        // cv mới sẽ ở trạng  thái draff
        cv.setStatus(CVStatus.DRAFT);
        cvRepository.save(cv);
    }

    @Override
    public void updateCV(String cvId, List<String> skills, Level level) {
        CV cv = cvRepository.findById(cvId)
                .orElseThrow(() -> new BusinessException("CV does not exist "));

        // Cập nhật ttin
        cv.setSkills(skills);
        cv.setLevel(level);

        cvRepository.save(cv);
        System.out.println("CV update successful ");
    }

    @Override
    public void submitCV(String cvId) {
        CV cv = cvRepository.findById(cvId)
                .orElseThrow(() -> new BusinessException("CV not found"));
        cv.setStatus(CVStatus.SUBMITTED);
        cvRepository.save(cv);
        System.out.println("CV " + cvId + " has been SUBMITTED.");
    }

    // loọc dl
    @Override
    public List<CV> getCVsByCandidate(String candidateId) {
        return cvRepository.findByCandidateId(candidateId);
    }

}