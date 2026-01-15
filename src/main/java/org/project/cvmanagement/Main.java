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
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CandidateRepository candidateRepository = new CandidateRepositoryImpl();
        CandidateService candidateService = new CandidateServiceImpl(candidateRepository);

        CVRepository cvRepository = new CVRepositoryImpl();
        CVService cvService = new CVServiceImpl(cvRepository);

        while (true) {
            System.out.println("Main menu");
            System.out.println("1. Candidate Service");
            System.out.println("2. CV Service");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    showCandidateService(scanner, candidateService);
                case 2:
                    showCVService(scanner, cvService);
                case 0:
                    return;
                default:
                    System.out.println("Invaid input.");
            }
        }
//        CandidateRepository candidateRepository = new CandidateRepositoryImpl();
//
//        CandidateService candidateService = new CandidateServiceImpl(candidateRepository);
//
//        Candidate candidate = new Candidate(
//                "C001",
//                "Nguyễn Văn Anh",
//                "a@gmail.com",
//                2,
//                null
//        );
//
//        candidateService.addCandidate(candidate);
//        System.out.println("Candidate created successfully");
//
//

        // TODO:
        // 1. init services
        // 2. show menu
        // 3. read input
        // 4. call service
    }

    public static void showCandidateService(Scanner scanner, CandidateService candidateService) {
        while (true){
            System.out.println("Candidate service menu");
            System.out.println("1. Add new candidate");
            System.out.println("2. Update candidate");
            System.out.println("3. Deactivate candidate");
            System.out.println("0. Back");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1: candidateService.addCandidate();
            }
        }
    }

    public static void showCVService(Scanner scanner, CVService cvService) {
    }
}