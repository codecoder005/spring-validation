package com.popcorn.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private UUID pid;
    private String name;
    private BigDecimal price;
    private ZonedDateTime expiryDate;
}
