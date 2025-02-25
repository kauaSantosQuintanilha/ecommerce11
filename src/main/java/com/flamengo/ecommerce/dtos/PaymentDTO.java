package com.flamengo.ecommerce.dtos;

import lombok.Data;

import java.security.Timestamp;

@Data
public class PaymentDTO {
    private Long id;
    private Timestamp moment;
    private String  status;
}
