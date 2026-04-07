package com.Tranzo.DTOS;

import com.Tranzo.Model.Enums.CompanyServiceType;
import com.Tranzo.Model.Enums.CompanyType;

import java.util.List;

public class CompanyDetailsDTO {
    private Long cid;
    private String companyName;
    private String companyEmail;
    private String contactNo;
    private CompanyServiceType companyServiceType;
    private CompanyType companyType;
    private Double maxWeight;
    private Double pricePerKM;
    private List<RoutesDTO> routes;

    public CompanyDetailsDTO(){}

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

    public List<RoutesDTO> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RoutesDTO> routes) {
        this.routes = routes;
    }
}
