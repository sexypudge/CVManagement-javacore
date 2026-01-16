package org.project.cvmanagement;

import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.enums.CandidateStatus;
import org.project.cvmanagement.enums.Level;
import org.project.cvmanagement.repository.CVRespository;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.repository.impl.CVRespositoryImpl;
import org.project.cvmanagement.repository.impl.CandidateRepositoryImpl;
import org.project.cvmanagement.service.CVService;
import org.project.cvmanagement.service.CandidateService;
import org.project.cvmanagement.service.impl.CVServiceImpl;
import org.project.cvmanagement.service.impl.CandidateServiceImpl;
import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.enums.CVStatus;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static CandidateRepository candidateRepository = new CandidateRepositoryImpl();
    private static CandidateService candidateService = new CandidateServiceImpl(candidateRepository);

    private static CandidateStatus candidateStatus;

    private static CVRespository cvRespository = new CVRespositoryImpl();
    private static CVService cvService = new CVServiceImpl(cvRespository);




    public static void main(String[] args) {
        Main main = new Main();
        main.showMenu();


        // TODO:
        // 1. init services
        // 2. show menu
        // 3. read input
        // 4. call service
    }
    public void showMenu(){
        while(true){
            System.out.println("1. Add candidate\n" +
                    "2. Deactivate candidate\n" +
                    "3. Create new CV\n" +
                    "4. Show list candidates\n" +
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
                    deactivateCandidate();
                    break;
                case 3:
                    processAddCV();
                    break;
                case 4:
                    showList();
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
    }
    private static void processAddCandidate(){
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
                    null
            );
            candidateService.addCandidate(newCandidate);
            System.out.println("Success: Candidate added successfully!");

        } catch (NumberFormatException e) {
            System.err.println("Error: Years of experience must be a number.");
        } catch (RuntimeException e) {

            System.err.println("Error: " + e.getMessage());
        }

    }
    private static void deactivateCandidate(){
        System.out.println("Type the id of the Candidate to delete: ");
        sc.nextLine();
        String id = sc.nextLine();
        candidateService.deactivateCandidate(id);
    }
    private static void showList(){
        System.out.println("----THE LIST OF CANDIDATES----");
        List<Candidate> candidates = candidateRepository.findAll();
        if(candidates.isEmpty()){
            System.out.println("There is no candidate");
        }
        System.out.println("The amount of candidates: "+candidates.size());
        for(Candidate candidate: candidates){
            System.out.println(candidate.toString());
        }


    }
    private static void processAddCV(){
        System.out.println("Enter CV ID:");
        sc.nextLine();
        String cvId = sc.nextLine();

        System.out.println("Enter Candidate ID:");
        String candidateId = sc.nextLine();



        System.out.println("Enter skills (comma separated, e.g. Java,SQL):");
        String rawSkills = sc.nextLine();
        List<String> skills = Arrays.stream(rawSkills.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());


        Level level = null;



        CVStatus status = null;




        CV cv = new CV(cvId, candidateId, skills, level, status);



        System.out.println("-----------------------------");
        System.out.println("CV Created Successfully:");
        System.out.println(cv.toString());
    }
}