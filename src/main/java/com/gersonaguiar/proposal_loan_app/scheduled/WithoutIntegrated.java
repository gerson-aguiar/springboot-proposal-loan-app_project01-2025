package com.gersonaguiar.proposal_loan_app.scheduled;

import com.gersonaguiar.proposal_loan_app.dto.ProposalResponseDto;
import com.gersonaguiar.proposal_loan_app.repository.ProposalRepository;
import com.gersonaguiar.proposal_loan_app.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class WithoutIntegrated {

    private final ProposalRepository proposalRepository;
    private final NotificationService notificationService;
    private final String rabbitmqProposalPendingExchange;

    private final Logger logger = LoggerFactory.getLogger(WithoutIntegrated.class);

    public WithoutIntegrated(
            @Value("${rabbitmq.proposal.pending.exchange}")
            String rabbitmqProposalPendingExchange,
            NotificationService notificationService,
            ProposalRepository proposalRepository
    ) {
        this.rabbitmqProposalPendingExchange = rabbitmqProposalPendingExchange;
        this.notificationService = notificationService;
        this.proposalRepository = proposalRepository;
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void getProposalWithoutIntegrated() {
        proposalRepository.findAllByIntegratedIsFalse().forEach(proposal -> {
            ProposalResponseDto proposalResponseDto = new ProposalResponseDto();
            proposalResponseDto.setAnnotation(proposal.getNote());

            try {
                notificationService.sendNotification(proposalResponseDto, rabbitmqProposalPendingExchange);
                proposal.setIntegrated(true);
                proposalRepository.save(proposal);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        });
    }
}
