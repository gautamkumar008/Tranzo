package com.Tranzo.Services;

import com.Tranzo.DTOS.AdminDashboardDTO;
import com.Tranzo.Model.Company;
import com.Tranzo.Model.Enums.OrderStatus;
import com.Tranzo.Model.Enums.PaymentMode;
import com.Tranzo.Model.Enums.PaymentStatus;
import com.Tranzo.Model.Orders;
import com.Tranzo.Model.Routes;
import com.Tranzo.Model.User;
import com.Tranzo.Repository.CompanyRepository;
import com.Tranzo.Repository.OrderRepository;
import com.Tranzo.Repository.RouteRepository;
import com.Tranzo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServices {

    @Autowired private UserRepository userRepository;
    @Autowired private CompanyRepository companyRepository;
    @Autowired private RouteRepository routeRepository;
    @Autowired private OrderRepository orderRepository;

    public List<Company> getCompany(){
        return companyRepository.findAll();
    }

    public List<Routes> getRouts(){
        return routeRepository.findAll();
    }

    public List<User> getUser(){
        return userRepository.findAll();
    }

    public List<Orders> getOrders(){
        return orderRepository.findAll();
    }

    public AdminDashboardDTO getAdminDashboard() {

        AdminDashboardDTO dto = new AdminDashboardDTO();

        dto.setTotalOrders(orderRepository.count());

        dto.setPendingOrders(orderRepository.countByOrderStatus(OrderStatus.Placed));

        dto.setAcceptedOrders(orderRepository.countByOrderStatus(OrderStatus.Delivered));

        dto.setCancelledOrders(orderRepository.countByOrderStatus(OrderStatus.Cancelled));

        dto.setTotalRevenue(orderRepository.getTotalRevenue());

        dto.setTotalPlatformFees(orderRepository.getTotalPlatformFees());

        dto.setTotalCompanyEarnings(orderRepository.getTotalCompanyEarnings());

        dto.setCodPayments(orderRepository.countByPaymentMod(PaymentMode.COD));

        dto.setOnlinePayments(orderRepository.countByPaymentMod(PaymentMode.Online));

        dto.setPaidOrders(orderRepository.countByPaymentStatus(PaymentStatus.Paid));

        dto.setUnpaidOrders(orderRepository.countByPaymentStatus(PaymentStatus.Pending));

        dto.setTotalWeight(orderRepository.getTotalWeightTransported());

        return dto;
    }
}
