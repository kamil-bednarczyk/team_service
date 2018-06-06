package sa.common.config;

import org.axonframework.amqp.eventhandling.spring.SpringAMQPPublisher;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.fanoutExchange("events").build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("events").build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
    }

    @Autowired
    public void configure(AmqpAdmin admin) {
        admin.declareExchange(exchange());
        admin.declareQueue(queue());
        admin.declareBinding(binding());
    }

/*    @Bean
    public SpringAMQPPublisher springAMQPPublisher(){
        SpringAMQPPublisher publisher = new SpringAMQPPublisher(eventStore);
        publisher.setDurable(true);
        publisher.start();
        publisher.setExchange(exchange());
        return publisher;
    }*/
}