package com.flamengo.ecommerce.dtos;

import com.flamengo.ecommerce.entities.OrderStatus;
import lombok.Data;

import java.time.Instant;

@Data
public class OrderDTO {
    private Long id;
    private Instant moment;
    private OrderStatus status;

    public OrderDTO(Long id, Instant moment, OrderStatus status) {
    }
}
