package com.popcorn.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @NotNull(message = "address.line is null")
    @NotBlank(message = "address.line is blank")
    @NotEmpty(message = "address.line is empty")
    private String line;

    @NotNull(message = "address.city is null")
    @NotBlank(message = "address.city is blank")
    @NotEmpty(message = "address.city is empty")
    private String city;
    private String state;
    private String country;
}
