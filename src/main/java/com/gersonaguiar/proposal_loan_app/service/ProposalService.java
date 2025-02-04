package com.gersonaguiar.proposal_loan_app.service;

import com.gersonaguiar.proposal_loan_app.dto.ProposalRequestDto;
import com.gersonaguiar.proposal_loan_app.dto.ProposalResponseDto;
import com.gersonaguiar.proposal_loan_app.entity.Proposal;
import com.gersonaguiar.proposal_loan_app.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalService {

    private ProposalRepository proposalRepository;

    @Autowired
    public ProposalService(ProposalRepository proposalRepository){
        this.proposalRepository = proposalRepository;
    }

    public ProposalResponseDto create(ProposalRequestDto requestDto){

        Proposal proposal = new Proposal();
        proposalRepository.save(proposal);
        return null;
    }
}
