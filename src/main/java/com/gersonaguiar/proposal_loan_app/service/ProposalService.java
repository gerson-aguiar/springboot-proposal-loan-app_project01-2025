package com.gersonaguiar.proposal_loan_app.service;

import com.gersonaguiar.proposal_loan_app.dto.ProposalRequestDto;
import com.gersonaguiar.proposal_loan_app.dto.ProposalResponseDto;
import com.gersonaguiar.proposal_loan_app.entity.Proposal;
import com.gersonaguiar.proposal_loan_app.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalService {

    private final NotificationService notificationService;
    private ProposalRepository proposalRepository;

    @Autowired
    public ProposalService(ProposalRepository proposalRepository, NotificationService notificationService){
        this.proposalRepository = proposalRepository;
        this.notificationService = notificationService;
    }

    public ProposalResponseDto create(ProposalRequestDto requestDto){

        Proposal proposal = new Proposal();
        proposal.setNote(requestDto.getNote());

        proposalRepository.save(proposal);

        ProposalResponseDto responseDto = new ProposalResponseDto();
        responseDto.setAnnotation(proposal.getNote());

        notificationService.sendNotification(responseDto, "proposal-pending.exchange");

        return null;
    }
}
