package com.Tranzo.Controller;

import com.Tranzo.APIResponse.ApiResponse;
import com.Tranzo.DTOS.CompanyRegisterDTO;
import com.Tranzo.DTOS.ForgetPasswordRequestDTO;
import com.Tranzo.DTOS.LoginRequestDTO;
import com.Tranzo.DTOS.SignupRequestDTO;
import com.Tranzo.Services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authentication")
public class AuthController {

    @Autowired private AuthService authService;

    //api for user signup
    //http://localhost:8080/authentication/signup
    /*IMPORTANT: (frontend send to user)
    This is your Security Key for password recovery:
    {SECURITY_KEY}
    Please save this key in a safe place.
    This key will be shown only once and cannot be recovered again.
    If you lose it, you will not be able to reset your password.*/
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDTO request){
        return ResponseEntity.ok(ApiResponse.success("signup successfully",authService.signup(request)));
    }

    //api for register-company
    //http://localhost:8080/authentication/registerCompany
    @PostMapping("/registerCompany")
    public ResponseEntity<?> registerCompany(@Valid @RequestBody CompanyRegisterDTO request){
        return ResponseEntity.ok(ApiResponse.success("register successfully",authService.companyRegister(request)));
    }

    //api for login
    //http://localhost:8080/authentication/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(ApiResponse.success("login successfully",authService.login(request)));
    }

    //api for forget-password
    //http://localhost:8080/authentication/forgetPassword
    @PostMapping("/forgetPassword")
    public ResponseEntity<?> forgetPassword(@Valid @RequestBody ForgetPasswordRequestDTO request) {
        authService.forgetPassword(request);
        return ResponseEntity.ok(ApiResponse.success("Password reset successfully", null));
    }
}
