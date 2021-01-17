package com.osworksapi.api.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrderServiceDTO {

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Long userId;
}
