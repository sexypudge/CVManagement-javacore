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

        candidateService.addCandidate(candidate);
        System.out.println("Candidate created successfully");




        // TODO:
        // 1. init services
        // 2. show menu
        // 3. read input
        // 4. call service
    }
}