package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.common.CommonConstant;
import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.enums.CVStatus;
import org.project.cvmanagement.enums.Level;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.exception.DuplicateCVException;
import org.project.cvmanagement.repository.CVRepository;
import org.project.cvmanagement.service.CVService;
import org.project.cvmanagement.util.CommonUtil;

public class CVServiceImpl implements CVService {
    public final CVRepository cvRepository;

    public CVServiceImpl(CVRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    @Override
    public void createCV(CV cv) {
        if (cv == null) {
            throw new BusinessException(CommonConstant.NOT_NULL_CV_ERROR_MESSAGE);
        }
        if (CommonUtil.isBlank(cv.getId())) {
            throw new BusinessException(CommonConstant.REQUIRED_CV_ERROR_MESSAGE);
        }

        System.out.println("Create new cv with cvId " + cv.getId());

        boolean existed = cvRepository.findById(cv.getId()).isPresent();
        if (existed) {
            System.err.println("Duplicate cv (same id)" + cv.getId());
            throw  new DuplicateCVException(cv.getId());
        }
        cv.setLevel(Level.INTERN);
        cv.setStatus(CVStatus.DRAFT);

        cvRepository.save(cv);
        System.out.println("Successfully create cv.");
    }


    @Override
    public void submitCV(String cvId) {
        if (CommonUtil.isBlank(cvId)){
            throw new BusinessException(CommonConstant.REQUIRED_CV_ERROR_MESSAGE);
        }

    }

    @Override
    public CV getById(String cvId) {
        return null;
    }
}
