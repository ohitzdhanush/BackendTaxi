package com.nsmstravels.nsms_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="rides")
@Data
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rider_id")
    private User rider;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    private String pickupLatLng;
    private String dropoffLatLng;

    @Enumerated(EnumType.STRING)
    private RideStatus status;

    private double fare;
    private String paymentId;
    
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
