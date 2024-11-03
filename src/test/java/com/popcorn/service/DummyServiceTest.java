package com.popcorn.service;

import com.popcorn.dao.DaoImpl;
import com.popcorn.model.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class DummyServiceTest {

    /**
     * When you are using {@code @SpringBootTest} annotation
     * use below style
     * {@code
     *     @MockBean
     *     private DaoImpl daoImpl;
     *     @Autowired
     *     private DummyService dummyService;
     * }
     */
    private Object springBootStyle;

    @Mock
    private DaoImpl daoImpl;

    @InjectMocks
    private DummyService dummyService;

    @Test
    void testGetData() {
        ProductDto mockedProductDto = ProductDto.builder()
                .pid(UUID.fromString("7d060e20-83af-4507-a2a4-a3af45327c93"))
                .name("Heritage Fresh Milk 1L")
                .expiryDate(ZonedDateTime.now().plusDays(3))
                .price(BigDecimal.valueOf(29.78))
                .build();
        when(daoImpl.getData()).thenReturn(mockedProductDto);
        ProductDto response = dummyService.getData();

        assertNotNull(response);
        assertEquals(UUID.fromString("7d060e20-83af-4507-a2a4-a3af45327c93"), response.getPid());
        assertEquals("Heritage Fresh Milk 1L", response.getName());
    }
}