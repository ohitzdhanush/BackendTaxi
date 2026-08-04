package com.nsmstravels.nsms_backend.repository;
import com.nsmstravels.nsms_backend.model.Ride;
import com.nsmstravels.nsms_backend.model.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByStatus(RideStatus status);
    List<Ride> findByRider_Id(Long riderId);
    List<Ride> findByDriver_Id(Long driverId);
    List<Ride> findByRiderIdOrderByIdDesc(Long riderId);
}