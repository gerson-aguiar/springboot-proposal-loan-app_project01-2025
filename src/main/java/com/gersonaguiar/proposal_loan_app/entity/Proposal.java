package com.gersonaguiar.proposal_loan_app.entity;

import jakarta.persistence.*;

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "proposal_number")
    private Double expectedValue;

    @Column(name = "payment_deadline")
    private int paymentDeadline;

    private Boolean confirmed;

    private boolean integrated;

    private String note;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
