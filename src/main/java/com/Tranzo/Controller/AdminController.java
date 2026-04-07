package com.Tranzo.Controller;

import com.Tranzo.APIResponse.ApiResponse;
import com.Tranzo.Model.Company;
import com.Tranzo.Repository.CompanyRepository;
import com.Tranzo.Repository.OrderRepository;
import com.Tranzo.Repository.RouteRepository;
import com.Tranzo.Repository.UserRepository;
import com.Tranzo.Services.AdminServices;
import com.Tranzo.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired private UserRepository userRepository;
    @Autowired private CompanyRepository companyRepository;
    @Autowired private RouteRepository routeRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private UserServices userServices;
    @Autowired private AdminServices adminServices;

    //
    @GetMapping("/companies")
    public ResponseEntity<?> getCompany(){
        try {
            return ResponseEntity.ok(ApiResponse.success("company fetched successfully",companyRepository.findAll()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }

    //
    @GetMapping("/routs")
    public ResponseEntity<?> routs(){
        try {
            return ResponseEntity.ok(ApiResponse.success("Routes fetched successfully",routeRepository.findAll()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }

    //
    @GetMapping("/users")
    public ResponseEntity<?> users(){
        try {
            return ResponseEntity.ok(ApiResponse.success("User fetched successfully",userRepository.findAll()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }

    //
    @GetMapping("/orders")
    public ResponseEntity<?> orders(){
        try {
            return ResponseEntity.ok(ApiResponse.success("order fetched successfully",orderRepository.findAll()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }

    //
    @GetMapping("/income")
    public ResponseEntity<?> income(){
        try {
            return ResponseEntity.ok(ApiResponse.success("income fetched successfully",orderRepository.getTotalIncomeAllCompanies()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }

    // GET /admin/companies/pending
    //
    @GetMapping("/companies/pending")
    public ResponseEntity<?> getPendingCompanies() {
        try {
            return ResponseEntity.ok(ApiResponse.success("Pending companies fetched", companyRepository.findByApprovedFalse()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }

    //Approve specific company
    //
    @PutMapping("/company/approve/{companyId}")
    public ResponseEntity<?> approveCompany(@PathVariable Long companyId) {
        try {
            Company company = companyRepository.findById(companyId).orElseThrow(() -> new IllegalArgumentException("Company not found"));
            company.setApproved(true); // mark as approve
            companyRepository.save(company);
            return ResponseEntity.ok(ApiResponse.success("Company approved successfully", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<?> deleteUserById(@RequestBody Long id) {
        try {
            // Call your service method to delete user
            userServices.deleteUserById(id);

            // Return success response
            return ResponseEntity.ok(ApiResponse.success("User deleted successfully", null));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Validation error", List.of(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }


    @GetMapping("/dashboard")
    public ResponseEntity<?> getAdminDashboard() {
        try {
            return ResponseEntity.ok(ApiResponse.success("Admin dashboard data fetched", adminServices.getAdminDashboard()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Server error", List.of(e.getMessage())));
        }
    }

}
