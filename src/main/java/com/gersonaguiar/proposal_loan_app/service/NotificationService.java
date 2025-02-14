package com.gersonaguiar.proposal_loan_app.service;

import com.gersonaguiar.proposal_loan_app.dto.ProposalResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationService {

    private RabbitTemplate rabbitTemplate;

    public void sendNotification(
            ProposalResponseDto proposalResponseDto,
            String exchange
    ) {
        rabbitTemplate.convertAndSend(exchange,"", proposalResponseDto);
    }
}
