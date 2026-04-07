package com.Tranzo.Model;

import com.Tranzo.Model.Enums.OrderStatus;
import com.Tranzo.Model.Enums.PaymentMode;
import com.Tranzo.Model.Enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

    // 🔗 Company Foreign Key
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    // 🔗 Sender (User) Foreign Key
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Routes route;

    @Column(nullable = false, length = 100)
    private String receiverName;

    @Column(nullable = false, length = 15)
    private String receiverPhone;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double weight;

    private double totalPrice;

    private double companyEarning;

    private double platformFees;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.Pending;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.Placed;

    @Column(nullable = false, unique = true, length = 50)
    private String trackingNo;

    private LocalDateTime CreatedAt = LocalDateTime.now();

    private LocalDateTime CancelledAt;

    public Orders() {}

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Routes getRoute() {
        return route;
    }

    public void setRoute(Routes route) {
        this.route = route;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getCompanyEarning() {
        return companyEarning;
    }

    public void setCompanyEarning(double companyEarning) {
        this.companyEarning = companyEarning;
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

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        CreatedAt = createdAt;
    }

    public LocalDateTime getCancelledAt() {
        return CancelledAt;
    }

    public void setCancelledAt(LocalDateTime cancelledAt) {
        CancelledAt = cancelledAt;
    }
}
