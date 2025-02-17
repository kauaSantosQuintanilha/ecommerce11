package com.flamengo.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
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
    private String phone;
    private LocalDate birth_date;
    private String password;
    private String roles;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders ;



}
