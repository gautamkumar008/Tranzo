package com.Tranzo.DTOS;

public class CompanyDashboardDTO {
    private double totalIncome;
    private double totalPlatformFees;
    private long totalOrders;
    private long acceptedOrders;
    private long cancelledOrders;
    private long pendingOrders;

    public CompanyDashboardDTO(){}

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalPlatformFees() {
        return totalPlatformFees;
    }

    public void setTotalPlatformFees(double totalPlatformFees) {
        this.totalPlatformFees = totalPlatformFees;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public long getAcceptedOrders() {
        return acceptedOrders;
    }

    public void setAcceptedOrders(long acceptedOrders) {
        this.acceptedOrders = acceptedOrders;
    }

    public long getCancelledOrders() {
        return cancelledOrders;
    }

    public void setCancelledOrders(long cancelledOrders) {
        this.cancelledOrders = cancelledOrders;
    }

    public long getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(long pendingOrders) {
        this.pendingOrders = pendingOrders;
    }
}
