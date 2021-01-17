package com.osworksapi.domain.services;

import com.osworksapi.domain.exceptions.BusinessException;
import com.osworksapi.domain.exceptions.EntityNotFoundException;
import com.osworksapi.domain.model.Comment;
import com.osworksapi.domain.model.OrderService;
import com.osworksapi.domain.model.StatusOrderService;
import com.osworksapi.domain.model.User;
import com.osworksapi.domain.repository.CommentRepository;
import com.osworksapi.domain.repository.OrderServiceRepository;
import com.osworksapi.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ManagementOrderService {
    
    private final OrderServiceRepository orderServiceRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public ManagementOrderService(OrderServiceRepository orderServiceRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.orderServiceRepository = orderServiceRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public OrderService create(OrderService orderService) {
        final Long userID = orderService.getUser().getId();

        final User user = userRepository.findById(userID)
                .orElseThrow(() -> new BusinessException("Not found user with id -> " + userID));

        orderService.setUser(user);
        orderService.setStatus(StatusOrderService.OPEN);
        orderService.setDateHasOpened(OffsetDateTime.now());

        return this.orderServiceRepository.save(orderService);
    }

    public List<OrderService> listAll() {
        return orderServiceRepository.findAll();
    }

    public OrderService find(Long id) {
        return orderServiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found ordem de serviço with id -> " + id));
    }

    public Comment addComment(Long orderServiceId, String description) {
        final OrderService orderService = orderServiceRepository.findById(orderServiceId)
                .orElseThrow(() -> new EntityNotFoundException("Not found ordem de serviço with id -> " + orderServiceId));

        final Comment comment = Comment.builder()
                .description(description)
                .dateHasSent(OffsetDateTime.now())
                .orderService(orderService)
                .build();

        return this.commentRepository.save(comment);
    }

    public void finish(Long orderServiceId) {
        final OrderService orderService = orderServiceRepository.findById(orderServiceId)
                .orElseThrow(() -> new EntityNotFoundException("Not found ordem de serviço with id -> " + orderServiceId));

        orderService.finish();

        orderServiceRepository.save(orderService);
    }
}
