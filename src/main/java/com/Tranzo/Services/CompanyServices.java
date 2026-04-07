package com.Tranzo.Services;

import com.Tranzo.DTOS.AddRoutsDTO;
import com.Tranzo.DTOS.CompanyDashboardDTO;
import com.Tranzo.DTOS.FeesSummaryDTO;
import com.Tranzo.DTOS.GetOrderDTO;
import com.Tranzo.Model.Company;
import com.Tranzo.Model.Enums.OrderStatus;
import com.Tranzo.Model.Enums.PaymentStatus;
import com.Tranzo.Model.Orders;
import com.Tranzo.Model.Routes;
import com.Tranzo.Repository.CompanyRepository;
import com.Tranzo.Repository.OrderRepository;
import com.Tranzo.Repository.RouteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServices {

    @Autowired private CompanyRepository companyRepository;
    @Autowired private RouteRepository routeRepository;
    @Autowired private OrderRepository orderRepository;

    //method for add routs
    public String addRouts(Authentication authentication, AddRoutsDTO routsDTO){
        Company company = companyRepository.findByCompanyEmail(authentication.getName());
        //without company user not access this so no need to check company are exist or not
        if (!company.isApproved()) {
            throw new IllegalArgumentException("Company is not approved please wait some days other wise send mail or contact support team");
        }
        Routes routes = new Routes();
        routes.setCompany(company);
        routes.setArrivalPoint(routsDTO.getArrivalPoint());
        routes.setDestinationPoint(routsDTO.getDestinationPoint());
        routes.setArrivalTime(routsDTO.getArrivalTime());
        routes.setKilometer(routsDTO.getKilometer());
        routeRepository.save(routes);
        return "Routs added successfully";
    }

    //method for get orders
    public List<GetOrderDTO> getOrder(Authentication authentication){
        Company company = companyRepository.findByCompanyEmail(authentication.getName());
        if (!company.isApproved()) {
            throw new IllegalArgumentException("Company is not approved please wait some days other wise send mail or contact support team");
        }
        List<Orders> orders = orderRepository.findByCompany_CidAndOrderStatus(company.getCid(), OrderStatus.Placed);
        List<GetOrderDTO> dtoList = new ArrayList<>();

        for (Orders order : orders) {

            GetOrderDTO dto = getOrderDTO(order);
            dtoList.add(dto);
        }
        return dtoList;
    }

    //method for get orders
    public List<GetOrderDTO> getAllOrder(Authentication authentication){
        Company company = companyRepository.findByCompanyEmail(authentication.getName());
        if (!company.isApproved()) {
            throw new IllegalArgumentException("Company is not approved please wait some days other wise send mail or contact support team");
        }
        List<Orders> orders = orderRepository.findByCompany_Cid(company.getCid());
        List<GetOrderDTO> dtoList = new ArrayList<>();

        for (Orders order : orders) {

            GetOrderDTO dto = getOrderDTO(order);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private static GetOrderDTO getOrderDTO(Orders order) {
        GetOrderDTO dto = new GetOrderDTO();

        dto.setOrderNumber(order.getOid());
        dto.setSenderName(order.getSender().getUsername());
        dto.setMobileNo(order.getSender().getMobileNo());
        dto.setReceiverName(order.getReceiverName());
        dto.setReceiverPhone(order.getReceiverPhone());
        dto.setDescription(order.getDescription());
        dto.setWeight(order.getWeight());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setEarning(order.getCompanyEarning());
        dto.setPlatformFees(order.getPlatformFees());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setPaymentMod(order.getPaymentMod());
        dto.setPaymentStatus(order.getPaymentStatus());
        dto.setArrivalPoint(order.getRoute().getArrivalPoint());
        dto.setDestinationPoint(order.getRoute().getDestinationPoint());
        dto.setKilometer(order.getRoute().getKilometer());
        return dto;
    }

    //accept order
    public void acceptOrder(Authentication authentication,long orderId){
        Orders orders = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("order not found"));
        orders.setOrderStatus(OrderStatus.Accept);
        orderRepository.save(orders);
    }

    //cancel order
    public void rejectOrder(Authentication authentication, long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setOrderStatus(OrderStatus.Cancelled);
        order.setCancelledAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    //Update Order Status
    public void updateOrderStatus(Authentication authentication, long orderId, String status) {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        OrderStatus orderStatus;
        try {
            orderStatus = OrderStatus.valueOf(status);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Order Status");
        }
        switch (orderStatus){
            case Delivered :
                order.setPaymentStatus(PaymentStatus.Paid);
                break;

            case Cancelled, Reject:
                order.setPaymentStatus(PaymentStatus.Failed);
                order.setCancelledAt(LocalDateTime.now());
                break;

            default:
                order.setPaymentStatus(PaymentStatus.Pending);
                order.setCancelledAt(null);
        }
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }

    //update routs
    public void updateRoute(Authentication authentication, @PathVariable Long routeId, @Valid @RequestBody AddRoutsDTO dto){
        Company company = companyRepository.findByCompanyEmail(authentication.getName());
        if (company == null) {
            throw new IllegalArgumentException("Company not found");
        }
        Routes route = routeRepository.findById(routeId).orElseThrow(() -> new IllegalArgumentException("Route not found"));
        //Make sure company owns this route
        if (!route.getCompany().getCid().equals(company.getCid())) {
            throw new IllegalArgumentException("Unauthorized to update this route");
        }
        // Update fields using DTO
        route.setArrivalPoint(dto.getArrivalPoint());
        route.setDestinationPoint(dto.getDestinationPoint());
        route.setKilometer(dto.getKilometer());
        route.setArrivalTime(dto.getArrivalTime());
        routeRepository.save(route);
    }

    //method for dashboard
    public CompanyDashboardDTO getDashboard(Authentication authentication) {

        Company company = companyRepository.findByCompanyEmail(authentication.getName());

        if (!company.isApproved()) {
            throw new IllegalArgumentException("Company not approved");
        }

        CompanyDashboardDTO dto = new CompanyDashboardDTO();
        dto.setTotalOrders(orderRepository.countByCompany_Cid(company.getCid()));
        dto.setPendingOrders(orderRepository.countByCompany_CidAndOrderStatus(company.getCid(), OrderStatus.Placed));
        dto.setAcceptedOrders(orderRepository.countByCompany_CidAndOrderStatus(company.getCid(), OrderStatus.Accept));
        dto.setCancelledOrders(orderRepository.countByCompany_CidAndOrderStatus(company.getCid(), OrderStatus.Cancelled));
        dto.setTotalIncome(orderRepository.getTotalIncome(company.getCid()));
        dto.setTotalPlatformFees(orderRepository.getTotalPlatformFeesByCompany(company.getCid()));

        return dto;
    }

    //method for fee summery
    public FeesSummaryDTO getFeesSummary(Authentication authentication) {
        Company company = companyRepository.findByCompanyEmail(authentication.getName());
        if (!company.isApproved()) {
            throw new IllegalArgumentException("Company not approved");
        }
        FeesSummaryDTO dto = new FeesSummaryDTO();
        dto.setTotalOrderAmount(orderRepository.getTotalOrderAmountByCompany(company.getCid()));
        dto.setTotalPlatformFees(orderRepository.getTotalPlatformFeesByCompany(company.getCid()));
        dto.setCompanyEarning(orderRepository.getCompanyEarningByCompany(company.getCid()));
        return dto;
    }

    public void deleteRoute(Authentication authentication, Long routeId) {
        String email = authentication.getName();
        Company company = companyRepository.findByCompanyEmail(email);

        if (company == null) {
            throw new IllegalArgumentException("Company not found");
        }

        Routes route = routeRepository.findById(routeId)
                .orElseThrow(() -> new IllegalArgumentException("Route not found"));

        if (!route.getCompany().getCid().equals(company.getCid())) {
            throw new IllegalArgumentException("You are not authorized to delete this route");
        }

        routeRepository.delete(route);

    }

}
