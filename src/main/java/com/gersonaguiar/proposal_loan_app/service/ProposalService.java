package com.gersonaguiar.proposal_loan_app.service;

import com.gersonaguiar.proposal_loan_app.dto.ProposalRequestDto;
import com.gersonaguiar.proposal_loan_app.dto.ProposalResponseDto;
import com.gersonaguiar.proposal_loan_app.entity.Proposal;
import com.gersonaguiar.proposal_loan_app.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProposalService {

    private final NotificationService notificationService;
    private ProposalRepository proposalRepository;
    private String rabbitmqProposalPendingExchange;

    @Autowired
    public ProposalService(
            ProposalRepository proposalRepository,
            NotificationService notificationService,
            @Value("${rabbitmq.proposal.pending.exchange}") String rabbitmqProposalPendingExchange
    ) {
        this.proposalRepository = proposalRepository;
        this.notificationService = notificationService;
        this.rabbitmqProposalPendingExchange = rabbitmqProposalPendingExchange;
    }


    public ProposalResponseDto create(ProposalRequestDto requestDto) {

        Proposal proposal = new Proposal();
        proposal.setNote(requestDto.getNote());

        proposalRepository.save(proposal);

        ProposalResponseDto responseDto = new ProposalResponseDto();
        responseDto.setAnnotation(proposal.getNote());

        try {
            notificationRabbitMQ(responseDto);
            proposal.setIntegrated(true);
            proposalRepository.save(proposal);

        } catch (RuntimeException e) {
            proposal.setIntegrated(false);
            proposalRepository.save(proposal);
        }

        return null;
    }

    private void notificationRabbitMQ(ProposalResponseDto responseDto) {
        notificationService.sendNotification(responseDto, rabbitmqProposalPendingExchange);
    }
}
