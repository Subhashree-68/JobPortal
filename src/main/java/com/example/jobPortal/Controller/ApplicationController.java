package com.example.jobPortal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobPortal.Dto.ApplicationDto;
import com.example.jobPortal.Dto.Response;
import com.example.jobPortal.Service.ApplicationService;

@RestController
@RequestMapping("application")
public class ApplicationController {
	@Autowired
	ApplicationService applicationService;

	@PostMapping("/apply/job")
	public ResponseEntity<?> applyForJob(@RequestBody ApplicationDto applicationDto) {
		Response<?> response = applicationService.applyForJob(applicationDto);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
	}

	@GetMapping("/view/all")
	public ResponseEntity<?> viewAllApplication() {
		Response<?> response = applicationService.viewAllApplication();
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
	}

}
