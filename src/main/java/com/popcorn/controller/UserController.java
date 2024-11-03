package com.popcorn.controller;

import com.google.gson.Gson;
import com.popcorn.model.InputRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final Gson jsonHelper;

    @PostMapping(
            path = "/{token}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> createUser(
            @PathVariable(name = "token") String token,
            @RequestHeader(name = "Authorization") String authorization,
            @RequestParam(name = "company") String company,
            @Valid @RequestBody InputRequest user
    ) {
        log.info("UserController::createUser token: {}", token);
        log.info("UserController::createUser Authorization: {}", authorization);
        log.info("UserController::createUser company: {}", company);
        log.info("UserController::createUser input: {}", jsonHelper.toJson(user));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                        "data", user,
                        "message", "User created",
                        "status", HttpStatus.CREATED.value(),
                        "timestamp", ZonedDateTime.now()
                ));
    }
}
