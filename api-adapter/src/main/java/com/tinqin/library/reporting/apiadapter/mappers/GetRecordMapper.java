package com.tinqin.library.reporting.apiadapter.mappers;

import com.tinqin.library.reporting.api.operations.getrecord.GetRecordInput;
import com.tinqin.library.reporting.api.operations.getrecord.GetRecordOutput;
import com.tinqin.library.reporting.apiadapter.operations.getrecord.ReportingGetRecordInput;
import com.tinqin.library.reporting.apiadapter.operations.getrecord.ReportingGetRecordOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface GetRecordMapper {

    GetRecordMapper INSTANCE = Mappers.getMapper(GetRecordMapper.class);

    ReportingGetRecordInput toReporting(GetRecordInput input);

    GetRecordOutput toApiResult(ReportingGetRecordOutput output);
}
