package org.project.cvmanagement.common;

/**
 * Centralised constants for validation messages and business rules.
 */
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

    // ===== Job =====
    public static final String JOB_NULL = "Job must not be null";
    public static final String JOB_ID_REQUIRED = "Job id is required";
    public static final String JOB_TITLE_REQUIRED = "Job title is required";
    public static final String JOB_REQUIRED_LEVEL_REQUIRED = "Job required level is required";
    public static final String JOB_REQUIRED_SKILLS_REQUIRED = "Job required skills is required";

    // ===== Submission / Apply / Evaluate =====
    public static final String SUBMISSION_ID_REQUIRED = "Submission id is required";
    public static final String SCORE_OUT_OF_RANGE = "Score must be in range [0..10]";
    public static final double PASS_SCORE_THRESHOLD = 5.0;

    // ===== Helpers =====
    public static String submissionId(String cvId, String jobId) {
        return cvId + "::" + jobId;
    }
}
