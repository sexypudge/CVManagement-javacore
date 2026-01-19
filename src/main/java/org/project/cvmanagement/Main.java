package org.project.cvmanagement;

import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.enums.CandidateStatus;
import org.project.cvmanagement.enums.Level;
import org.project.cvmanagement.exception.*;
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
                "6. List ACTIVE Candidates\n"+
                "7. Exit\n");
        sc.nextLine();
        System.out.println("<-------------------------------->");
        System.out.println("Choose a number from 1-7:");
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
                listACTIVECandidate();
                break;
            case "7":
                showMenu();
                break;
            default:
                System.out.println("Choose a number from 1-7");
        }

    }
    public void showCVMenu(){
        System.out.println("---WELCOME TO CV SERVICES---");
        System.out.println("1. Add a CV\n" +
                "2. Delete a CV\n" +
                "3. Show CVs list\n" +
                "4. Update CVs\n"+
                "5. Exit\n");
        System.out.println("<---------------------------->");
        System.out.println("Choose a number from 1-5:");
        sc.nextLine();
        String answer = sc.nextLine();
        switch (answer){
            case "1":
                processAddCV();
                break;
            case "2":
                deleteCVs();
                break;
            case "3":
                showCVList();
                break;
            case "4":
                updateCVs();
                break;
            case "5":
                showMenu();
                break;
            default:
                System.out.println("Choose a number from 1-5:");
        }

    }
    public void showJobMenu(){
        System.out.println("---WELCOME TO JOB SERVICES---");
        System.out.println("1. Add a job\n" +
                "2. Delete a Job\n" +
                "3. Show Jobs list\n" +
                "4. Update requirements\n"+
                "5. Exit\n");
        System.out.println("<----------------------------->");
        System.out.println("Choose a number from 1-5:");
        sc.nextLine();
        String answer = sc.nextLine();

        switch (answer){
            case "1":
                processAddJobPosition();
                break;
            case "2":
                deleteJob();
                break;
            case "3":
                showJobList();
                break;
            case "4":
                updateJob();
                break;
            case "5":
                showMenu();
                break;
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
        List<Candidate> candidate = candidateRepository.findAll();
        System.out.println(candidate);
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
    private static void findCandidate(){
        System.out.println("Type in your candidate's name or id: ");


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
                Optional<Candidate> candidate = candidateRepository.findById(candidateId);
                if(candidate.get().getStatus().equals(CandidateStatus.INACTIVE)){
                    throw new CandidateInvalidException(candidateId);
                }
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
    private static void listACTIVECandidate(){
        System.out.println("Here is ACTIVE Candidates list: ");
        List<Candidate> candidates = candidateService.searchByStatus();
        System.out.println(candidates);

    }
    private static void deleteCVs(){
        System.out.println("Type in the CV's id to delete:");
        List<CV> cv = cvRespository.findAll();
        System.out.println(cv);
        String cvId=sc.nextLine();
        cvService.deleteCV(cvId);

    }
    private static void updateCVs(){
        System.out.println("Type in your CV's id :");
        String cvId = sc.nextLine();
        boolean existed = candidateRepository.findById(cvId).isPresent();
        if(!existed){
            throw new CVNotFoundException("Your cv is not existed!!");
        }
        System.out.println("What part you want to update?:");
        System.out.print("1. Skills "+"  "+" 2.Level");
        int answer = sc.nextInt();
        Optional<CV> cv = cvRespository.findById(cvId);
        switch (answer){
            case 1:
                sc.nextLine();
                System.out.println("Your new skills list:");
                String rawSkills = sc.nextLine();
                List<String> skills = Arrays.stream(rawSkills.split(",")).collect(Collectors.toList());
                cv.get().setSkills(skills);
                System.out.println("Successfully updated!!!");

                break;
            case 2:
                System.out.println("Choose your new Level:");
                System.out.println("1. INTERN    2.FRESHER    3.JUNIOR    4.MIDDLE    5.SENIOR");
                System.out.println("<---------------------------->");
                System.out.println("Type in here: ");
                int trLoi = sc.nextInt();
                switch (trLoi){
                    case 1:
                        cv.get().setLevel(Level.INTERN);
                        System.out.println("Successfully updated!!!");
                        break;
                    case 2:
                        cv.get().setLevel(Level.FRESHER);
                        System.out.println("Successfully updated!!!");
                        break;
                    case 3:
                        cv.get().setLevel(Level.JUNIOR);
                        System.out.println("Successfully updated!!!");
                        break;
                    case 4:
                        cv.get().setLevel(Level.MIDDLE);
                        System.out.println("Successfully updated!!!");
                        break;
                    case 5:
                        cv.get().setLevel(Level.SENIOR);
                        System.out.println("Successfully updated!!!");
                        break;
                    default:
                        System.out.println("Choose a number from 1-5");
                }

                break;
            default:
                System.out.println("Choose a number 1 or 2");
        }

    }
    private static void deleteJob(){
        System.out.println("Type in the job's id to delete:");
        List<Job> job = jobRepository.findAll();
        System.out.println(job);
        System.out.println("<--------------------------------->");
        System.out.println("Type in job's id here:");
        String jobId= sc.nextLine();
        jobRepository.deleteById(jobId);
        System.out.println("Successfully deleted!!!");

    }
    private static void showJobList(){
        System.out.println("HERE IS THE JOBS LIST: ");
        List<Job> job = jobRepository.findAll();
        System.out.println(job);
    }
    private static void updateJob(){
        System.out.println("Choose job's id:");
        List<Job> jobList = jobRepository.findAll();
        System.out.println(jobList);
        System.out.println("<--------------------------------->");
        System.out.println("Type id in here: ");
        String jobId =sc.nextLine();
        boolean existed = jobRepository.findById(jobId).isPresent();
        if(!existed){
            throw new JobNotFoundException(jobId);
        }
        System.out.println("Choose one field to update: ");
        System.out.println("1.Title    2.Level    3.Skills");
        System.out.println("<--------------------------------->");
        System.out.println("Type in here: ");
        int answer = sc.nextInt();
        Optional<Job> job = jobRepository.findById(jobId);
        switch (answer){
            case 1:
                sc.nextLine();
                System.out.println("Your new title:");
                String newTitle = sc.nextLine();
                job.get().setTitle(newTitle);
                System.out.println("Successfully updated!!!");
                break;
            case 2:
                System.out.println("Your new level:");
                System.out.print("1. INTERN   ");
                System.out.print("2. FRESHER   ");
                System.out.print("3. JUNIOR   ");
                System.out.print("4. MIDDLE   ");
                System.out.print("5. SENIOR   \n");
                System.out.println("<----------------------->");
                System.out.println("Type a number from 1-5:");
                int newLevel = sc.nextInt();

                switch (answer){
                    case 1:
                        job.get().setLevel(Level.INTERN);
                        System.out.println("Successfully updated!!!");
                        break;
                    case 2:
                        job.get().setLevel(Level.FRESHER);
                        System.out.println("Successfully updated!!!");
                        break;
                    case 3:
                        job.get().setLevel(Level.JUNIOR);
                        System.out.println("Successfully updated!!!");
                        break;
                    case 4:
                        job.get().setLevel(Level.MIDDLE);
                        System.out.println("Successfully updated!!!");
                        break;
                    case 5:
                        job.get().setLevel(Level.SENIOR);
                        System.out.println("Successfully updated!!!");
                        break;
                    default:
                        System.out.println("Choose a number from 1-5");
                }


                break;
            case 3:
                System.out.println("Your new skills:");
                String newSkills = sc.nextLine();
                Set<String> skills = Arrays.stream(newSkills.split(",")).collect(Collectors.toSet());
                job.get().setRequiredLevel(skills);
                System.out.println("Successfully updated!!!");
                break;
        }

    }



}