package com.casestudy.administrationservice.service;

import java.util.List;

import com.casestudy.administrationservice.model.Departments;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface DepartmentService {

    public String addDepartment(@RequestParam int departmentId, @RequestParam String departmentName, @RequestParam String departmentType, @RequestParam String departmentIncharge);
    public String updateDepartment(@PathVariable int departmentId, @RequestParam String departmentName, @RequestParam String departmentType, @RequestParam String departmentIncharge);
    public String deleteDepartment(@PathVariable int departmentId);
    public List<Departments> viewDepartments();
    
}
