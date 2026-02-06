package org.project.cvmanagement.common;

public final class CommonConstant {
    private CommonConstant() {
    }

    // ===== Candidate =====
    public static final String CANDIDATE_NULL = "Candidate must not be null";
    public static final String CANDIDATE_ID_REQUIRED = "Candidate id is required";
    public static final String CANDIDATE_NAME_REQUIRED = "Candidate full name is required";
    public static final String CANDIDATE_EMAIL_REQUIRED = "Candidate email is required";
    public static final String CANDIDATE_YOE_INVALID = "Candidate years of experience must be >= 0";

    // ===== CV =====
    public static final String CV_NULL = "CV must not be null";
    public static final String CV_ID_REQUIRED = "CV id is required";
    public static final String CV_CANDIDATE_ID_REQUIRED = "Candidate id in CV is required";
    public static final String CV_SKILLS_REQUIRED = "CV skills is required";
    public static final String CV_LEVEL_REQUIRED = "CV level is required";
}
