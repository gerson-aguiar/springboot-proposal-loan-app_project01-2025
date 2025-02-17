package com.gersonaguiar.proposal_loan_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    private String cpf;

    @Column(name = "phone_number")
    private String phoneNumber;

    private Double income;

    @OneToOne(mappedBy = "user")
//    @JsonBackReference
    private Proposal proposal;
}
