package com.tinqin.library.reporting.apiadapter.mappers;

import com.tinqin.library.reporting.api.operations.getrecord.GetRecordInput;
import com.tinqin.library.reporting.apiadapter.config.MapstructContextConfig;
import com.tinqin.library.reporting.apiadapter.operations.getrecord.ReportingGetRecordInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MapstructContextConfig.class)
class GetRecordMapperTest {

    @Autowired
    private GetRecordMapper mapper;

    private final String RECORD_ID = "00000000-0000-0000-0000-000000000000";


    @Test
    void toReportingConvertsCorrect(){
        GetRecordInput input = GetRecordInput
                .builder()
                .recordId(RECORD_ID)
                .build();

        ReportingGetRecordInput reporting = mapper.toReporting(input);

        assertEquals(RECORD_ID, reporting.getRecordId());

    }
}