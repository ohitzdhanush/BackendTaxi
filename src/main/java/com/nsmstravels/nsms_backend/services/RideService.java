package com.nsmstravels.nsms_backend.services;

import com.nsmstravels.nsms_backend.model.Ride;
import com.nsmstravels.nsms_backend.model.RideStatus;
import com.nsmstravels.nsms_backend.model.User;
import com.nsmstravels.nsms_backend.repository.RideRepository;
import com.nsmstravels.nsms_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    public Ride requestRide(Long riderId, String pickup, String dropoff, double fare) {
        User rider = userRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider Not Found"));

        Ride ride = new Ride();
        ride.setRider(rider);
        ride.setPickupLatLng(pickup);
        ride.setDropoffLatLng(dropoff);
        ride.setFare(fare);
        ride.setStatus(RideStatus.REQUESTED);

        return rideRepository.save(ride);
    }

    public Ride acceptRide(Long rideId, Long driverId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        User driver =  userRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        ride.setDriver(driver);
        ride.setStatus(RideStatus.ACCEPTED);

        return rideRepository.save(ride);
    }

    public List<Ride> getPendingRides() {
        return rideRepository.findByStatus(RideStatus.REQUESTED);
    }
}