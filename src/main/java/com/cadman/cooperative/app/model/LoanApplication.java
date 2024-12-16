package com.cadman.cooperative.app.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    private double amount;
    private String purpose;
    private String status = "PENDING"; //PENDING, APPROVED, REJECTED
}
