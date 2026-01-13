package org.project.cvmanagement;

import org.project.cvmanagement.domain.Candidate;
import org.project.cvmanagement.repository.CandidateRepository;
import org.project.cvmanagement.repository.impl.CandidateRepositoryImpl;
import org.project.cvmanagement.service.CandidateService;
import org.project.cvmanagement.service.impl.CandidateServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        CandidateRepository candidateRepository =
                new CandidateRepositoryImpl();

        CandidateService candidateService =
                new CandidateServiceImpl(candidateRepository);

        Candidate candidate = new Candidate(
                "C001",
                "Nguyen Van A",
                "a@gmail.com",
                2,
                null
        );
        Candidate candidate1 = new Candidate(
                "C002",
                "Nguyen Van A1",
                "asdasd",
                2,
                null
        );

        Candidate candidate2 = new Candidate(
                "C003",
                "Nguyen Van AB",
                "asdasd",
                2,
                null
        );


        candidateService.addCandidate(candidate);

        candidateService.addCandidate(candidate1);


        candidateService.addCandidate(candidate2);




        // TODO:
        // 1. init services
        // 2. show menu
        // 3. read input
        // 4. call service
    }
}