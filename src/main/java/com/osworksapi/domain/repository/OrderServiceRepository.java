package com.osworksapi.domain.repository;

import com.osworksapi.domain.model.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {
}
