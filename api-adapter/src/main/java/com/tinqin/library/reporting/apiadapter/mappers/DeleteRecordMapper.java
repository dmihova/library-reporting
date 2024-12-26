package com.tinqin.library.reporting.apiadapter.mappers;

import com.tinqin.library.reporting.api.operations.deleterecord.DeleteRecordInput;
import com.tinqin.library.reporting.api.operations.deleterecord.DeleteRecordOutput;
import com.tinqin.library.reporting.apiadapter.operations.deleterecord.ReportingDeleteRecordInput;
import com.tinqin.library.reporting.apiadapter.operations.deleterecord.ReportingDeleteRecordOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface DeleteRecordMapper {

    DeleteRecordMapper INSTANCE = Mappers.getMapper(DeleteRecordMapper.class);

    ReportingDeleteRecordInput toReporting(DeleteRecordInput deleteRecordInput);

    DeleteRecordOutput toApiResult(ReportingDeleteRecordOutput output);
}
