package com.tinqin.library.reporting.apiadapter.mappers;

import com.tinqin.library.reporting.api.operations.getevent.GetEventInput;
import com.tinqin.library.reporting.api.operations.getevent.GetEventOutput;
import com.tinqin.library.reporting.apiadapter.operations.getevent.ReportingGetEventInput;
import com.tinqin.library.reporting.apiadapter.operations.getevent.ReportingGetEventOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ModelMapper.class)
public interface GetEventMapper {

    GetEventMapper INSTANCE = Mappers.getMapper(GetEventMapper.class);

    ReportingGetEventInput toReporting(GetEventInput input);

    GetEventOutput toApiResult(ReportingGetEventOutput output);
}
