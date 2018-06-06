package sa.common.config;

import com.mongodb.MongoClient;
import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j2;
import org.axonframework.amqp.eventhandling.DefaultAMQPMessageConverter;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPPublisher;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.MongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Log4j2
public class AxonConfig {

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient("localhost", 27017);
    }

    @Bean
    public MongoTemplate defaultMongoTemplate() {
        return new DefaultMongoTemplate(mongoClient());
    }

    @Bean
    public EventStorageEngine eventStorageEngine() {
        return new MongoEventStorageEngine(defaultMongoTemplate());
    }

   @Bean
    public EventStore eventStore() {
        return new EmbeddedEventStore(eventStorageEngine());
    }


    @Bean
    public SpringAMQPMessageSource newevents(Serializer serializer) {
        return new SpringAMQPMessageSource(new DefaultAMQPMessageConverter(serializer)) {
            @RabbitListener(queues = "events")
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                log.info("Received message: " + message.toString());
                super.onMessage(message, channel);
            }
        };
    }
}