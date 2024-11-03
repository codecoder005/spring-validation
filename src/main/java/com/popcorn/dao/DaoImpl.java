package com.popcorn.dao;

import com.popcorn.model.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Repository
@Slf4j
public class DaoImpl {
    public ProductDto getData() {
        log.info("DaoImpl::getData");
        return ProductDto.builder()
                .pid(UUID.fromString("c90cbb59-5480-4019-9960-9462255449a1"))
                .name("Whole Wheat Bread")
                .expiryDate(ZonedDateTime.now().plusDays(10))
                .price(BigDecimal.valueOf(12.56))
                .build();
    }
}
