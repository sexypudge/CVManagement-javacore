package org.project.cvmanagement;

import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.repository.impl.CandidateRepositoryImpl;
import org.project.cvmanagement.service.CandidateService;
import org.project.cvmanagement.service.impl.CandidateServiceImpl;

import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);

    static CandidateRepository candidateRepo = new CandidateRepositoryImpl();
    static CandidateService candidateService = new CandidateServiceImpl(candidateRepo);

    public static void main(String[] args) {
        Main main = new Main();
        main.showMenu();
        // TODO:
        // 1. init services
        // 2. show menu
        // 3. read input
        // 4. call service
    }

    public void showMenu() {
        while (true) {
            System.out.println("======= CV Management system =======");
            System.out.println("1: Add candidate");
            System.out.println("2: Deactivate Candidate");
            System.out.println("3: Update information ");
            System.out.println("Please enter your choice : ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    handleAddCandidate();
                    break;
                case "2":

                    break;
                case "3":
                    handleUpdateCandidate();
                    break;
            }
        }
    }

    private void handleAddCandidate() {
        try {
            System.out.println("====== Add new candidate ======");
            System.out.println("Enter Candidate id: ");
            String id = sc.nextLine();
            System.out.println("Enter name: ");
            String fullName = sc.nextLine();
            System.out.println("Enter email: ");
            String email = sc.nextLine();
            System.out.println("Enter year of experience: ");
            int yearExperience = sc.nextInt();

            Candidate candidate = new Candidate(id, fullName, email, yearExperience, null);
            candidateService.addCandidate(candidate);

            System.out.println("Success: Candidate created");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
  /*
    private void handleDeactivateCandidate() {
        try {
            System.out.println("====== Deactivate candidate======");
            System.out.print("Enter id to deactivate: ");
            String id = sc.nextLine();

            candidateService.deactivateCandidate(id);

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

   */
  private void handleUpdateCandidate() {
      try {
          System.out.println("====== Update Candidate ======");
          System.out.print("Enter id to update: ");
          String id = sc.nextLine();

          System.out.print("full name: ");
          String name = sc.nextLine();
          System.out.print("Update email: ");
          String email = sc.nextLine();
          System.out.print("update years of experience: ");
          int yoe = Integer.parseInt(sc.nextLine());


          Candidate updateInfo = new Candidate(id, name, email, yoe, null);

          candidateService.updateCandidate(updateInfo);

      } catch (Exception e) {
          System.err.println("error: " + e.getMessage());
      }
  }
}