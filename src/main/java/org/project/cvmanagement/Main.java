package org.project.cvmanagement;

import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.enums.CandidateStatus;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.repository.impl.CandidateRepositoryImpl;
import org.project.cvmanagement.service.CandidateService;
import org.project.cvmanagement.service.impl.CandidateServiceImpl;


import java.util.Scanner;

public class Main {
    private static Scanner sc;
    private static CandidateService candidateService;

    private static CandidateStatus candidateStatus;
    private static CandidateRepository candidateRepository;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        candidateRepository = new CandidateRepositoryImpl();
        candidateService = new CandidateServiceImpl(candidateRepository);
        while(true){
            System.out.println("1. Add candidate\n" +
                    "2. Create CV\n" +
                    "3. Submit CV\n" +
                    "4. Add job position\n" +
                    "5. Apply CV to job\n" +
                    "6. Evaluate CV\n" +
                    "7. Show candidate report\n" +
                    "8. Statistics\n" +
                    "9. Exit\n");
            System.out.println("<----------------------------->");
            System.out.println("Please choose a number: ");
            int answer;
            try{
                answer=sc.nextInt();
            }catch (NumberFormatException e){
                System.out.println("Invalid number!");
                continue;
            }
            switch (answer){
                case 1:
                    processAddCandidate();
                    break;
                case 2:
                    System.out.println("Under construction");
                    break;
                case 3:
                    System.out.println("Under construction");
                    break;
                case 4:
                    System.out.println("Under construction");
                    break;
                case 5:
                    System.out.println("Under construction");
                    break;
                case 6:
                    System.out.println("Under construction");
                    break;
                case 7:
                    System.out.println("Under construction");
                    break;
                case 8:
                    System.out.println("Under construction");
                    break;
                case 9:
                    System.out.println("Good bye!!");
                    System.exit(0);
                default:
                    System.out.println("Choose a number from 1-9");
            }
        }










        // TODO:
        // 1. init services
        // 2. show menu
        // 3. read input
        // 4. call service
    }
    public static void processAddCandidate(){
        System.out.println("--- ADD NEW CANDIDATE ---");
        try {
            System.out.print("Enter ID: ");
            sc.nextLine();
            String id = sc.nextLine();

            System.out.print("Enter Full Name: ");
            String fullName = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Years of Experience: ");
            int yoe = sc.nextInt();


            Candidate newCandidate = new Candidate(
                    id,
                    fullName,
                    email,
                    yoe,
                    candidateStatus.ACTIVE
            );


            candidateService.addCandidate(newCandidate);

            System.out.println("Success: Candidate added successfully!");

        } catch (NumberFormatException e) {
            System.err.println("Error: Years of experience must be a number.");
        } catch (RuntimeException e) {

            System.err.println("Error: " + e.getMessage());
        }

    }
}