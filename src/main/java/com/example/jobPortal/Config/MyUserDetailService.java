package com.example.jobPortal.Config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jobPortal.Entity.User;
import com.example.jobPortal.Entity.UserPrincipal;
import com.example.jobPortal.Repository.UserRepository;
@Service
public class MyUserDetailService implements UserDetailsService{
@Autowired
UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optionalUser= userRepository.findByEmail(email);
		if(optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		return new UserPrincipal(optionalUser.get());
	}

}
