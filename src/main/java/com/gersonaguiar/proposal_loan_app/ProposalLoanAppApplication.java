package com.gersonaguiar.proposal_loan_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProposalLoanAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProposalLoanAppApplication.class, args);
    }

}
