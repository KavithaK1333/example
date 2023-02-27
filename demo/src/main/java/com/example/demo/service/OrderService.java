package com.example.demo.service;

import com.example.demo.exception.CustomException;
import com.example.demo.helper.CSVHelper;
import com.example.demo.model.OrderSummary;
import com.example.demo.respository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
     public void putNaceDetails(MultipartFile file) {
        try {
            List<OrderSummary> orders = CSVHelper.csvToOrders(file.getInputStream());
            orderRepository.saveAll(orders);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
    public OrderSummary getNaceDetails(Integer order){
       return orderRepository.findByOrder(order).orElseThrow(
                () -> new CustomException(order + " Order is not found")
    );

    }
}
