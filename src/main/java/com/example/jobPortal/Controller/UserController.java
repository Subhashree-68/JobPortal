package com.example.jobPortal.Controller;

import com.example.jobPortal.Dto.LoginRequest;
import com.example.jobPortal.Dto.RegistrationDto;
import com.example.jobPortal.Dto.Response;
import com.example.jobPortal.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody @Valid  RegistrationDto registrationDto){
        Response<?> response=userService.registration(registrationDto);
        return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatusCode()));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest){
        Response<?> response=userService.login(loginRequest);
        return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatusCode()));
    }
    @PostMapping("/welcome")
    public String Hello() {
    	return "Hello";
    }
  
   
}
