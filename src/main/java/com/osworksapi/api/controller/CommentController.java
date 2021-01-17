package com.osworksapi.api.controller;

import com.osworksapi.api.dto.CommentDTO;
import com.osworksapi.api.dto.NewCommentDTO;
import com.osworksapi.domain.model.Comment;
import com.osworksapi.domain.model.OrderService;
import com.osworksapi.domain.services.ManagementOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("order-service/{orderServiceId}/comments")
public class CommentController {

    private final ManagementOrderService managementOrderService;
    private final ModelMapper modelMapper;

    public CommentController(ManagementOrderService managementOrderService, ModelMapper modelMapper) {
        this.managementOrderService = managementOrderService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDTO add(@PathVariable Long orderServiceId, @Valid @RequestBody NewCommentDTO commentDTO) {
        Comment comment = this.managementOrderService.addComment(orderServiceId, commentDTO.getDescription());

        return modelMapper.map(comment, CommentDTO.class);
    }

    @GetMapping
    public List<CommentDTO> list(@PathVariable Long orderServiceId) {
        final OrderService orderService = managementOrderService.find(orderServiceId);

        return orderService.getComments().stream()
                .map(comment -> modelMapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());
    }
}
