package com.popcorn.service;

import com.popcorn.dao.DaoImpl;
import com.popcorn.model.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DummyService {
    private final DaoImpl daoImpl;

    public ProductDto getData() {
        log.info("DummyService::getData");
        return daoImpl.getData();
    }
}
