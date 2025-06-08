package com.example.jobPortal.Dto;

public class SpecificationDto {
	private String title;
	private String location;
	private String company;
	private Double minSalary;
	private Double maxSalary;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Double getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(Double minSalary) {
		this.minSalary = minSalary;
	}
	public Double getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(Double maxSalary) {
		this.maxSalary = maxSalary;
	}
	public SpecificationDto(String title, String location, String company, Double minSalary, Double maxSalary) {
		super();
		this.title = title;
		this.location = location;
		this.company = company;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
	}
	public SpecificationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
