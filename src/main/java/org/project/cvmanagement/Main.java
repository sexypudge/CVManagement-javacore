package org.project.cvmanagement;

import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.enums.CandidateStatus;
import org.project.cvmanagement.enums.Level;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.exception.DuplicateCandidateException;
import org.project.cvmanagement.repository.CVRespository;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.repository.JobRepository;
import org.project.cvmanagement.repository.impl.CVRespositoryImpl;
import org.project.cvmanagement.repository.impl.CandidateRepositoryImpl;
import org.project.cvmanagement.repository.impl.JobRepositoryImpl;
import org.project.cvmanagement.service.CVService;
import org.project.cvmanagement.service.CandidateService;
import org.project.cvmanagement.service.JobService;
import org.project.cvmanagement.service.impl.CVServiceImpl;
import org.project.cvmanagement.service.impl.CandidateServiceImpl;
import org.project.cvmanagement.domain.CV;
import org.project.cvmanagement.enums.CVStatus;
import org.project.cvmanagement.domain.Job;
import org.project.cvmanagement.service.impl.JobServiceImpl;


import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static CandidateRepository candidateRepository = new CandidateRepositoryImpl();
    private static CandidateService candidateService = new CandidateServiceImpl(candidateRepository);

    private static CandidateStatus candidateStatus;

    private static CVRespository cvRespository = new CVRespositoryImpl();
    private static CVService cvService = new CVServiceImpl(cvRespository);
    private static JobRepository jobRepository=new JobRepositoryImpl();
    private static JobService jobService = new JobServiceImpl(jobRepository);





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
                    "5. Show list CVs\n" +
                    "6. Add job position\n" +
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
                    showCVList();
                    break;
                case 6:
                    processAddJobPosition();
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
            System.out.println(newCandidate.toString());

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
            System.out.println("The amount of candidates: "+candidates.size());
        }else {
            System.out.println("The amount of candidates: "+candidates.size());
            for(Candidate candidate: candidates){
                System.out.println(candidate.toString());
            }
        }



    }
    private static void showCVList(){
        System.out.println("---THE LISTS OF CV---");
        List<CV> cvs = cvRespository.findAll();
        if(cvs.isEmpty()){
            System.out.println("There is no cv yet");
            System.out.println("The amount of candidates: "+cvs.size());
        }else {
            System.out.println("The amount of candidates: "+cvs.size());
            for(CV cv: cvs){
                System.out.println(cv.toString());
            }
        }
    }

    private static void processAddCV(){
        System.out.println("Enter CV ID:");
        sc.nextLine();
        String cvId = sc.nextLine();
        String candidateId;

        while(true){
            System.out.println("Enter Candidate ID:");
            candidateId = sc.nextLine();
            boolean existed = candidateRepository.findById(candidateId).isPresent();
            if (existed) {
                break;
            }else {System.out.println(candidateId+" id is not existed, please try again!");}
        }



        System.out.println("Enter skills (comma separated, e.g. Java,SQL):");
        String rawSkills = sc.nextLine();
        List<String> skills = Arrays.stream(rawSkills.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        Level level = null;
        CVStatus status = null;

        CV newcv = new CV(cvId, candidateId, skills, level, status);
        cvService.createCV(newcv);

        System.out.println("-----------------------------");
        System.out.println("CV Created Successfully:");
        System.out.println(newcv.toString());
    }
    private static void processAddJobPosition(){
        System.out.println("Enter job id: ");
        sc.nextLine();
        String jobId = sc.nextLine();
        System.out.println("Enter title of the job: ");
        String jobTitle = sc.nextLine();
        System.out.println("Choose a level for the job: ");
        System.out.print("1. INTERN   ");
        System.out.print("2. FRESHER   ");
        System.out.print("3. JUNIOR   ");
        System.out.print("4. MIDDLE   ");
        System.out.print("5. SENIOR   \n");

        Level level=Level.INTERN;

        boolean completed = false;
        while (true){
            System.out.println("Type in a number: ");
            String answer = sc.nextLine();
            switch (answer){
                case "1":
                    completed=true;
                    break;
                case "2":
                    level=Level.FRESHER;
                    completed=true;
                    break;
                case "3":
                    level=Level.JUNIOR;
                    completed=true;
                    break;
                case "4":
                    level=Level.MIDDLE;
                    completed=true;
                    break;
                case "5":
                    level=Level.SENIOR;
                    completed=true;
                    break;
                default:
                    System.out.println("Choose a number from 1-5");
            }
            if (completed){
                break;
            }
        }

        System.out.println("Type in skills that are required: (comma separated, e.g. Java,SQL): ");
        String rawSkills = sc.nextLine();
        Set<String> requiredSkills = Arrays.stream(rawSkills.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());

        Job job = new Job(jobId,jobTitle,level,requiredSkills);
        jobService.addJob(job);

        System.out.println("The job has successfully added!!");
        System.out.println(job.toString());


    }
    private static void findCandidate(){
        System.out.println("Type in your candidate's name or id: ");
        String candidateIDN = sc.nextLine();


    }

}