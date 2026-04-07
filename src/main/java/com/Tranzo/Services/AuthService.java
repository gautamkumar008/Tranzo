package com.Tranzo.Services;

import com.Tranzo.DTOS.*;
import com.Tranzo.Model.Company;
import com.Tranzo.Model.Enums.Role;
import com.Tranzo.Model.User;
import com.Tranzo.Repository.CompanyRepository;
import com.Tranzo.Repository.UserRepository;
import com.Tranzo.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AuthService {

    @Autowired private UserRepository userRepository;

    @Autowired private CompanyRepository companyRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private JwtUtil jwtUtil;


    //method for signup
    public String signup(SignupRequestDTO request){

        if (request == null ||
                request.getUsername() == null || request.getUsername().trim().isEmpty() ||
                request.getEmail() == null || request.getEmail().trim().isEmpty() ||
                request.getPassword() == null || request.getPassword().trim().isEmpty()) {

            throw new IllegalArgumentException("All fields are required");
        }

        if (userRepository.existsByEmail(request.getEmail())) throw new IllegalArgumentException("Email already exists");

        if (userRepository.existsByMobileNo(request.getMobileNo())) throw new IllegalArgumentException("Mobile already exists");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setMobileNo(request.getMobileNo());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setSecurityKey(generateKey());
        user.setRole(Role.USER); // Default USER role

        userRepository.save(user);

        return user.getSecurityKey();
    }

    //method for register-company
    public String companyRegister(CompanyRegisterDTO register){

        if (companyRepository.existsByCompanyEmail(register.getCompanyEmail())) throw new IllegalArgumentException("Email already exists");

        if (companyRepository.existsByContactNo(register.getContactNo())) throw new IllegalArgumentException("ContactNumber already exists");

        if (companyRepository.existsByRegisterNo(register.getRegisterNo())) throw new IllegalArgumentException("Company Already Register with this number");

        Company company = new Company();
        company.setCompanyName(register.getCompanyName());
        company.setRegisterNo(register.getRegisterNo());
        company.setGstNo(register.getGstNo());
        company.setCompanyEmail(register.getCompanyEmail());
        company.setContactNo(register.getContactNo());
        company.setPassword(passwordEncoder.encode(register.getPassword()));
        company.setCompanyServiceType(register.getCompanyServiceType());
        company.setCompanyType(register.getCompanyType());
        company.setMaxWeight(register.getMaxWeight());
        company.setPricePerKM(register.getPricePerKm());
        company.setRole(Role.COMPANY);
        company.setApproved(false); // Admin must approve
        companyRepository.save(company);
        return "Company registered successfully. Waiting for approval.";
    }

    //method for login
    public LoginResponseDTO login(LoginRequestDTO request){
        User user = userRepository.findByEmail(request.getEmail());

        if (user != null) {
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) throw new IllegalArgumentException("Invalid password");

            String token = jwtUtil.generateToken(user);
            return new LoginResponseDTO(token, user.getEmail(), user.getRole());
        }

        return companyLogin(request);
    }

    //method for company login
    private LoginResponseDTO companyLogin(LoginRequestDTO request){
        Company company = companyRepository.findByCompanyEmail(request.getEmail());

        if (company == null) throw new IllegalArgumentException("Account not found");

        if (!company.isApproved()) throw new IllegalArgumentException("Company not approved yet");

        if (!passwordEncoder.matches(request.getPassword(), company.getPassword())) throw new IllegalArgumentException("Invalid password");

        String token = jwtUtil.generateCompanyToken(company);
        return new LoginResponseDTO(token, company.getCompanyEmail(), company.getRole());
    }

    //method for forget-password (user)
    public void forgetPassword(ForgetPasswordRequestDTO request){
        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) throw new IllegalArgumentException("User not found");

        if (!user.getSecurityKey().equals(request.getSecurityKey())) throw new IllegalArgumentException("Invalid security key");

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    //method for generate security key
    public static String generateKey() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&*!";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(15); // 15 characters

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
}
