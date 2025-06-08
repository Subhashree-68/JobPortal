package com.example.jobPortal.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.jobPortal.Entity.Job;

@Component
public class JobSpecification  {
	public Specification<Job> containsTitle(String title){
		return(root,query,criteriaBuilder)->criteriaBuilder.
				like(criteriaBuilder.lower(root.get("title")),"%"+title.toLowerCase()+"%");
	}
	public Specification<Job> hasLocation(String location){
		return(root,query,criteriaBuilder)->criteriaBuilder.equal(root.get("location"), location);
	}
	public Specification<Job> hasCompany(String company){
		return(root,query,criteriaBuilder)->criteriaBuilder.equal(root.get("company"), company);
	}
	public Specification<Job> hasSalaryGreaterThan(Double salary){
		return (root,query,criteriaBuilder)->criteriaBuilder.greaterThan(root.get("salary"), salary);
	}
	public Specification<Job> hasSalaryLessThan(Double salary){
		return (root,query,criteriaBuilder)->criteriaBuilder.lessThan(root.get("salary"), salary);
	}
	
}
