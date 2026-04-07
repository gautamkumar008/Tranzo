package com.Tranzo.DTOS;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class AddRoutsDTO {

    @NotBlank(message = "Arrival point is required")
    @Size(min = 2, max = 100, message = "Arrival point must be between 2 and 100 characters")
    @Pattern(
            regexp = "^[A-Za-z ]+$",
            message = "Arrival point must contain only alphabets and spaces"
    )
    private String arrivalPoint;

    @NotBlank(message = "Destination point is required")
    @Size(min = 2, max = 100, message = "Destination point must be between 2 and 100 characters")
    @Pattern(
            regexp = "^[A-Za-z ]+$",
            message = "Destination point must contain only alphabets and spaces"
    )
    private String destinationPoint;

    @Positive(message = "Kilometer must be greater than 0")
    @Max(value = 50000, message = "Kilometer value is too large")
    private int kilometer;

    private LocalDateTime arrivalTime;

    public AddRoutsDTO(){}

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

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}