package com.gersonaguiar.proposal_loan_app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;

    private String last_name;

    private String cpf;

    private String phone_number;

    private Double income;

    @OneToOne(mappedBy = "user")
    private Proposal proposal;
}
