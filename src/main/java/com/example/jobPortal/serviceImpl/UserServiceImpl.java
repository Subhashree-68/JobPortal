package com.example.jobPortal.serviceImpl;

import com.example.jobPortal.Config.JwtService;
import com.example.jobPortal.Dto.LoginRequest;
import com.example.jobPortal.Dto.RegistrationDto;
import com.example.jobPortal.Dto.Response;
import com.example.jobPortal.Entity.User;
import com.example.jobPortal.Enum.Role;
import com.example.jobPortal.Repository.UserRepository;
import com.example.jobPortal.Service.UserService;
import com.example.jobPortal.exception.UserNotFoundException;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(10);
  @Autowired
  EmailServiceImpl emailService;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public Response<?> registration(RegistrationDto registrationDto) {
        if(registrationDto.getId()==null){
            User user = new User();
            user.setUserName(registrationDto.getUserName());
            user.setEmail(registrationDto.getEmail());
            user.setRole(registrationDto.getRole());
            user.setPassword(bCryptPasswordEncoder.encode(registrationDto.getPassword()));
            user.setCreatedAt(new Date(System.currentTimeMillis()));
            userRepository.save(user);
            emailService.sendEmailForRegisterUser(user);
            return new Response<>(HttpStatus.OK.value(), "User registation successful",null);

        }else{
            Optional<User> optionalUser = userRepository.findById(registrationDto.getId());
            if(optionalUser.isEmpty()){
                throw new UserNotFoundException("User not found");
            }
            User user = optionalUser.get();
            user.setUserName(registrationDto.getUserName());
            user.setEmail(registrationDto.getEmail());
            user.setRole(registrationDto.getRole());
            user.setPassword(bCryptPasswordEncoder.encode(registrationDto.getPassword()));
            user.setUpdatedAt(new Date());
            userRepository.save(user);
            return new Response<>(HttpStatus.OK.value(), "User updated successfully",null);
        }
    }

	@Override
	public Response<?> login(LoginRequest loginRequest) {
		Authentication authentication=authenticationManager.authenticate
				(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			String token=jwtService.generateToken(loginRequest.getEmail());
            return new Response<>(HttpStatus.OK.value(), "Login successfully",token);

		}
        return new Response<>(HttpStatus.BAD_REQUEST.value(), "Login failed",null);
	}
	
}
