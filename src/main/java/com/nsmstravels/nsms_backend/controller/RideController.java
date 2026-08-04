package com.nsmstravels.nsms_backend.controller;

import com.nsmstravels.nsms_backend.model.Ride;
import com.nsmstravels.nsms_backend.repository.RideRepository;
import com.nsmstravels.nsms_backend.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nsmstravels.nsms_backend.model.RideStatus;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rides")
@CrossOrigin(origins = "*")
public class RideController {

    @Autowired
    private RideService rideService;
    @Autowired
    private RideRepository rideRepository;

    @PostMapping("/request")
    public Ride requestRide(@RequestBody Map<String, Object> requestData){
        Long riderId=((Number) requestData.get("riderId")).longValue();
        String pickup =(String) requestData.get("pickup");
        String dropoff =(String) requestData.get("dropoff");
        double fare =((Number) requestData.get("fare")).doubleValue();

        return rideService.requestRide(riderId,pickup,dropoff,fare);
    }

    @PostMapping("/{rideId}/accept")
    public Ride acceptRide(@PathVariable Long rideId, @RequestParam Long driverId) {
        return rideService.acceptRide(rideId, driverId);
    }

    @GetMapping("/pending")
    public List<Ride> getPendingRides() {
        return rideService.getPendingRides();
    }

    @PostMapping("/{rideId}/start")
    public Ride startRide(@PathVariable Long rideId) {
        return rideService.startRide(rideId);
    }

    @PostMapping("/{rideId}/complete")
    public Ride completeRide(@PathVariable Long rideId) {
        return rideService.completeRide(rideId);
    }

    @PostMapping("/{rideId}/cancel")
    public Ride cancelRide(@PathVariable Long rideId) {
        return rideService.cancelRide(rideId);
    }

    @GetMapping("/{rideId}")
    public Ride getRideById(@PathVariable Long rideId) {
        return rideService.getRideById(rideId);
    }
    // Endpoint to save Payment ID after Razorpay success
    @PostMapping("/{rideId}/pay")
    public Ride markPaymentComplete(@PathVariable Long rideId, @RequestParam String paymentId) {
        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RuntimeException("Ride not found"));

        ride.setPaymentId(paymentId);
        ride.setStatus(RideStatus.PAID);

        return rideRepository.save(ride);
    }

    // Endpoint to get Ride History for a user
    @GetMapping("/user/{userId}")
    public List<Ride> getUserRideHistory(@PathVariable Long userId) {
        return rideRepository.findByRiderIdOrderByIdDesc(userId);
    }
}
