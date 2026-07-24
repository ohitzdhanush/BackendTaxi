package com.nsmstravels.nsms_backend.repository;
import com.nsmstravels.nsms_backend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByDriver_Id(Long driverId);
}