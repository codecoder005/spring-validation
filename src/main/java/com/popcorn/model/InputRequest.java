package com.popcorn.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InputRequest {
    @NotNull(message = "request.age is null")
    @Min(value = 18, message = "request.age minimum is 18")
    private Byte age;

    @NotNull(message = "request.height is null")
    @Min(value = 170, message = "request.height minimum is 170")
    private Short height;

    @NotNull(message = "request.name is null")
    @NotBlank(message = "request.name is blank")
    @NotEmpty(message = "request.name is empty")
    private String name;

    @NotNull(message = "request.email is null")
    @NotBlank(message = "request.email is blank")
    @Email(message = "request.email invalid email")
    private String email;

    @Size(min = 1, message = "request.hobbies at least 1 required")
    @NotNull(message = "request.hobbies is null")
    private List<String> hobbies;

    @NotNull(message = "request.transactionId is null")
    private UUID transactionId;

    @Valid
    @NotNull(message = "request.address is null")
    private Address address;
}