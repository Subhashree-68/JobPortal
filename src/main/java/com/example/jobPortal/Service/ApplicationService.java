package com.example.jobPortal.Service;

import com.example.jobPortal.Dto.ApplicationDto;
import com.example.jobPortal.Dto.Response;

public interface ApplicationService {

	Response<?> applyForJob(ApplicationDto applicationDto);

	Response<?> viewAllApplication();
}
