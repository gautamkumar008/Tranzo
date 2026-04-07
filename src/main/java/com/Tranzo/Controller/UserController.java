package com.Tranzo.Controller;

import com.Tranzo.APIResponse.ApiResponse;
import com.Tranzo.DTOS.PlaceOrderRequestDTO;
import com.Tranzo.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private UserServices userServices;

    //api for get company
    //http://localhost:8080/user/companies
    @GetMapping("/companies")
    public ResponseEntity<?> getCompany(){
        try {
            return ResponseEntity.ok(ApiResponse.success("company fetched successfully",userServices.getCompany()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }

    //api for get company
    //http://localhost:8080/user/company/email
    @GetMapping("/company/{name}")
    public ResponseEntity<?> getCompanyById(@PathVariable String name){
        try {
            return ResponseEntity.ok(ApiResponse.success("company fetched successfully",userServices.getCompanyByIdForUser(name)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }

    //api for make order
    @PostMapping("/order")
    public ResponseEntity<?> placeOrder(Authentication authentication, @RequestBody PlaceOrderRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.success("Order placed successfully", userServices.placeOrder(authentication,request)));
    }

    // Track order using tracking number
    @GetMapping("/track/{trackingNumber}")
    public ResponseEntity<?> trackOrder(@PathVariable String trackingNumber) {
        try {
            return ResponseEntity.ok(ApiResponse.success("Order track successfully", userServices.trackOrder(trackingNumber)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }
    //api for cancel order
    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<?> cancelOrder(Authentication authentication,@PathVariable Long orderId) {
        try {
            userServices.cancelOrder(authentication, orderId);
            return ResponseEntity.ok(ApiResponse.success("Order cancel successfully", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }

    //api for get profile
    @GetMapping("/profile")
    public ResponseEntity<?> profile(Authentication authentication) {
        try {
            return ResponseEntity.ok(ApiResponse.success("profile fetched successfully", userServices.getProfile(authentication)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }

}
