package com.gersonaguiar.proposal_loan_app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private boolean integrated = false;

    private String note;

    @OneToOne
    @JoinColumn(name = "user_id")
//    @JsonManagedReference
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(Double expectedValue) {
        this.expectedValue = expectedValue;
    }

    public int getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(int paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isIntegrated() {
        return integrated;
    }

    public void setIntegrated(boolean integrated) {
        this.integrated = integrated;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
