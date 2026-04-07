package com.Tranzo.Controller;

import com.Tranzo.APIResponse.ApiResponse;
import com.Tranzo.DTOS.AddRoutsDTO;
import com.Tranzo.Model.Company;
import com.Tranzo.Model.Routes;
import com.Tranzo.Repository.CompanyRepository;
import com.Tranzo.Repository.RouteRepository;
import com.Tranzo.Services.CompanyServices;
import com.Tranzo.Services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired private CompanyRepository companyRepository;
    @Autowired private RouteRepository routeRepository;
    @Autowired private CompanyServices companyServices;
    @Autowired private UserServices userServices;

    //api for add routs
    //http://localhost:8080/company/addRoutes
    @PostMapping("/addRoutes")
    public ResponseEntity<?> addRouts(Authentication authentication, @Valid @RequestBody AddRoutsDTO routsDTO){
        return ResponseEntity.ok(ApiResponse.success("routs added success",companyServices.addRouts(authentication, routsDTO)));
    }

    //api for get orders
    //http://localhost:8080/company/orders
    @GetMapping("/orders")
    public ResponseEntity<?> getOrders(Authentication authentication){
        try {
            return ResponseEntity.ok(ApiResponse.success("order fetched successfully",companyServices.getOrder(authentication)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }

    //api for get orders
    //http://localhost:8080/company/myOrders
    @GetMapping("/AllOrders")
    public ResponseEntity<?> getAllOrders(Authentication authentication){
        try {
            return ResponseEntity.ok(ApiResponse.success("order fetched successfully",companyServices.getAllOrder(authentication)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }

    //api for reject / accept orders
    //http://localhost:8080/company/acceptOrder/1
    @PutMapping("/acceptOrder/{orderId}")
    public ResponseEntity<?> acceptOrder(Authentication authentication, @PathVariable long orderId) {
        try {
            companyServices.acceptOrder(authentication, orderId);
            return ResponseEntity.ok(ApiResponse.success("Order accepted successfully", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of("Something went wrong")));
        }
    }

    //http://localhost:8080/company/rejectOrder/1
    @PutMapping("/rejectOrder/{orderId}")
    public ResponseEntity<?> rejectOrder(Authentication authentication, @PathVariable long orderId) {
        try {
            companyServices.rejectOrder(authentication, orderId);
            return ResponseEntity.ok(ApiResponse.success("Order rejected successfully", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of("Something went wrong")));
        }
    }

    @PutMapping("/updateOrderStatus/{orderId}")
    public ResponseEntity<?> updateOrderStatus(Authentication authentication, @PathVariable Long orderId, @RequestParam String status)
    {
        try {
            companyServices.updateOrderStatus(authentication,orderId,status);
            return ResponseEntity.ok(ApiResponse.success("Order Status Updated successfully", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of("Something went wrong")));
        }
    }

    //api for update routs
    //http://localhost:8080/company/updateRoute/1
    @PutMapping("/updateRoute/{routeId}")
    public ResponseEntity<?> updateRoute(Authentication authentication, @PathVariable Long routeId, @Valid @RequestBody AddRoutsDTO dto) {
        try {
            companyServices.updateRoute(authentication, routeId, dto);
            return ResponseEntity.ok(ApiResponse.success("Route updated successfully", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of("Something went wrong")));
        }
    }

    // http://localhost:8080/company/deleteRoute/1
    //api  for delete routes
    @DeleteMapping("/deleteRoute/{routeId}")
    public ResponseEntity<?> deleteRoute(Authentication authentication, @PathVariable Long routeId) {
        try {
            companyServices.deleteRoute(authentication, routeId);
            return ResponseEntity.ok(ApiResponse.success("Route deleted successfully", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of("Something went wrong")));
        }
    }

    //get routes api
    //http://localhost:8080/company/myRoutes
    @GetMapping("/myRoutes")
    public ResponseEntity<?> myRoutes(Authentication authentication) {
        try {
            String email = authentication.getName();
            Company company = companyRepository.findByCompanyEmail(email);
            List<Routes> routes = routeRepository.findByCompanyCid(company.getCid());
            return ResponseEntity.ok(ApiResponse.success("Routes fetched successfully", routes));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of("Something went wrong")));
        }
    }


        //api for get  profile
    //http://localhost:8080/company/profile
    @GetMapping("/profile")
    public ResponseEntity<?> getCompany(Authentication authentication){
        try {
            return ResponseEntity.ok(ApiResponse.success("profile fetched successfully",userServices.getMyProfile(authentication)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }

    //api for dashboard
    //http://localhost:8080/company/dashboard
    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard(Authentication authentication) {
        try {
            return ResponseEntity.ok(ApiResponse.success("Dashboard data fetched", companyServices.getDashboard(authentication)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }


    //api for fees-summary
    //http://localhost:8080/company/fees-summary
    @GetMapping("/fees-summary")
    public ResponseEntity<?> getFeesSummary(Authentication authentication) {
        try {
            return ResponseEntity.ok(ApiResponse.success("Fees summary fetched", companyServices.getFeesSummary(authentication)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }


}
