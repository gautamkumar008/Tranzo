package com.Tranzo.DTOS;

public class FeesSummaryDTO {
    private double totalOrderAmount;
    private double totalPlatformFees;
    private double companyEarning;

    public FeesSummaryDTO() {}

    public double getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(double totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public double getTotalPlatformFees() {
        return totalPlatformFees;
    }

    public void setTotalPlatformFees(double totalPlatformFees) {
        this.totalPlatformFees = totalPlatformFees;
    }

    public double getCompanyEarning() {
        return companyEarning;
    }

    public void setCompanyEarning(double companyEarning) {
        this.companyEarning = companyEarning;
    }
}
