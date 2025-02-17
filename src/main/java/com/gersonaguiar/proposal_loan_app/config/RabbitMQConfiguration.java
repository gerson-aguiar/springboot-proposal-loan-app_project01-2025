package com.gersonaguiar.proposal_loan_app.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Value("${rabbitmq.proposal.pending.exchange}")
    private String rabbitmqProposalPendingExchange;

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

    @Bean
    public FanoutExchange fanoutExchangeProposalPending() {
        return ExchangeBuilder.fanoutExchange(rabbitmqProposalPendingExchange).build();
    }

    @Bean
    public Binding bindingProposalPendindMsCreditAnalysis() {
        return BindingBuilder.bind(makeQueueProposalPendindMsCreditAnalysis()).to(fanoutExchangeProposalPending());
    }

    @Bean
    public FanoutExchange fanoutExchangeProposalNotification() {
        return ExchangeBuilder.fanoutExchange(rabbitmqProposalPendingExchange).build();
    }

    @Bean
    public Binding bindingProposalPendindMsNotification() {
        return BindingBuilder.bind(makeQueueProposalPendindMsNotification()).to(fanoutExchangeProposalNotification());
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());

        return rabbitTemplate;
    }
}