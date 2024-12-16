package com.cadman.cooperative.app.controller;



import com.cadman.cooperative.app.model.LoanApplication;
import com.cadman.cooperative.app.model.Member;
import com.cadman.cooperative.app.repository.LoanApplicationRepository;
import com.cadman.cooperative.app.repository.MemberRepository;
import com.cadman.cooperative.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final LoanApplicationRepository loanApplicationRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String memberId, @RequestParam String password) {
        Optional<Member> member = memberService.login(memberId, password);
        return member.isPresent() ? ResponseEntity.ok("Login Successful") :
                ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/{id}/loan")
    public ResponseEntity<String> applyForLoan(@PathVariable Long id, @RequestBody LoanApplication loanApplication) {
        return memberRepository.findById(id).map(member -> {
            loanApplication.setMember(member);
            loanApplicationRepository.save(loanApplication);
            return ResponseEntity.ok("Loan Application Submitted");
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/loans")
    public ResponseEntity<Object> getLoans(@PathVariable Long id) {
        return memberRepository.findById(id)
                .map(member -> ResponseEntity.ok(member.getLoanApplications()))
                .orElse(ResponseEntity.notFound().build());
    }
}