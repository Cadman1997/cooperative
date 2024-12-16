package com.cadman.cooperative.app.repository;

import com.cadman.cooperative.app.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
}
