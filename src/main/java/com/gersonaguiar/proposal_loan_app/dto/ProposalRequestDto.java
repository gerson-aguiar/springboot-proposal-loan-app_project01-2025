package com.gersonaguiar.proposal_loan_app.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProposalRequestDto {

    private String name;

    private String lastName;

    private String phoneNumber;

    private String cpf;

    private String renda;

    private String requestValue;

    private String deadlinePayment;
}
