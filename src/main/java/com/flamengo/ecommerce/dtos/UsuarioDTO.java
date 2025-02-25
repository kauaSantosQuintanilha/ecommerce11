package com.flamengo.ecommerce.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birth_date;
    private String password;
    private String roles;
}
