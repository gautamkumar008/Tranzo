package com.Tranzo.DTOS;

import com.Tranzo.Model.Enums.OrderStatus;
import com.Tranzo.Model.Enums.PaymentMode;
import com.Tranzo.Model.Enums.PaymentStatus;

public class GetOrderDTO {
    private Long OrderNumber;
    private String SenderName;
    private String MobileNo;
    private String ReceiverName;
    private String receiverPhone;
    private String description;
    private double weight;
    private double totalPrice;
    private double Earning;
    private double platformFees;
    private PaymentMode paymentMod;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private String arrivalPoint;
    private String destinationPoint;
    private int kilometer;

    public GetOrderDTO(){}

    public Long getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getSenderName() {
        return SenderName;
    }

    public void setSenderName(String senderName) {
        SenderName = senderName;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getReceiverName() {
        return ReceiverName;
    }

    public void setReceiverName(String receiverName) {
        ReceiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public double getEarning() {
        return Earning;
    }

    public void setEarning(double earning) {
        Earning = earning;
    }

    public double getPlatformFees() {
        return platformFees;
    }

    public void setPlatformFees(double platformFees) {
        this.platformFees = platformFees;
    }

    public PaymentMode getPaymentMod() {
        return paymentMod;
    }

    public void setPaymentMod(PaymentMode paymentMod) {
        this.paymentMod = paymentMod;
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

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public int getKilometer() {
        return kilometer;
    }

    public void setKilometer(int kilometer) {
        this.kilometer = kilometer;
    }
}
