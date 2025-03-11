package com.flamengo.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private LocalDate birthDate;
    private String roles;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
