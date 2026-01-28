package org.project.cvmanagement;

import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.enums.Level;
import org.project.cvmanagement.repository.CVRepository;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.repository.JobRepository;
import org.project.cvmanagement.repository.impl.SubmissionRepositoryImpl;
import org.project.cvmanagement.repository.impl.CVRepositoryImpl;
import org.project.cvmanagement.repository.impl.CandidateRepositoryImpl;
import org.project.cvmanagement.repository.impl.JobRepositoryImpl;
import org.project.cvmanagement.service.CVService;
import org.project.cvmanagement.service.CandidateService;
import org.project.cvmanagement.service.JobService;
import org.project.cvmanagement.service.SubmissionService;
import org.project.cvmanagement.service.impl.CVServiceImpl;
import org.project.cvmanagement.service.impl.CandidateServiceImpl;
import org.project.cvmanagement.service.impl.JobServiceImpl;
import org.project.cvmanagement.service.impl.SubmissionServiceImpl;

import java.util.*;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static CandidateRepository candidateRepository = new CandidateRepositoryImpl();
    static CVRepository cvRepository = new CVRepositoryImpl();
    static JobRepository jobRepository = new JobRepositoryImpl();

    static SubmissionRepositoryImpl submissionRepository = new SubmissionRepositoryImpl();
    static CandidateService candidateService = new CandidateServiceImpl(candidateRepository, cvRepository, jobRepository, submissionRepository);
    static CVService cvService = new CVServiceImpl(cvRepository, candidateRepository);
    static JobService jobService = new JobServiceImpl(jobRepository, cvRepository);
    static SubmissionService submissionService = new SubmissionServiceImpl(submissionRepository,jobRepository, cvRepository);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Main menu");
            System.out.println("1. Candidate Service");
            System.out.println("2. CV Service");
            System.out.println("3. Job Service");
            System.out.println("4. Submission");
            System.out.println("5. Show candidate report");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    showCandidateService(scanner);
                    break;
                case 2:
                    showCVService(scanner);
                    break;
                case 3:
                    showJobService(scanner);
                    break;
                case 4:
                    showSubmission(scanner);
                    break;
                case 5:
                    showCandidateReport(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
        // TODO:
        // 1. init services
        // 2. show menu
        // 3. read input
        // 4. call service
    }

    public static void showCandidateService(Scanner scanner) {
        while (true) {
            System.out.println("Candidate service menu");
            System.out.println("1. Add new candidate");
            System.out.println("2. Update candidate");
            System.out.println("3. Deactivate candidate");
            System.out.println("0. Back");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleAddCandidate();
                    break;
                case 2:
                    handleUpdateCandidate();
                    break;
                case 3:
                    handleDeactivateCandidate();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    public static void showCVService(Scanner scanner) {
        while (true) {
            System.out.println("Candidate service menu");
            System.out.println("1. Create new cv");
            System.out.println("2. Update cv");
            System.out.println("3. Submit cv");
            System.out.println("4. Delete cv");
            System.out.println("0. Back");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    handleCreateCV();
                    break;
                case 2:
                    handleUpdateCV();
                    break;
                case 3:
                    handleSubmitCV();
                    break;
                case 4:
                    handleDeleteCV();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    public static void showJobService(Scanner scanner) {
        while (true) {
            System.out.println("Job menu.");
            System.out.println("1. Add job");
            System.out.println("2. Update job");
            System.out.println("3. Delete job");
            System.out.println("0. Back");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    handleAddJob();
                    break;
                case 2:
                    handleUpdateJob();
                    break;
                case 3:
                    handleDeleteJob();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    public static void showSubmission(Scanner scanner) {
        while (true) {
            System.out.println("Submission");
            System.out.println("1. Apply CV");
            System.out.println("2. Evaluate CV");
            System.out.println("0. Back");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    handleApplyCV();
                    break;
                case 2:
                    handleEvaluateCV();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
    public static void showCandidateReport(Scanner scanner){
        System.out.println("Show candidate report");
        try {
            System.out.println("Enter id:");
            String id = scanner.nextLine();

            candidateService.showCandidateReport(id);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleAddCandidate() {
        try {
            System.out.println("Add new candidate.");
            System.out.println("Enter Candidate id: ");
            String id = scanner.nextLine();
            System.out.println("Enter name: ");
            String fullName = scanner.nextLine();
            System.out.println("Enter email: ");
            String email = scanner.nextLine();
            System.out.println("Enter year of experience: ");
            int yearExperience = Integer.parseInt(scanner.nextLine());

            Candidate candidate = new Candidate(id, fullName, email, yearExperience, null);
            candidateService.addCandidate(candidate);

            System.out.println("Candidate added ");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleUpdateCandidate() {
        try {
            System.out.println("Update candidate");
            System.out.println("Enter id: ");
            String id = scanner.nextLine();
            System.out.println("Update name: ");
            String fullName = scanner.nextLine();
            System.out.println("Update email: ");
            String email = scanner.nextLine();
            System.out.println("Enter year of experience: ");
            int yearExperience = Integer.parseInt(scanner.nextLine());

            Candidate candidate = new Candidate(id, fullName, email, yearExperience, null);
            candidateService.updateCandidate(candidate);
            System.out.println("Candidate updated ");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleDeactivateCandidate() {

        try {
            System.out.println("deactivate candidate");
            System.out.println("Enter id: ");
            String id = scanner.nextLine();

            candidateService.deactivateCandidate(id);
            System.out.println("candidate Deactivated ");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleCreateCV() {
        try {
            System.out.println("Create new cv.");
            System.out.println("Enter id: ");
            String id = scanner.nextLine();
            System.out.println("Enter candidate id: ");
            String candidateId = scanner.nextLine();
            System.out.println("Enter skills (comma separated):");
            String skillIssue = scanner.nextLine();
            List<String> skills = Arrays.asList(skillIssue.split(","));

            CV cv = new CV(id, candidateId, skills, null, null);
            cvService.createCV(cv);

            System.out.println("CV Created.");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    private static void handleUpdateCV() {
        try {
            System.out.print("Enter cv id: ");
            String id = scanner.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println("Error: CV ID cannot be empty!");
                return;
            }

            Level[] levels = Level.values();
            System.out.println("Choose level:");
            for (int i = 0; i < levels.length; i++) {
                System.out.println((i + 1) + ". " + levels[i]);
            }

            System.out.print("Enter level choice (1-" + levels.length + "): ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice < 1 || choice > levels.length) {
                System.out.println("Invalid level choice!");
                return;
            }

            Level selectedLevel = levels[choice - 1];

            System.out.print("Enter skills (comma separated): ");
            String skillsInput = scanner.nextLine();
            List<String> skillList = Arrays.stream(skillsInput.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();

            CV cv = new CV(id, null,skillList,null,null);
            cv.setId(id);
            cv.setLevel(selectedLevel);
            cv.setSkills(skillList);

            cvService.updateCV(cv);
            System.out.println("CV updated successfully.");

        } catch (NumberFormatException e) {
            System.err.println("Error: Please enter a valid number for level.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleSubmitCV() {
        try {
            System.out.println("Submit cv.");
            System.out.println("Enter id: ");
            String id = scanner.nextLine();

            cvService.submitCV(id);
            System.out.println("CV submitted.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleDeleteCV() {
        try {
            System.out.println("Delete cv.");
            System.out.println("Enter id: ");
            String id = scanner.nextLine();

            cvService.deleteCV(id);
            System.out.println("CV Deleted.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    private static void handleAddJob() {
        try {
            System.out.println("Add job.");
            System.out.println("Enter id: ");
            String id = scanner.nextLine();
            System.out.println("Enter title");
            String title = scanner.nextLine();
            System.out.println("Enter required skills (comma separated):");
            String skills = scanner.nextLine();
            Set<String> skillSet = new HashSet<>(Arrays.asList(skills.split(",")));

            Job job = new Job(id, title, null, skillSet);

            Level[] reqLevels = Level.values();
            System.out.println("Chose required level: ");
            for (int i = 0; i < reqLevels.length; i++) {
                System.out.println((i+1) + "." + reqLevels[i]);
            }
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 && choice <= reqLevels.length) {
                System.out.println(reqLevels[choice - 1]);
            }
            Level selectedLevel = reqLevels[choice - 1];
            job.setRequiredLevel(selectedLevel);

            jobService.addJob(job);
            System.out.println("Job added.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleUpdateJob() {
        try {
            System.out.println("update job.");
            System.out.println("Enter id: ");
            String id = scanner.nextLine();
            System.out.println("Change title");
            String title = scanner.nextLine();
            System.out.println("Update skills");
            String skills = scanner.nextLine();
            Set<String> skillSet = new HashSet<>(Arrays.asList(skills.split(",")));

            Job job = new Job(id, title, null, skillSet);

            Level[] reqLevels = Level.values();
            System.out.println("Chose required level: ");
            for (int i = 0; i < reqLevels.length; i++) {
                System.out.println((i+1) + "." + reqLevels[i]);
            }
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 && choice <= reqLevels.length) {
                System.out.println(reqLevels[choice - 1]);
            }
            Level selectedLevel = reqLevels[choice - 1];
            job.setRequiredLevel(selectedLevel);

            jobService.updateJob(job);
            System.out.println("Job updated.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleDeleteJob() {
        try {
            System.out.println("Delete job");
            System.out.println("Enter job id:");
            String jobId = scanner.nextLine();

            jobService.deleteJob(jobId, null);
            System.out.println("Job deleted");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleApplyCV() {
        try {
            System.out.println("Submission");
            System.out.println("Enter cv id: ");
            String cvId = scanner.nextLine();
            System.out.println("Enter job id: ");
            String jobId = scanner.nextLine();

            submissionService.applyCV(cvId,jobId);
            System.out.println("CV applied");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleEvaluateCV() {
        try {
            System.out.println("Evaluate cv");
            System.out.println("Enter job id: ");
            String jobId = scanner.nextLine();
            System.out.println("Enter cv id: ");
            String cvId = scanner.nextLine();
            System.out.println("score (Admin evaluate cv): ");
            double score = Double.parseDouble(scanner.nextLine());

            submissionService.evaluateCV(cvId,jobId,score);
            System.out.println("Evaluated");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}