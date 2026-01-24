package org.project.cvmanagement;

import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.enums.Level;
import org.project.cvmanagement.repository.CVRepository;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.repository.JobRepository;
import org.project.cvmanagement.repository.SubmissionRepository;
import org.project.cvmanagement.repository.impl.CandidateRepositoryImpl;
import org.project.cvmanagement.repository.impl.JobRepositoryImpl;
import org.project.cvmanagement.repository.impl.SubmissionRepositoryImpl;
import org.project.cvmanagement.service.CVService;
import org.project.cvmanagement.service.CandidateService;
import org.project.cvmanagement.service.JobService;
import org.project.cvmanagement.service.SubmissionService;
import org.project.cvmanagement.service.impl.CVServiceImpl;
import org.project.cvmanagement.service.impl.CandidateServiceImpl;
import org.project.cvmanagement.repository.impl.CVRepositoryImpl;
import org.project.cvmanagement.service.impl.JobServiceImpl;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.service.impl.SubmissionServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    Scanner sc = new Scanner(System.in);

    static CandidateRepository candidateRepo = new CandidateRepositoryImpl();
    static CVRepository cvRepo = new CVRepositoryImpl();
    static JobRepository jobRepo = new JobRepositoryImpl();
    static SubmissionRepository submissionRepo = new SubmissionRepositoryImpl();


    static CandidateService candidateService = new CandidateServiceImpl(candidateRepo, cvRepo, jobRepo, submissionRepo);
    static CVService cvService = new CVServiceImpl(cvRepo, candidateRepo);
    static JobService jobService = new JobServiceImpl(jobRepo, submissionRepo);
    static SubmissionService submissionService = new SubmissionServiceImpl(cvRepo, jobRepo, submissionRepo);

    public static void main(String[] args) {
        Main main = new Main();
        main.showMenu();

    }

    // menu
    public void showMenu() {
        while (true) {
            System.out.println("======= CV Management system =======");
            System.out.println("1: Add candidate");
            System.out.println("2: Deactivate Candidate");
            System.out.println("3: Update information ");
            System.out.println("4: Search candidate by name ");
            System.out.println("5:Create CV for candidate");
            System.out.println("6: show all cv of candidate");
            System.out.println("7;  update cv of candidate");
            System.out.println("8: add job position ");
            System.out.println("9: update job position ");
            System.out.println("10: Delete job position");
            System.out.println("11: submit cv ");
            System.out.println("12: Apply cv to job pition");
            System.out.println("13: Evaluate cv");
            System.out.println("14: show candidate's report");
            System.out.println("Please enter your choice : ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    handleAddCandidate();
                    break;
                case "2":
                    handleDeactivateCandidate();
                    break;
                case "3":
                    handleUpdateCandidate();
                    break;
                case "4":
                    handleSearchByname();
                    break;
                case "5":
                    handleCreateCV();
                    break;
                case "6":
                    handleViewCVsByCandidate();
                    break;
                case "7":
                    handleUpdateCV();
                    break;
                case "8":
                    handleAddJob();
                    break;
                case "9":
                    handleUpdateJob();
                    break;
                case "10":
                    handleDeleteJob();
                    break;
                case "11":
                    handleSubmitCV();
                    break;
                case "12":
                    handleApplyCV();
                    break;
                case "13":
                    handleEvaluateCV();
                    break;
                case "14":
                    handleShowReport();
                    break;
            }
        }
    }

    // add new candidate
    private void handleAddCandidate() {
        try {
            System.out.println("====== Add new candidate ======");
            System.out.println("Enter Candidate id: ");
            String id = sc.nextLine();
            System.out.println("Enter name: ");
            String fullName = sc.nextLine();
            System.out.println("Enter email: ");
            String email = sc.nextLine();
            System.out.println("Enter year of experience: ");
            int yearExperience = sc.nextInt();

            Candidate candidate = new Candidate(id, fullName, email, yearExperience, null);
            candidateService.addCandidate(candidate);

            System.out.println("Success: Candidate created");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    //deactive status candidate
    private void handleDeactivateCandidate() {
        try {
            System.out.println("====== Deactivate candidate======");
            System.out.print("Enter id to deactivate: ");
            String id = sc.nextLine();

            candidateService.deactivateCandidate(id);

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    // update candidate infor
    private void handleUpdateCandidate() {
        try {
            System.out.println("====== Update Candidate ======");
            System.out.print("Enter id to update: ");
            String id = sc.nextLine();

            System.out.print("full name: ");
            String name = sc.nextLine();
            System.out.print("Update email: ");
            String email = sc.nextLine();
            System.out.print("update years of experience: ");
            int yoe = Integer.parseInt(sc.nextLine());


            Candidate updateInfo = new Candidate(id, name, email, yoe, null);

            candidateService.updateCandidate(updateInfo);

        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
    }

    // search by name candidate
    private void handleSearchByname() {
        try {
            System.out.println("====== Search Candidate By Name ======");
            System.out.print("Enter name to search: ");
            String keyword = sc.nextLine();

            List<Candidate> results = candidateService.searchByName(keyword);
            if (results.isEmpty()) {
                System.out.println("No candidates found.");
            } else {
                System.out.println(" Candidate: ");
                for (Candidate candidate : results) {
                    System.out.println(" candidate :" + candidate);
                    System.out.println(" id: " + candidate.getId());
                    System.out.println("Name: " + candidate.getFullName());
                    System.out.println("Email: " + candidate.getEmail());
                    System.out.println("Experience:" + candidate.getYearsOfExperience());
                }
            }

        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }

    }

    // create cv
    private void handleCreateCV() {
        try {
            System.out.println("====== Create New CV ======");
            System.out.print("Enter candidate ID: ");
            String candidateId = sc.nextLine();

            System.out.print("Enter cv ID: ");
            String cvId = sc.nextLine();

            System.out.print("Enter skills : ");
            String skillsInput = sc.nextLine();
            List<String> skills = java.util.Arrays.asList(skillsInput.split(","));

            System.out.println("Select Level (1: INTERN, 2: FRESHER, 3: JUNIOR, 4: MIDDLE, 5: SENIOR): ");
            int levelChoice = Integer.parseInt(sc.nextLine());
            Level level = Level.values()[levelChoice - 1]; //
            CV cv = new CV(cvId, candidateId, skills, level, null); //rut gon bằng cách import đầu file.
            cvService.createCV(cv);

            System.out.println("CV created in DRAFT status.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    //list cv of candidate
    private void handleViewCVsByCandidate() {
        System.out.print("Enter candidate ID to view the list of cv: ");
        String candidateId = sc.nextLine();

        List<CV> cvList = cvService.getCVsByCandidate(candidateId);

        if (cvList.isEmpty()) {
            System.out.println("candidate does not have a CV..");
        } else {
            System.out.println("List of canidate's cv " + candidateId + " ---");
            cvList.forEach(cv -> {
                System.out.println("ID CV: " + cv.getId() +
                        " | Level: " + cv.getLevel() +
                        " | Status: " + cv.getStatus() +
                        " | Skills: " + cv.getSkills());
            });
        }
    }

    // update cv.
    private void handleUpdateCV() {
        System.out.print("Enter cv ID needs update: ");
        String cvId = sc.nextLine();

        System.out.print("Import a list of new skills: ");
        String skillsInput = sc.nextLine();
        List<String> skills = java.util.Arrays.asList(skillsInput.split(","));

        System.out.println("Select new level (1:INTERN, 2:FRESHER, 3:JUNIOR, 4:MIDDLE, 5:SENIOR): ");
        int levelIdx = Integer.parseInt(sc.nextLine()) - 1;
        Level level = Level.values()[levelIdx];

        cvService.updateCV(cvId, skills, level);
    }

    private void handleAddJob() {
        try {
            System.out.println("====== Add New Job Position ======");
            System.out.print("Enter Job ID: ");
            String id = sc.nextLine();

            System.out.print("enter job title: ");
            String title = sc.nextLine();

            System.out.println("select Level:");
            System.out.println("1: INTERN, 2: FRESHER, 3: JUNIOR, 4: MIDDLE, 5: SENIOR");
            int levelChoice = Integer.parseInt(sc.nextLine());
            Level level = Level.values()[levelChoice - 1];

            System.out.print("enter required skills : ");
            String skillsInput = sc.nextLine();
            java.util.Set<String> skills = new java.util.HashSet<>();
            for (String s : skillsInput.split(",")) {
                skills.add(s.trim());
            }

            Job job = new Job(id, title, level, skills);
            jobService.addJob(job);

            System.out.println("Job Position created successfull.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void handleUpdateJob() {
        try {
            System.out.println("====== Update Job Requirement ======");
            System.out.print("enter job id to update: ");
            String id = sc.nextLine();

            jobService.getById(id);

            System.out.print("new title: ");
            String title = sc.nextLine();

            System.out.print("New level (1:INTERN, 2:FRESHER, 3:JUNIOR, 4:MIDDLE, 5:SENIOR): ");
            int lv = Integer.parseInt(sc.nextLine());
            Level level = Level.values()[lv - 1];

            System.out.print("new skill: ");
            Set<String> skills = Arrays.stream(sc.nextLine().split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toSet());

            jobService.updateJob(id, title, level, skills);
            System.out.println("iupdate successful");

        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
    }

    private void handleDeleteJob() {
        try {
            System.out.println("delete job position:");
            System.out.print("Enter job id to delete: ");
            String jobId = sc.nextLine();

            jobService.deleteJob(jobId);
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
    }

    private void handleSubmitCV() {
        try {
            System.out.println(" SUBMIT CV ");
            System.out.print("enter cv to submit: ");
            String cvId = sc.nextLine();

            cvService.submitCV(cvId);
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
    }

    private void handleApplyCV() {
        try {
            System.out.println(" apply cv to job position ");
            System.out.print("Enter CV ID: ");
            String cvId = sc.nextLine();

            System.out.print("Enter Job ID: ");
            String jobId = sc.nextLine();
            submissionService.applyCV(cvId, jobId);

        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
    }

    private void handleEvaluateCV() {
        try {

            System.out.println(" EVALUATE CV ");
            System.out.print("enter ID CV: ");
            String cvId = sc.nextLine();
            System.out.print("enter ID Job: ");
            String jobId = sc.nextLine();
            System.out.print("enter score (0-10): ");
            double score = Double.parseDouble(sc.nextLine());

            submissionService.evaluateCV(cvId, jobId, score);
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
    }

    private void handleShowReport() {
        try {
            System.out.println(" CANDIDATE REPORT ");
            System.out.print("Enter candidate id: ");
            String candidateId = sc.nextLine();

            candidateService.showCandidateReport(candidateId);
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
    }
}