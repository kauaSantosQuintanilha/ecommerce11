package com.flamengo.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;

@Data
@Entity
@Table(name = "tb_payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp moment;
    private String  status;


}
