package org.project.cvmanagement;

import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.CVSubmission;
import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.enums.Level;
import org.project.cvmanagement.repository.CVRepository;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.repository.JobRepository;
import org.project.cvmanagement.repository.impl.CVRepositoryImpl;
import org.project.cvmanagement.repository.impl.CandidateRepositoryImpl;
import org.project.cvmanagement.repository.impl.JobRepositoryImpl;
import org.project.cvmanagement.service.CVService;
import org.project.cvmanagement.service.CandidateService;
import org.project.cvmanagement.service.JobService;
import org.project.cvmanagement.service.impl.CVServiceImpl;
import org.project.cvmanagement.service.impl.CandidateServiceImpl;
import org.project.cvmanagement.service.impl.JobServiceImpl;

import java.util.*;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static CandidateRepository candidateRepository = new CandidateRepositoryImpl();
    static CandidateService candidateService = new CandidateServiceImpl(candidateRepository);
    static CVRepository cvRepository = new CVRepositoryImpl();
    static CVService cvService = new CVServiceImpl(cvRepository, candidateRepository);
    static JobRepository jobRepository = new JobRepositoryImpl();
    static JobService jobService = new JobServiceImpl(jobRepository, cvRepository);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Main menu");
            System.out.println("1. Candidate Service");
            System.out.println("2. CV Service");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    showCandidateService(scanner);
                case 2:
                    showCVService(scanner);
                case 3:
                    showJobService(scanner);
                case 0:
                    return;
                default:
                    System.out.println("Invaid input.");
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
                case 2:
                    handleUpdateCandidate();
                case 3:
                    handleDeactivateCandidate();
                case 0:
                    return;
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

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleCreateCV();
                case 2:
                    handleUpdateCV();
                case 3:
                    handleSubmitCV();
                case 4:
                    handleDeleteCV();
                case 0:
                    return;
            }
        }
    }

    public static void showJobService(Scanner scanner) {
        while (true) {
            System.out.println("Job menu.");
            System.out.println("1. Add job");
            System.out.println("2. Update job");
            System.out.println("3. Delete job");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    handleAddJob();
                case 2:
                    handleUpdateJob();
                case 3:
                    handleDeleteJob();
                case 0:
                    return;
            }
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

            System.out.println("Candidate added.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleUpdateCandidate() {
        try {
            System.out.println("Update candidate");
            System.out.println("Enter id: ");
            String id = scanner.nextLine();
            System.out.println("Enter name: ");
            String fullName = scanner.nextLine();
            System.out.println("Enter email: ");
            String email = scanner.nextLine();
            System.out.println("Enter year of experience: ");
            int yearExperience = Integer.parseInt(scanner.nextLine());

            Candidate candidate = new Candidate(id, fullName, email, yearExperience, null);
            candidateService.updateCandidate(candidate);

            System.out.println("Candidate updated.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleDeactivateCandidate() {

        try {
            System.out.println("Update candidate");
            System.out.println("Enter id: ");
            String id = scanner.nextLine();

            candidateService.deactivateCandidate(id);
            System.out.println("Deactivated candidate.");
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
            System.out.println("Enter skills");
            String skillIssue = scanner.nextLine();
            List<String> skills = Arrays.asList(skillIssue);
            scanner.nextLine();

            CV cv = new CV(id, candidateId, skills, null, null);
            cvService.createCV(cv);

            System.out.println("CV Created.");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    private static void handleUpdateCV() {
        try {
            System.out.println("Enter cv id:");

            Level[] levels = Level.values();
            System.out.println("Chose level: ");
            for (int i = 0; i < levels.length; i++) {
                System.out.println(i + "." + levels[i]);
            }
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 || choice <= levels.length) {
                System.out.println(levels[choice - 1]);
            }
            String skills = scanner.nextLine();
            List<String> skillList = Arrays.asList(skills);
            Level selectedLevel = levels[choice - 1];
            CV cv = new CV(null, null, skillList, null, null);

            cv.setLevel(selectedLevel);
            cv.setSkills(skillList);

            cvService.updateCV(cv);
        } catch (Exception e) {
            System.out.println("Please Enter valid number.");
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
            System.out.println("Enter skills");
            String skills = scanner.nextLine();
            Set<String> skillSet = new HashSet<>(Arrays.asList(skills.split(",")));

            Job job = new Job(id, title, null, skillSet);

            Level[] reqLevels = Level.values();
            System.out.println("Chose required level: ");
            for (int i = 0; i < reqLevels.length; i++) {
                System.out.println(i + "." + reqLevels[i]);
            }
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 || choice <= reqLevels.length) {
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
                System.out.println(i + "." + reqLevels[i]);
            }
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 || choice <= reqLevels.length) {
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
    private static void handleDeleteJob(){
        try {
            System.out.println("Delete job");
            System.out.println("Enter job id:");
            String jobId = scanner.nextLine();
            System.out.println("Enter job id:");
            String cvId = scanner.nextLine();

            jobService.deleteJob(cvId,jobId);
            System.out.println("JOb deleted");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}