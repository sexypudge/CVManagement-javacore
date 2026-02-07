package org.project.cvmanagement;

import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.enums.CandidateStatus;
import org.project.cvmanagement.enums.Level;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.repository.CVRepository;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.repository.impl.CVRepositoryImpl;
import org.project.cvmanagement.repository.impl.CandidateRepositoryImpl;
import org.project.cvmanagement.service.CVService;
import org.project.cvmanagement.service.CandidateService;
import org.project.cvmanagement.service.impl.CVServiceImpl;
import org.project.cvmanagement.service.impl.CandidateServiceImpl;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static CandidateService candidateService;
    private static CVService cvService;

    public static void main(String[] args) {
        // Wiring (in-memory)
        CandidateRepository candidateRepository = new CandidateRepositoryImpl();
        CVRepository cvRepository = new CVRepositoryImpl();

        candidateService = new CandidateServiceImpl(candidateRepository);
        cvService = new CVServiceImpl(cvRepository, candidateRepository);

        while (true) {
            printMainMenu();
            int choice = readInt("Choose: ");

            switch (choice) {
                case 1 -> candidateMenu();
                case 2 -> cvMenu();
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ========================= MENUS =========================

    private static void printMainMenu() {
        System.out.println("\n==============================");
        System.out.println(" CV MANAGEMENT (Console)");
        System.out.println("==============================");
        System.out.println("1. Candidate Management");
        System.out.println("2. CV Management");
        System.out.println("0. Exit");
        System.out.println("------------------------------");
    }

    private static void candidateMenu() {
        while (true) {
            System.out.println("\n------ Candidate Management ------");
            System.out.println("1. Add candidate");
            System.out.println("2. Update candidate");
            System.out.println("3. Deactivate candidate (soft delete)");
            System.out.println("4. Find candidate by id");
            System.out.println("5. Search candidate by name (contains)");
            System.out.println("6. List ACTIVE candidates");
            System.out.println("0. Back");
            System.out.println("----------------------------------");

            int choice = readInt("Choose: ");
            switch (choice) {
                case 1 -> handleAddCandidate();
                case 2 -> handleUpdateCandidate();
                case 3 -> handleDeactivateCandidate();
                case 4 -> handleFindCandidateById();
                case 5 -> handleSearchCandidateByName();
                case 6 -> handleListActiveCandidates();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void cvMenu() {
        while (true) {
            System.out.println("\n---------- CV Management ----------");
            System.out.println("1. Create CV for candidate");
            System.out.println("2. Update CV skills / level");
            System.out.println("3. List CV by candidate id");
            System.out.println("0. Back");
            System.out.println("----------------------------------");

            int choice = readInt("Choose: ");
            switch (choice) {
                case 1 -> handleCreateCV();
                case 2 -> handleUpdateCV();
                case 3 -> handleListCvByCandidateId();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ========================= HANDLERS: Candidate =========================

    private static void handleAddCandidate() {
        try {
            System.out.println("\n[Add Candidate]");
            String id = readLine("Id: ");
            String fullName = readLine("Full name: ");
            String email = readLine("Email: ");
            int yoe = readInt("Years of experience: ");

            Candidate candidate = new Candidate(id, fullName, email, yoe, CandidateStatus.ACTIVE);
            candidateService.addCandidate(candidate);

            System.out.println("=> Added candidate successfully.");
        } catch (BusinessException e) {
            System.out.println("=> Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("=> Unexpected error: " + e.getMessage());
        }
    }

    private static void handleUpdateCandidate() {
        try {
            System.out.println("\n[Update Candidate]");
            String id = readLine("Id: ");
            String fullName = readLine("New full name: ");
            String email = readLine("New email: ");
            int yoe = readInt("New years of experience: ");

            // status will be kept in service (it loads existing then updates info fields only)
            Candidate candidate = new Candidate(id, fullName, email, yoe, CandidateStatus.ACTIVE);
            candidateService.updateCandidate(candidate);

            System.out.println("=> Updated candidate successfully.");
        } catch (BusinessException e) {
            System.out.println("=> Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("=> Unexpected error: " + e.getMessage());
        }
    }

    private static void handleDeactivateCandidate() {
        try {
            System.out.println("\n[Deactivate Candidate]");
            String id = readLine("Candidate id: ");
            candidateService.deactivateCandidate(id);
            System.out.println("=> Deactivated successfully.");
        } catch (BusinessException e) {
            System.out.println("=> Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("=> Unexpected error: " + e.getMessage());
        }
    }

    private static void handleFindCandidateById() {
        try {
            System.out.println("\n[Find Candidate By Id]");
            String id = readLine("Candidate id: ");
            Candidate c = candidateService.getById(id);

            System.out.println("=> Found:");
            System.out.println("Id: " + c.getId());
            System.out.println("Full name: " + c.getFullName());
            System.out.println("Email: " + c.getEmail());
            System.out.println("YOE: " + c.getYearsOfExperience());
            System.out.println("Status: " + c.getStatus());
        } catch (BusinessException e) {
            System.out.println("=> Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("=> Unexpected error: " + e.getMessage());
        }
    }

    private static void handleSearchCandidateByName() {
        try {
            System.out.println("\n[Search Candidate By Name]");
            String keyword = readLine("Keyword (contains): ");
            var results = candidateService.searchByName(keyword);

            if (results.isEmpty()) {
                System.out.println("=> No candidates found.");
                return;
            }

            System.out.println("=> Results:");
            results.forEach(c ->
                    System.out.println(c.getId() + " | " + c.getFullName() + " | " + c.getEmail() + " | " + c.getStatus())
            );
        } catch (BusinessException e) {
            System.out.println("=> Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("=> Unexpected error: " + e.getMessage());
        }
    }

    private static void handleListActiveCandidates() {
        try {
            System.out.println("\n[List ACTIVE Candidates]");
            var results = candidateService.listActiveCandidates();

            if (results.isEmpty()) {
                System.out.println("=> No ACTIVE candidates.");
                return;
            }

            results.forEach(c ->
                    System.out.println(c.getId() + " | " + c.getFullName() + " | " + c.getEmail())
            );
        } catch (BusinessException e) {
            System.out.println("=> Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("=> Unexpected error: " + e.getMessage());
        }
    }

    // ========================= HANDLERS: CV =========================

    private static void handleCreateCV() {
        try {
            System.out.println("\n[Create CV]");
            String cvId = readLine("CV id: ");
            String candidateId = readLine("Candidate id: ");
            Set<String> skills = readSkills("Skills (comma separated, e.g. Java,Spring,Docker): ");
            Level level = readLevel("Level (e.g. JUNIOR/MIDDLE/SENIOR): ");

            CV cv = new CV(cvId, candidateId, skills, level, null);
            cvService.createCV(cv);

            System.out.println("=> Created CV successfully.");
        } catch (BusinessException e) {
            System.out.println("=> Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("=> Unexpected error: " + e.getMessage());
        }
    }

    private static void handleUpdateCV() {
        try {
            System.out.println("\n[Update CV Skills/Level]");
            String cvId = readLine("CV id: ");
            Set<String> skills = readSkills("New skills (comma separated): ");
            Level level = readLevel("New level (JUNIOR/MIDDLE/SENIOR): ");

            cvService.updateSkillAndLevel(cvId, skills, level);
            System.out.println("=> Updated CV successfully.");
        } catch (BusinessException e) {
            System.out.println("=> Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("=> Unexpected error: " + e.getMessage());
        }
    }

    private static void handleListCvByCandidateId() {
        try {
            System.out.println("\n[List CV By Candidate Id]");
            String candidateId = readLine("Candidate id: ");

            var cvs = cvService.listByCandidateId(candidateId);
            if (cvs.isEmpty()) {
                System.out.println("=> No CV found for candidate " + candidateId);
                return;
            }

            cvs.forEach(cv ->
                    System.out.println(cv.getId() + " | " + cv.getCandidateId() + " | " + cv.getSkills() + " | " + cv.getLevel() + " | " + cv.getStatus())
            );
        } catch (BusinessException e) {
            System.out.println("=> Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("=> Unexpected error: " + e.getMessage());
        }
    }

    // ========================= INPUT HELPERS =========================

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String raw = SCANNER.nextLine().trim();
            try {
                return Integer.parseInt(raw);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private static Set<String> readSkills(String prompt) {
        String raw = readLine(prompt);
        if (raw.isBlank()) {
            return Set.of();
        }
        return Arrays.stream(raw.split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .collect(Collectors.toSet());
    }

    private static Level readLevel(String prompt) {
        while (true) {
            String raw = readLine(prompt);
            try {
                return Level.valueOf(raw.trim().toUpperCase());
            } catch (Exception e) {
                System.out.println("Invalid level. Please type JUNIOR / MIDDLE / SENIOR.");
            }
        }
    }
}
