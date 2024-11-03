package com.popcorn.controller;

import com.google.gson.Gson;
import com.popcorn.advice.GlobalAPIControllerAdvice;
import com.popcorn.model.Address;
import com.popcorn.model.InputRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class UserControllerTest {
    private static final String USERS_API_BASE_URL = "/api/v1/users";
    private MockMvc mockMvc;

    @Autowired
    private GlobalAPIControllerAdvice globalAPIControllerAdvice;

    @Autowired
    private Gson jsonHelper;

    @Autowired
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(globalAPIControllerAdvice)
                .build();
    }

    @Test
    @DisplayName(value = "400 bad request")
    void testCreateUserShouldReturnBadRequest() throws Exception {
        final String TOKEN = "ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnpkV0lpT2lJeE1qTTBOVFkzT0Rrd0lpd2libUZ0WlNJNklrcHZhRzRnUkc5bElpd2lhV0YwSWpveE5URTJNak01TURJeWZRLlNmbEt4d1JKU01lS0tGMlFUNGZ3cE1lSmYzNlBPazZ5SlZfYWRRc3N3NWM=";
        InputRequest requestBody = InputRequest.builder()
                .name("John Doe")
                .email("john.doe@gmail.com")
                .build();
        mockMvc.perform(
                post(USERS_API_BASE_URL+"/{token}", TOKEN)
                        .param("company", "JP Morgan Chase & Co.")
                        .header(HttpHeaders.AUTHORIZATION, "Basic dXNlcm5hbWU6cGFzc3dvcmQ=")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonHelper.toJson(requestBody))
                        .characterEncoding(StandardCharsets.UTF_8)
        ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("One or more fields are failing validation criteria"))
                .andExpect(jsonPath("$.status").value(400))
                .andReturn();
    }

    @Test
    @DisplayName(value = "200 success")
    void testCreateUserShouldReturn200Success() throws Exception {
        final String TOKEN = "ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnpkV0lpT2lJeE1qTTBOVFkzT0Rrd0lpd2libUZ0WlNJNklrcHZhRzRnUkc5bElpd2lhV0YwSWpveE5URTJNak01TURJeWZRLlNmbEt4d1JKU01lS0tGMlFUNGZ3cE1lSmYzNlBPazZ5SlZfYWRRc3N3NWM=";
        InputRequest requestBody = InputRequest.builder()
                .age(Byte.valueOf("18"))
                .height(Short.valueOf("170"))
                .name("John Doe")
                .email("john.doe@gmail.com")
                .hobbies(List.of("READING"))
                .transactionId(UUID.randomUUID())
                .address(
                        Address.builder()
                                .line("Post office street")
                                .city("New York")
                                .state("New York")
                                .country("USA")
                                .build()
                ).build();
        mockMvc.perform(
                        post(USERS_API_BASE_URL+"/{token}", TOKEN)
                                .param("company", "JP Morgan Chase & Co.")
                                .header(HttpHeaders.AUTHORIZATION, "Basic dXNlcm5hbWU6cGFzc3dvcmQ=")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(jsonHelper.toJson(requestBody))
                                .characterEncoding(StandardCharsets.UTF_8)
                ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andReturn();
    }
}