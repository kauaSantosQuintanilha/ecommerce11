package com.flamengo.ecommerce.dtos;

import lombok.Data;

@Data
public class OrderItemDTO {
    private int quantity;
    private double price;
}
