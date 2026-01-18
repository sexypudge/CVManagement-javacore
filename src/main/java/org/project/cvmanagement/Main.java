package org.project.cvmanagement;

import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.repository.CVRepository;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.repository.impl.CVRepositoryImpl;
import org.project.cvmanagement.repository.impl.CandidateRepositoryImpl;
import org.project.cvmanagement.service.CVService;
import org.project.cvmanagement.service.CandidateService;
import org.project.cvmanagement.service.impl.CVServiceImpl;
import org.project.cvmanagement.service.impl.CandidateServiceImpl;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static CandidateRepository candidateRepository = new CandidateRepositoryImpl();
    static CandidateService candidateService = new CandidateServiceImpl(candidateRepository);
    static CVRepository cvRepository = new CVRepositoryImpl();
    static CVService cvService = new CVServiceImpl(cvRepository,candidateRepository);

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
            scanner.nextLine();
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
    private static void handleCreateCV(){

    }
    private static void handleUpdateCV(){

    }
    private static void handleSubmitCV(){

    }
}