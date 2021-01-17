package com.osworksapi.api.dto;

import com.osworksapi.domain.model.StatusOrderService;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderServiceDTO {
    private Long id;
    private UserDTO user;
    private String description;
    private BigDecimal price;
    private StatusOrderService status;
    private OffsetDateTime dateHasOpened;
    private OffsetDateTime dateHasFinished;
}
