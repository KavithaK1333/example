package com.example.demo.controller;

import com.example.demo.helper.CSVHelper;
import com.example.demo.model.OrderSummary;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PutMapping(value="/naceDetails", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> putNaceDetails(@RequestParam("file") MultipartFile file){
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                orderService.putNaceDetails(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload a csv file!");

    }
    @GetMapping(value="/getNaceDetails/{order}")
    public OrderSummary getNaceDetails(@PathVariable Integer order){
        return orderService.getNaceDetails(order);
    }
}
