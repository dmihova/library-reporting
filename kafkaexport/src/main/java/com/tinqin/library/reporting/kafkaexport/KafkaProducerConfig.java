package com.tinqin.library.reporting.kafkaexport;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.tinqin.library.reporting.kafkaexport.TopicConfig.TOPIC_NAME;

@Configuration
public class KafkaProducerConfig {

  @Bean
  public NewTopic topic() {
    return TopicBuilder
        .name(TOPIC_NAME)
        .build();
  }

}
