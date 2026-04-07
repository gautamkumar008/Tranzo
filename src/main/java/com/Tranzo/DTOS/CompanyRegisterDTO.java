package com.Tranzo.DTOS;

import com.Tranzo.Model.Enums.CompanyServiceType;
import com.Tranzo.Model.Enums.CompanyType;
import jakarta.validation.constraints.*;

public class CompanyRegisterDTO {

    @NotBlank(message = "Company name is required")
    @Size(max = 100, message = "Company name cannot exceed 100 characters")
    private String companyName;

    @NotBlank(message = "Register number is required")
    @Size(max = 50, message = "Register number cannot exceed 50 characters")
    private String registerNo;

    @Size(max = 50, message = "GST number cannot exceed 50 characters")
    private String gstNo;

    @NotBlank(message = "Company email is required")
    @Email(message = "Invalid email format")
    private String companyEmail;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits")
    private String contactNo;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%!&*])[A-Za-z\\d@#$%!&*]{8,}$",
            message = "Password must contain at least 1 uppercase, 1 lowercase, 1 number, and 1 special character"
    )
    private String password;

    @NotNull(message = "Company service type is required")
    private CompanyServiceType companyServiceType;

    @NotNull(message = "Company type is required")
    private CompanyType companyType;

    @NotNull(message = "Max weight is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Max weight must be greater than 0")
    private Double maxWeight;

    @NotNull(message = "Price per km is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price per km must be greater than 0")
    private Double pricePerKm;

    public CompanyRegisterDTO() {}

    // Getters and setters
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getRegisterNo() { return registerNo; }
    public void setRegisterNo(String registerNo) { this.registerNo = registerNo; }

    public String getGstNo() { return gstNo; }
    public void setGstNo(String gstNo) { this.gstNo = gstNo; }

    public String getCompanyEmail() { return companyEmail; }
    public void setCompanyEmail(String companyEmail) { this.companyEmail = companyEmail; }

    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public CompanyServiceType getCompanyServiceType() { return companyServiceType; }
    public void setCompanyServiceType(CompanyServiceType companyServiceType) { this.companyServiceType = companyServiceType; }

    public CompanyType getCompanyType() { return companyType; }
    public void setCompanyType(CompanyType companyType) { this.companyType = companyType; }

    public Double getMaxWeight() { return maxWeight; }
    public void setMaxWeight(Double maxWeight) { this.maxWeight = maxWeight; }

    public Double getPricePerKm() { return pricePerKm; }
    public void setPricePerKm(Double pricePerKm) { this.pricePerKm = pricePerKm; }
}