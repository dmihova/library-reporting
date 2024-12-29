package com.tinqin.library.reporting.kafka;

import com.tinqin.library.reporting.apiadapter.operations.createrecord.CreateRecord;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.ReportingCreateRecordInput;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumerListener {
  public static final String TOPIC_NAME = "reporting-topic";
  public static final String TOPIC_NAME_AUTHOR =  "reporting-topic-author";
  public static final String TOPIC_NAME_BOOK =  "reporting-topic-book";

  private final CreateRecord createRecord;

  @KafkaListener(topics = {TOPIC_NAME_AUTHOR}, groupId = "reporting_group_id")
  public void listener(String message){
    ReportingCreateRecordInput input = ReportingCreateRecordInput
        .builder()
        .objectId(message)
        .objectType("author")
        .build();

    createRecord.process(input);

  }
  @KafkaListener(topics = {TOPIC_NAME_BOOK}, groupId = "reporting_group_id")
  public void listenerBook(String message){
    ReportingCreateRecordInput input = ReportingCreateRecordInput
            .builder()
            .objectId(message)
            .objectType("book")
            .build();

    createRecord.process(input);

  }
}
