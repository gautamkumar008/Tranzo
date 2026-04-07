package com.Tranzo.DTOS;

import com.Tranzo.Model.Enums.PaymentMode;
import jakarta.validation.constraints.*;

public class PlaceOrderRequestDTO {

    @NotNull(message = "Route id is required")
    @Positive(message = "Route id must be greater than 0")
    private Long routeId;

    @NotBlank(message = "Receiver name is required")
    @Size(min = 3, max = 100, message = "Receiver name must be between 3 and 100 characters")
    @Pattern(
            regexp = "^[A-Za-z ]+$",
            message = "Receiver name must contain only alphabets and spaces"
    )
    private String receiverName;

    @NotBlank(message = "Receiver phone is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Receiver phone must contain exactly 10 digits"
    )
    private String receiverPhone;

    @NotBlank(message = "Description is required")
    @Size(min = 5, max = 300, message = "Description must be between 5 and 300 characters")
    private String description;

    @Positive(message = "Weight must be greater than 0")
    @DecimalMax(value = "10000", message = "Weight value is too large")
    private double weight;

    @NotNull(message = "Payment mode is required")
    private PaymentMode paymentMod;

    public PlaceOrderRequestDTO(){}

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
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

    public PaymentMode getPaymentMod() {
        return paymentMod;
    }

    public void setPaymentMod(PaymentMode paymentMod) {
        this.paymentMod = paymentMod;
    }
}