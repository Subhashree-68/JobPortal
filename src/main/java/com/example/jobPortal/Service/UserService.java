package com.example.jobPortal.Service;

import com.example.jobPortal.Dto.LoginRequest;
import com.example.jobPortal.Dto.RegistrationDto;
import com.example.jobPortal.Dto.Response;
import jakarta.validation.Valid;

public interface UserService {
    Response<?> registration(@Valid RegistrationDto registrationDto);

	Response<?> login(@Valid LoginRequest loginRequest);
}
