package com.chouxiaozi.mailserver.config;

import com.chouxiaozi.vhr.model.MailConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue employeeQueue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }

    @Bean
    public DirectExchange employeeExchange(){
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME);
    }

    @Bean
    public Binding employeeBinding(){
        return BindingBuilder.bind(employeeQueue()).to(employeeExchange()).with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }
}
