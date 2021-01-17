package com.osworksapi.api.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    private Long id;
    private String description;
    private OffsetDateTime dateHasSent;
}
