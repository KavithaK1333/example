package com.example.demo.respository;

import com.example.demo.model.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderSummary,Integer> {
    Optional<OrderSummary> findByOrder(Integer order);
}
