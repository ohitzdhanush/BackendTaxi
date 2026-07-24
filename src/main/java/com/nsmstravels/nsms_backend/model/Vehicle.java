package com.nsmstravels.nsms_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vechiles")
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private User driver;

    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private CarType carType;

    private int capacity;
}