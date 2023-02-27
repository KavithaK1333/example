package com.example.demo.service;

import com.example.demo.model.OrderSummary;
import com.example.demo.respository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    ObjectMapper mapper = new ObjectMapper();
    @Test
    void putNaceDetails() {
        MockMultipartFile multipartFile  = new MockMultipartFile("file", "NACE_REV2_20230224_171740.csv",
                "text/csv", "Spring Framework".getBytes());
        orderService.putNaceDetails(multipartFile);
        Mockito.verify(orderRepository,times(1)).saveAll(Mockito.any());
    }

    @Test
    void getNaceDetails() throws IOException {
        String profileReqJson = new String(Files.readAllBytes(Paths.get("src/test/java/stub/ordersummary.json")));
        OrderSummary orderSummary = mapper.readValue(profileReqJson, OrderSummary.class);
        Mockito.when(orderRepository.findByOrder(398482)).thenReturn(Optional.ofNullable(orderSummary));
        Assertions.assertEquals(orderSummary, orderService.getNaceDetails(398482));
    }
}