package org.project.cvmanagement;

import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.enums.CandidateStatus;
import org.project.cvmanagement.enums.Level;
import org.project.cvmanagement.exception.BusinessException;
import org.project.cvmanagement.exception.CandidateNotFoundException;
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


import javax.security.auth.DestroyFailedException;
import java.sql.SQLOutput;
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


            while (true){
                System.out.println("1. Candidate Service\n" +
                        "2. CV Service\n" +
                        "3. Job Service\n" +
                        "4. Exit\n");
                System.out.println("<----------------------------->");
                System.out.println("Please choose a number: ");
                int answer;
                try{
                    answer=sc.nextInt();
                    switch (answer){
                        case 1:
                            showCandidateMenu();
                            break;
                        case 2:
                            showCVMenu();
                            break;
                        case 3:
                            showJobMenu();
                            break;
                        case 4:
                            System.out.println("Good bye!!");
                            System.exit(0);
                        default:
                            System.out.println("Choose a number from 1-4");
                    }
                }catch (NumberFormatException e){
                    System.out.println("Invalid number! Choose a number from 1-4");

                }
            }

    }

    public void showCandidateMenu(){
        System.out.println("---WELCOME TO CANDIDATE SERVICES---");
        System.out.println("1. Add a candidate\n" +
                "2. Deactivate candidate\n" +
                "3. Update candidate information\n"+
                "4. Show candidates list\n" +
                "5. Find candidate\n"+
                "6. Exit\n");
        sc.nextLine();
        System.out.println("<-------------------------------->");
        System.out.println("Choose a number from 1-6:");
        String answer = sc.nextLine();
        switch (answer){
            case "1":
                processAddCandidate();
                break;
            case "2":
                deactivateCandidate();
                break;
            case "3":
                updateCandidate();
                break;
            case "4":
                showList();
                break;
            case "5":
                findCandidate();
                break;
            case "6":
                showMenu();
                break;
            default:
                System.out.println("Choose a number from 1-6");
        }

    }
    public void showCVMenu(){
        System.out.println("---WELCOME TO CV SERVICES---");
        System.out.println("1. Add a CV\n" +
                "2. Delete a CV\n" +
                "3. Show CVs list\n" +
                "4. Exit\n");
        System.out.println("<---------------------------->");
        System.out.println("Choose a number from 1-4:");
        sc.nextLine();
        String answer = sc.nextLine();
        switch (answer){
            case "1":
                processAddCV();
                break;
            case "2":
                break;
            case "3":
                showCVList();
                break;
            case "4":
                showMenu();
                break;
            default:
                System.out.println("Choose a number from 1-4:");
        }

    }
    public void showJobMenu(){
        System.out.println("---WELCOME TO JOB SERVICES---");
        System.out.println("1. Add a job\n" +
                "2. Delete a Job\n" +
                "3. Show Jobs list\n" +
                "4. Exit\n");
        System.out.println("<----------------------------->");
        System.out.println("Choose a number from 1-4:");
        sc.nextLine();
        String answer = sc.nextLine();

        switch (answer){
            case "1":
                processAddJobPosition();
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                showMenu();
            default:
                System.out.println("Choose a number from 1-4");
        }

    }
    private static void processAddCandidate(){
        System.out.println("--- ADD NEW CANDIDATE ---");
        try {
            System.out.print("Enter ID: ");

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
    private static void updateCandidate(){
        System.out.println("Type in the candidate id:");
        String candidateId= sc.nextLine();
        boolean existed = candidateRepository.findById(candidateId).isPresent();
        if(!existed){
            throw new CandidateNotFoundException("Your candidate is not existed");
        }
        System.out.println("Change infomation of :");
        System.out.print("1. Full Name\n"+
                "2. Email\n"+
                "3. Year of Experience\n"+
                "4. Candidate's Status\n");

        System.out.println("<--------------------------->");
        System.out.println("Choose a number from 1-4:");
        String answer = sc.nextLine();
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);

        switch (answer){
            case "1":
                System.out.println("Type in the new name:");
                String newname= sc.nextLine();
                candidate.get().setFullName(newname);
                System.out.println("Successfully changed!!");
                break;
            case "2":
                System.out.println("Type in the new email:");
                String newemail= sc.nextLine();
                candidate.get().setEmail(newemail);
                System.out.println("Successfully changed!!");
                break;
            case "3":
                System.out.println("Type in the new value of YOE:");
                int newyoe= sc.nextInt();
                candidate.get().setYearsOfExperience(newyoe);
                System.out.println("Successfully changed!!");
                break;
            case "4":
                System.out.println("Type in the new status");
                System.out.print("1. ACTIVE\n"+
                        "2. INACTIVE\n");
                int ans = sc.nextInt();
                switch (ans){
                    case 1:
                        candidate.get().setStatus(CandidateStatus.ACTIVE);
                        System.out.println("Successfully changed!!");
                        break;
                    case 2:
                        candidate.get().setStatus(CandidateStatus.INACTIVE);
                        System.out.println("Successfully changed!!");
                        break;
                    default:
                        System.out.println("Choose a number 1/2");
                }

                break;
            default:
                System.out.println("Choose a number from 1-4");
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
        String cvId;

        while (true){
            System.out.println("Enter CV ID:");
            sc.nextLine();
            cvId = sc.nextLine();
            boolean isExisted = cvRespository.findById(cvId).isPresent();
            if(!isExisted){
                break;
            }else {
                System.out.println("This CV id is already taken! Try again");
            }

        }

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
        sc.nextLine();

        boolean completed=false;
        while(true){
            System.out.print("1. Search By Name"+"    "+" 2. Search By ID"+"\n");
            System.out.println("Type here -->");
            String answer=sc.nextLine();
            switch (answer){
                case "1":
                    System.out.println("Type in candidate's full name:");
                    String candidateName=sc.nextLine();
                    List<Candidate>candidates=candidateService.searchByName(candidateName);
                    candidates.forEach(c-> System.out.println(c));
                    completed=true;
                    break;
                case "2":
                    System.out.println("Type in candidate's id:");
                    String candidateId= sc.nextLine();
                    System.out.println(candidateService.getById(candidateId).toString());
                    completed=true;
                    break;
                default:
                    System.out.println("Choose a number 1 or 2");
            }
            if(completed){
                break;
            }
        }
    }

}