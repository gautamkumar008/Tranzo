package com.Tranzo.DTOS;

import com.Tranzo.Model.Enums.OrderStatus;

public class OrderResponseDTO {
    private String trackingNo;
    private double totalPrice;
    private OrderStatus orderStatus;

    public  OrderResponseDTO(){}

    public OrderResponseDTO(String trackingNo, double totalPrice, OrderStatus orderStatus) {
        this.trackingNo = trackingNo;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
