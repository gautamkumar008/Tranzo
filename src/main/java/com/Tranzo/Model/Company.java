package com.Tranzo.Model;

import com.Tranzo.Model.Enums.CompanyServiceType;
import com.Tranzo.Model.Enums.CompanyType;
import com.Tranzo.Model.Enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Column(name = "company_name",nullable = false, length = 100)
    private String companyName;

    @Column(name = "register_no",nullable = false, unique = true, length = 50)
    private String registerNo;

    @Column(name = "gst_no",unique = true, length = 50)
    private String gstNo;

    @Column(name = "company_email",nullable = false, unique = true, length = 100)
    private String companyEmail;

    @Column(name = "contact_no",nullable = false, unique = true, length = 15)
    private String contactNo;

    @Column(nullable = false)
    private String password;

    @Column(name = "commission_rate",nullable = false)
    private Double commissionRate = 4.00;

    @Enumerated(EnumType.STRING)
    @Column(name = "Company_service_type",nullable = false)
    private CompanyServiceType companyServiceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "Company_type",nullable = false)
    private CompanyType companyType;

    @Column(name = "maxWeight",nullable = false)
    private Double maxWeight;

    @Column(name = "price_per_km",nullable = false)
    private Double pricePerKM;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.COMPANY;

    @Column(nullable = false)
    private boolean approved = true; // Admin approval

    public Company() {}

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(Double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public CompanyServiceType getCompanyServiceType() {
        return companyServiceType;
    }

    public void setCompanyServiceType(CompanyServiceType companyServiceType) {
        this.companyServiceType = companyServiceType;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Double getPricePerKM() {
        return pricePerKM;
    }

    public void setPricePerKM(Double pricePerKM) {
        this.pricePerKM = pricePerKM;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
