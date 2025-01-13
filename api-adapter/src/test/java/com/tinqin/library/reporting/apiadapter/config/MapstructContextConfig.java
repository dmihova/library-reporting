package com.tinqin.library.reporting.apiadapter.config;

import com.tinqin.library.reporting.apiadapter.mappers.GetRecordMapper;
import com.tinqin.library.reporting.apiadapter.mappers.ModelMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MapstructContextConfig {

    @Bean
    public GetRecordMapper getRecordMapper() {
        return Mappers.getMapper(GetRecordMapper.class);
    }

    @Bean
    public ModelMapper modelMapper() {
        return Mappers.getMapper(ModelMapper.class);
    }
}