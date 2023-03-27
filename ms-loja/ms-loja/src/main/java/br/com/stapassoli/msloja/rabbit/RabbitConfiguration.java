package br.com.stapassoli.msloja.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    public Queue criarFilaVenda() {
        return QueueBuilder.nonDurable("fila-venda").build();
    }

    @Bean
    public RabbitAdmin configureAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    @Bean
    public ApplicationListener<ApplicationEvent> initalize(RabbitAdmin admin){
        return event -> admin.initialize();
    }

    @Bean
    public Jackson2JsonMessageConverter converter (){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate applyConversor(ConnectionFactory conn, Jackson2JsonMessageConverter converter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(conn);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

}
