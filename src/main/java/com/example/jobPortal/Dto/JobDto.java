package com.example.jobPortal.Dto;

import com.example.jobPortal.Entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class JobDto {
   
    private Long id;
    @NotEmpty(message = "Please provide title of the job")
    private String title;
    @NotEmpty(message = "Please provide description of the job")
    private String description;
    @NotEmpty(message = "Please provide the location")
    private String location;
    @NotEmpty(message = "Please provide the company name")
    private String company;
    @NotNull(message = "Salary must not be null")
    @Min(value = 100, message = "Salary must be greater than 100")
    private Double salary;
    private Date createdAt;
    private Date updatedAt;
    private Long createdBy;

    public JobDto(Long id, String title, String description, String location, String company, Double salary, Date createdAt, Date updatedAt,Long createdBy) {
        this.id = id;
        this.title = title;
       this.description = description;
        this.location = location;
        this.company = company;
        this.salary = salary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public JobDto() {
    }
}
