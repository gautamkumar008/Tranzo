package com.Tranzo.Repository;

import com.Tranzo.Model.Enums.OrderStatus;
import com.Tranzo.Model.Enums.PaymentMode;
import com.Tranzo.Model.Enums.PaymentStatus;
import com.Tranzo.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

    Orders findByTrackingNo(String trackingNo);

    List<Orders> findByCompany_Cid(Long companyId);

    List<Orders> findByCompany_CidAndOrderStatus(Long companyId, OrderStatus orderStatus);

    long countByCompany_Cid(Long companyId);

    long countByCompany_CidAndOrderStatus(Long companyId, OrderStatus status);

    @Query("SELECT COALESCE(SUM(o.companyEarning),0) FROM Orders o WHERE o.company.cid = :companyId")
    Double getTotalIncome(Long companyId);


    @Query("SELECT COALESCE(SUM(o.platformFees), 0) FROM Orders o WHERE o.company.cid = :companyId")
    Double getTotalPlatformFeesByCompany(Long companyId);

    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM Orders o WHERE o.company.cid = :companyId")
    Double getTotalOrderAmountByCompany(Long companyId);

    @Query("SELECT COALESCE(SUM(o.totalPrice - o.platformFees), 0) FROM Orders o WHERE o.company.cid = :companyId")
    Double getCompanyEarningByCompany(Long companyId);

    // Total income for all companies
    @Query("SELECT COALESCE(SUM(o.totalPrice - o.platformFees), 0) FROM Orders o")
    Double getTotalIncomeAllCompanies();

    //dashborad data

    long countByOrderStatus(OrderStatus status);

    long countByPaymentMod(PaymentMode paymentMode);

    long countByPaymentStatus(PaymentStatus paymentStatus);

    @Query("SELECT COALESCE(SUM(o.totalPrice),0) FROM Orders o")
    Double getTotalRevenue();

    @Query("SELECT COALESCE(SUM(o.platformFees),0) FROM Orders o")
    Double getTotalPlatformFees();

    @Query("SELECT COALESCE(SUM(o.companyEarning),0) FROM Orders o")
    Double getTotalCompanyEarnings();

    @Query("SELECT COALESCE(SUM(o.weight),0) FROM Orders o")
    Double getTotalWeightTransported();
}
