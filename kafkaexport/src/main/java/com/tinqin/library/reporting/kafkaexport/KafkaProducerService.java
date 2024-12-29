package com.tinqin.library.reporting.kafkaexport;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tinqin.library.reporting.kafkaexport.TopicConfig.*;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

  private final KafkaTemplate<String,String> kafkaTemplate;

  public void createAuthorRecord(UUID id){
    kafkaTemplate.send(TOPIC_NAME_AUTHOR, id.toString());
  }

  public void createBookRecord(UUID id  ){
    kafkaTemplate.send(TOPIC_NAME_BOOK, id.toString() );
  }


}
