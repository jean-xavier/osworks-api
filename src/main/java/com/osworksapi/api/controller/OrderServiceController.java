package com.osworksapi.api.controller;

import com.osworksapi.api.dto.NewOrderServiceDTO;
import com.osworksapi.api.dto.OrderServiceDTO;
import com.osworksapi.domain.model.OrderService;
import com.osworksapi.domain.services.ManagementOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("order-service/")
public class OrderServiceController {
    private final ManagementOrderService managementOrderService;
    private final ModelMapper modelMapper;

    public OrderServiceController(ManagementOrderService managementOrderService, ModelMapper modelMapper) {
        this.managementOrderService = managementOrderService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderServiceDTO create(@Valid @RequestBody NewOrderServiceDTO newOrderServiceDTO) {
        OrderService orderService = modelMapper.map(newOrderServiceDTO, OrderService.class);

        orderService = managementOrderService.create(orderService);

        return modelMapper.map(orderService, OrderServiceDTO.class);
    }

    @GetMapping
    public List<OrderServiceDTO> list() {
        final List<OrderService> orderServices = managementOrderService.listAll();

        return orderServices.stream()
                .map(orderService -> modelMapper.map(orderService, OrderServiceDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderServiceDTO> find(@PathVariable Long id) {
        OrderService orderService = managementOrderService.find(id);

        OrderServiceDTO dto = modelMapper.map(orderService, OrderServiceDTO.class);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{orderServiceId}/finalisation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finish(@PathVariable Long orderServiceId) {
        managementOrderService.finish(orderServiceId);
    }
}
