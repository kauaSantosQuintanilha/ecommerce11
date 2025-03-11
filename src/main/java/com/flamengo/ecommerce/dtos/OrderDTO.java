package com.flamengo.ecommerce.dtos;

import com.flamengo.ecommerce.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private Long id;
    private LocalDateTime moment;
    private OrderStatus status;
}