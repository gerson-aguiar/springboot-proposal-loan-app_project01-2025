package com.gersonaguiar.proposal_loan_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Proposal {

    @Id
    private Long id;

    private Double expectedValue;

    private int paymentDeadline;

    private Boolean confirmed;

    private boolean integrated;

    private String note;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
