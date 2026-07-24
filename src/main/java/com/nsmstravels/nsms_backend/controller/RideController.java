package com.nsmstravels.nsms_backend.controller;

import com.nsmstravels.nsms_backend.model.Ride;
import com.nsmstravels.nsms_backend.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rides")
@CrossOrigin(origins = "*")
public class RideController {

    @Autowired
    private RideService rideService;

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

}
