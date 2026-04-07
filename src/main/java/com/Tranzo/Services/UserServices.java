package com.Tranzo.Services;

import com.Tranzo.DTOS.*;
import com.Tranzo.Model.Company;
import com.Tranzo.Model.Enums.OrderStatus;
import com.Tranzo.Model.Orders;
import com.Tranzo.Model.Routes;
import com.Tranzo.Model.User;
import com.Tranzo.Repository.CompanyRepository;
import com.Tranzo.Repository.OrderRepository;
import com.Tranzo.Repository.RouteRepository;
import com.Tranzo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {

    @Autowired private UserRepository userRepository;
    @Autowired private CompanyRepository companyRepository;
    @Autowired private RouteRepository routeRepository;
    @Autowired private OrderRepository orderRepository;


    //method for get Profile
    public UserProfileDTO getProfile(Authentication authentication){
        User user = userRepository.findByEmail(authentication.getName());
        if (user == null) throw new IllegalArgumentException("user not found");
        UserProfileDTO response = new UserProfileDTO();
        response.setUserName(user.getUsername());
        response.setEmail(user.getEmail());
        response.setMobileNo(user.getMobileNo());
        return response;
    }

    //method for find company profile
    public CompanyDetailsDTO getCompanyByIdForUser(String name){
        Company company = companyRepository.findByCompanyEmail(name);
        if (company == null) throw new IllegalArgumentException("Company not found");
        // Fetch routes for company
        List<Routes> routes = routeRepository.findByCompany(company);
        List<RoutesDTO> routesDTOList = new ArrayList<>();

        for (Routes route : routes) {

            RoutesDTO routesDTO = new RoutesDTO();
            routesDTO.setRouteId(route.getRid());
            routesDTO.setArrivalPoint(route.getArrivalPoint());
            routesDTO.setDestinationPoint(route.getDestinationPoint());
            routesDTO.setKilometer(route.getKilometer());
            routesDTO.setArrivalTime(route.getArrivalTime());

            routesDTOList.add(routesDTO);
        }
        return getCompanyDetailsDTO(company, routesDTOList);
    }


    //method for find company profile
    public CompanyDetailsDTO getMyProfile(Authentication authentication){
        Company company = companyRepository.findByCompanyEmail(authentication.getName());
        if (company == null) throw new IllegalArgumentException("Company not found");
        // Fetch routes for company
        List<Routes> routes = routeRepository.findByCompany(company);
        List<RoutesDTO> routesDTOList = new ArrayList<>();

        for (Routes route : routes) {

            RoutesDTO routesDTO = new RoutesDTO();
            routesDTO.setRouteId(route.getRid());
            routesDTO.setArrivalPoint(route.getArrivalPoint());
            routesDTO.setDestinationPoint(route.getDestinationPoint());
            routesDTO.setKilometer(route.getKilometer());
            routesDTO.setArrivalTime(route.getArrivalTime());

            routesDTOList.add(routesDTO);
        }
        return getCompanyDetailsDTO(company, routesDTOList);
    }

    // method for get company
    public List<CompanyDetailsDTO> getCompany() {

        List<CompanyDetailsDTO> companyDTOList = new ArrayList<>();

        List<Company> companies = companyRepository.findAll();

        for (Company company : companies) {

            // Fetch routes for company
            List<Routes> routes = routeRepository.findByCompany(company);
            List<RoutesDTO> routesDTOList = new ArrayList<>();

            for (Routes route : routes) {

                RoutesDTO routesDTO = new RoutesDTO();
                routesDTO.setRouteId(route.getRid());
                routesDTO.setArrivalPoint(route.getArrivalPoint());
                routesDTO.setDestinationPoint(route.getDestinationPoint());
                routesDTO.setKilometer(route.getKilometer());
                routesDTO.setArrivalTime(route.getArrivalTime());

                routesDTOList.add(routesDTO);
            }

            // Create company DTO
            CompanyDetailsDTO companyDTO = getCompanyDetailsDTO(company, routesDTOList);

            companyDTOList.add(companyDTO);
        }

        return companyDTOList;
    }


    private static CompanyDetailsDTO getCompanyDetailsDTO(Company company, List<RoutesDTO> routesDTOList) {
        CompanyDetailsDTO companyDTO = new CompanyDetailsDTO();
        companyDTO.setCid(company.getCid());
        companyDTO.setCompanyName(company.getCompanyName());
        companyDTO.setCompanyEmail(company.getCompanyEmail());
        companyDTO.setContactNo(company.getContactNo());
        companyDTO.setCompanyServiceType(company.getCompanyServiceType());
        companyDTO.setCompanyType(company.getCompanyType());
        companyDTO.setMaxWeight(company.getMaxWeight());
        companyDTO.setPricePerKM(company.getPricePerKM());
        companyDTO.setRoutes(routesDTOList);
        return companyDTO;
    }

    //method for place order
    public OrderResponseDTO placeOrder(Authentication authentication, PlaceOrderRequestDTO request){
        // 1 Get logged-in user (sender)
        User sender = userRepository.findByEmail(authentication.getName());
        if (sender == null) throw new IllegalArgumentException("Sender not found");

        // 2 Get route from routeId
        Routes route = routeRepository.findById(request.getRouteId()).orElseThrow(() -> new IllegalArgumentException("Route not found"));

        // 3 Get company from route
        Company company = route.getCompany();

        // 4 Check weight against company max weight
        if (request.getWeight() > company.getMaxWeight()) throw new IllegalArgumentException("Weight exceeds company limit");

        // 5 Calculate price
        double totalPrice = round2(route.getKilometer() * company.getPricePerKM());
        double platformFees = round2((totalPrice * company.getCommissionRate()) / 100.0);
        double companyEarning =round2(totalPrice - platformFees);

        // 6 Generate random 8-digit tracking number
        String trackingNo = generateTrackingNo();

        // 7 Create Orders object
        Orders order = new Orders();
        order.setSender(sender);
        order.setCompany(company);
        order.setReceiverName(request.getReceiverName());
        order.setReceiverPhone(request.getReceiverPhone());
        order.setDescription(request.getDescription());
        order.setWeight(request.getWeight());
        order.setPaymentMod(request.getPaymentMod());
        order.setCompanyEarning(companyEarning);
        order.setPlatformFees(platformFees);
        order.setTotalPrice(totalPrice);
        order.setTrackingNo(trackingNo);
        order.setRoute(route);
        // 8 Save order
        orderRepository.save(order);

        return new OrderResponseDTO(order.getTrackingNo(),order.getTotalPrice(),order.getOrderStatus());
    }

    // random 8-digit tracking number generator
    private static String generateTrackingNo() {
        int number = (int)(Math.random() * 90000000) + 10000000; // 8-digit random
        return "TRK" + number;
    }

    private static double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    //method for tracking number
    public OrderTrackingResponseDTO trackOrder(String trackingNumber){
        Orders orders = orderRepository.findByTrackingNo(trackingNumber);
        if (orders == null) throw new IllegalArgumentException("order not found");
        OrderTrackingResponseDTO response = new OrderTrackingResponseDTO();
        response.setOrderNumber(orders.getOid());
        response.setOrderStatus(orders.getOrderStatus());
        response.setPaymentMod(orders.getPaymentMod());
        response.setPaymentStatus(orders.getPaymentStatus());
        response.setWeight(orders.getWeight());
        response.setTotalPrice(orders.getTotalPrice());
        response.setReceiverName(orders.getReceiverName());
        return response;
    }

    //method for cancel order
    public void cancelOrder(Authentication authentication,Long orderId) {

        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));

        //Security check (order belongs to logged-in user)
        if (!order.getSender().getEmail().equals(authentication.getName())) {
            throw new IllegalArgumentException("Unauthorized to cancel this order");
        }

        //Cancellation rules
        if (order.getOrderStatus() == OrderStatus.Delivered) {
            throw new IllegalArgumentException("Delivered order cannot be cancelled");
        }

        if (order.getOrderStatus() == OrderStatus.Cancelled) {
            throw new IllegalArgumentException("Order already cancelled");
        }
        //Cancel order
        order.setOrderStatus(OrderStatus.Cancelled);
        order.setCancelledAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    public void deleteUserById(long id) {
        // Find user or throw exception if not found
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        // Delete user
        userRepository.deleteById(user.getUid());
    }

}
