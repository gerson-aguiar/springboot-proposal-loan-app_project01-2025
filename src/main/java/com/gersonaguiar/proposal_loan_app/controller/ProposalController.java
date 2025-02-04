package com.gersonaguiar.proposal_loan_app.controller;

import com.gersonaguiar.proposal_loan_app.dto.ProposalRequestDto;
import com.gersonaguiar.proposal_loan_app.dto.ProposalResponseDto;
import com.gersonaguiar.proposal_loan_app.service.ProposalService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.sampled.Port;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

    private ProposalService proposalService;

    @Autowired
    public ProposalController(ProposalService proposalService){
        this.proposalService = proposalService;
    }

    @PostMapping
    public ResponseEntity<ProposalResponseDto> create(@RequestBody ProposalRequestDto requestDto){
        ProposalResponseDto proposalResponseDto = proposalService.create(requestDto);
        return ResponseEntity.ok(proposalResponseDto);
    }
}
