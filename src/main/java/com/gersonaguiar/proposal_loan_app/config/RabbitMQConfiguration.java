package com.gersonaguiar.proposal_loan_app.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Queue makeQueueProposalPendindMsCreditAnalysis() {
        return QueueBuilder.durable("proposal-pending.ms-credit-analysis").build();
    }

    @Bean
    public Queue makeQueueProposalPendindMsNotification() {
        return QueueBuilder.durable("proposal-pending.ms-notification").build();
    }

    @Bean
    public Queue makeQueueProposalCompletedMsProposal() {
        return QueueBuilder.durable("proposal-completed.ms-proposal").build();
    }

    @Bean
    public Queue makeQueueProposalCompletedMsNotification() {
        return QueueBuilder.durable("proposal-completed.ms-notification").build();
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initialize(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }
}