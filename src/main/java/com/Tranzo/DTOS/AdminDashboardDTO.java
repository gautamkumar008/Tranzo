package com.Tranzo.DTOS;

public class AdminDashboardDTO {
    private long totalOrders;
    private long pendingOrders;
    private long acceptedOrders;
    private long cancelledOrders;

    private Double totalRevenue;
    private Double totalPlatformFees;
    private Double totalCompanyEarnings;

    private long codPayments;
    private long onlinePayments;

    private long paidOrders;
    private long unpaidOrders;

    private Double totalWeight;

    public AdminDashboardDTO(){}

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public long getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(long pendingOrders) {
        this.pendingOrders = pendingOrders;
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

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getTotalPlatformFees() {
        return totalPlatformFees;
    }

    public void setTotalPlatformFees(Double totalPlatformFees) {
        this.totalPlatformFees = totalPlatformFees;
    }

    public Double getTotalCompanyEarnings() {
        return totalCompanyEarnings;
    }

    public void setTotalCompanyEarnings(Double totalCompanyEarnings) {
        this.totalCompanyEarnings = totalCompanyEarnings;
    }

    public long getCodPayments() {
        return codPayments;
    }

    public void setCodPayments(long codPayments) {
        this.codPayments = codPayments;
    }

    public long getOnlinePayments() {
        return onlinePayments;
    }

    public void setOnlinePayments(long onlinePayments) {
        this.onlinePayments = onlinePayments;
    }

    public long getPaidOrders() {
        return paidOrders;
    }

    public void setPaidOrders(long paidOrders) {
        this.paidOrders = paidOrders;
    }

    public long getUnpaidOrders() {
        return unpaidOrders;
    }

    public void setUnpaidOrders(long unpaidOrders) {
        this.unpaidOrders = unpaidOrders;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }
}
