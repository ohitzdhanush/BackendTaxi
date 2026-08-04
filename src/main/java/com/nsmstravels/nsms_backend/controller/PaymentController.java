package com.nsmstravels.nsms_backend.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

        // Your Razorpay Test Keys
        private static final String KEY_ID = "rzp_test_TLeEGqPAOXIOMd";
        private static final String KEY_SECRET = "8NbSnaev5GhAXgKUyWeTOLED";

        @PostMapping("/create-order")
        public String createOrder(@RequestBody Map<String, Object> data) throws Exception {
            double amount = ((Number) data.get("amount")).doubleValue();

            // Razorpay expects currency in paise (1 INR = 100 paise), so multiply by 100
            int amountInPaise = (int) (amount * 100);

            RazorpayClient client = new RazorpayClient(KEY_ID, KEY_SECRET);

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amountInPaise);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "ride_txn_" + System.currentTimeMillis());

            // Create the order on Razorpay's servers
            Order order = client.orders.create(orderRequest);

            // Return the Order JSON string back to React
            return order.toString();
        }
    }
