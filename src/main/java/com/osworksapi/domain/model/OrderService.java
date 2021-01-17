package com.osworksapi.domain.model;

import com.osworksapi.domain.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String description;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private StatusOrderService status;

    private OffsetDateTime dateHasOpened;

    private OffsetDateTime dateHasFinished;

    @OneToMany(mappedBy = "orderService")
    private Set<Comment> comments;

    public void finish() {
        if (!StatusOrderService.OPEN.equals(status)) {
            throw new BusinessException("Order service can not to be finished");
        }

        this.status = StatusOrderService.FINISHED;
        this.dateHasFinished = OffsetDateTime.now();
    }
}
