package com.tinqin.library.reporting.kafkaexport;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tinqin.library.reporting.kafkaexport.TopicConfig.TOPIC_NAME;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

  private final KafkaTemplate<String,String> kafkaTemplate;

  public void createAuthorRecord(UUID authorId){

    kafkaTemplate.send(TOPIC_NAME, authorId.toString());
  }
}
