package com.example.demo.controller;

import com.example.demo.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {
    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderService orderService;


    @Test
    void putNaceDetails() {
        MockMultipartFile multipartFile  = new MockMultipartFile("file", "NACE_REV2_20230224_171740.csv",
                "text/csv", "Spring Framework".getBytes());
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).body("Uploaded the file successfully: NACE_REV2_20230224_171740.csv"), orderController.putNaceDetails(multipartFile));
    }
    @Test
    void putNaceDetailsBadRequest() {
        MockMultipartFile multipartFile  = new MockMultipartFile("file", "NACE_REV2_20230224_171740.csv",
                "text/plain", "Spring Framework".getBytes());
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload a csv file!"), orderController.putNaceDetails(multipartFile));
    }

    @Test
    void getNaceDetails() {
        orderController.getNaceDetails(398482);
       Mockito.verify(orderService, times(1)).getNaceDetails(398482);
    }
}