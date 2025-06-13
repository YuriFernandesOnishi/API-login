package com.yuri.login.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "system_settings")
public class SystemSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "max_spots", nullable = false)
    private Integer maxSpots;

    @Column(name = "first_hour_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal firstHourPrice; // Alterado para BigDecimal

    @Column(name = "additional_hour_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal additionalHourPrice; // Alterado para BigDecimal

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false, insertable = false, columnDefinition = "TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)")
    private Date updatedAt;
}