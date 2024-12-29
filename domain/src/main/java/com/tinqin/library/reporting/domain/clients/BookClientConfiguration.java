package com.tinqin.library.reporting.domain.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class BookClientConfiguration {
    private final ObjectMapper objectMapper;

    @Bean
    public Decoder feignDecoder() {

        return new ResponseEntityDecoder(new JacksonDecoder(objectMapper)
        );
    }
}
