package com.tinqin.library.reporting.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqin.library.reporting.api.ApiRoutes;
import com.tinqin.library.reporting.api.operations.postevent.CreateEventInput;
import com.tinqin.library.reporting.apiadapter.ApiAdapter;
import com.tinqin.library.reporting.rest.controllers.EventController;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(EventController.class)
class EventControllerTest {
    @Autowired
    MockMvc mockMvc;// browser functions

    @MockBean
    ApiAdapter apiAdapter;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public static Stream<Arguments> returnBadRequestWhenEventNameFromMethodInvalid() {

        return  Stream.of(
                Arguments.of("t", false),
                Arguments.of("t1234567890123456789012345678sssssssssssss", false) );
    }

    @Test
    @SneakyThrows
    void returnBadRequestWhenInputIsInvalid() throws Exception {
        CreateEventInput createEventInput = CreateEventInput
                .builder()
                .eventName("test")
                .recordId("not UUID")
                .build();

        HttpHeaders headers = new HttpHeaders() {
            {
                put("Locale", List.of("en"));
            }
        };
        mockMvc.perform(post(ApiRoutes.EVENT)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .content(objectMapper.writeValueAsString(createEventInput)))
                .andExpect(result -> assertEquals(400,  result.getResponse().getStatus())) ;
    }


    @ParameterizedTest
    @ValueSource(strings = {"t","t1234567890123456789012345678sssssssssssss"})
    @SneakyThrows
    void returnBadRequestWhenEventNameInvalid(String name) throws Exception {
        CreateEventInput createEventInput = CreateEventInput
                .builder()
                .eventName(name)
                .recordId("c6cb8f1d-a147-44b8-80a1-39e9ba373747")
                .build();

        mockMvc.perform(post(ApiRoutes.EVENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createEventInput)))
                .andExpect(result -> assertEquals(400,  result.getResponse().getStatus())) ;
    }
    @ParameterizedTest
    @MethodSource
    @SneakyThrows
     void returnBadRequestWhenEventNameFromMethodInvalid(String name) throws Exception {
        CreateEventInput createEventInput = CreateEventInput
                .builder()
                .eventName(name)
                .recordId("c6cb8f1d-a147-44b8-80a1-39e9ba373747")
                .build();

        mockMvc.perform(post(ApiRoutes.EVENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createEventInput)))
                .andExpect(result -> assertEquals(400,  result.getResponse().getStatus())) ;
    }
}