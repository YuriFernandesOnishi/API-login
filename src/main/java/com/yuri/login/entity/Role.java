package com.yuri.login.entity;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "privilege") // Alinha com o nome da tabela no banco
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "privilege_level", nullable = false, unique = true)
    private Integer privilegeLevel;
}