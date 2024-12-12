package com.tinqin.library.reporting.apiadapter.mappers;

import com.tinqin.library.reporting.api.operations.createrecord.CreateRecordInput;
import com.tinqin.library.reporting.api.operations.createrecord.CreateRecordOutput;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.ReportingCreateRecordInput;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.ReportingCreateRecordOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface CreateRecordMapper {

    CreateRecordMapper INSTANCE = Mappers.getMapper(CreateRecordMapper.class);

    ReportingCreateRecordInput toReporting(CreateRecordInput createRecordInput);

    CreateRecordOutput toApiResult(ReportingCreateRecordOutput output);
}
