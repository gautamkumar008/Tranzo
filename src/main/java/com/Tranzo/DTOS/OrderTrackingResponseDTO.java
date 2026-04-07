package com.Tranzo.DTOS;

import com.Tranzo.Model.Enums.OrderStatus;
import com.Tranzo.Model.Enums.PaymentMode;
import com.Tranzo.Model.Enums.PaymentStatus;

public class OrderTrackingResponseDTO {
    private long orderNumber;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private PaymentMode paymentMod;
    private double weight;
    private double totalPrice;
    private String receiverName;

    public OrderTrackingResponseDTO(){}

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentMode getPaymentMod() {
        return paymentMod;
    }

    public void setPaymentMod(PaymentMode paymentMod) {
        this.paymentMod = paymentMod;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}
