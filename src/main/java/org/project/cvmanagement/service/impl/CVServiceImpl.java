package org.project.cvmanagement.service.impl;

import org.project.cvmanagement.common.CommonConstant;
import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.enums.CVStatus;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.repository.CVRespository;
import org.project.cvmanagement.service.CVService;
import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.util.CommonUtil;

import java.util.List;

public class CVServiceImpl implements CVService {
    private final CVRespository cvRespository;
    public CVServiceImpl(CVRespository cvRespository){
        this.cvRespository=cvRespository;
    }
    @Override
    public void createCV(CV cv){
        if (cv == null) {
            throw new BusinessException(CommonConstant.NOT_NULL_CV_ERROR_MESSAGE);
        }

        if (CommonUtil.isBlank(cv.getId())) {
            throw new BusinessException(CommonConstant.REQUIRED_CV_ERROR_MESSAGE);
        }

        cv.setStatus(CVStatus.DRAFT);
        cvRespository.save(cv);
        System.out.println("Successfully created CV: "+cv.getId());

    }


    @Override
    public CV getById(String cvId) {
        return null;
    }

    @Override
    public void submitCV(String cvId){}
}
