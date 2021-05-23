package com.casestudy.administrationservice.service;

import java.util.List;
import org.slf4j.*;

import com.casestudy.administrationservice.exception.EmptyInputException;
import com.casestudy.administrationservice.exception.IdNotFoundException;
import com.casestudy.administrationservice.model.Departments;
import com.casestudy.administrationservice.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public String addDepartment(int departmentId, String departmentName, String departmentType,
            String departmentIncharge) {
                logger.info("Entered Service addDepartment()");

                Departments departments = new Departments();
                departments.setDepartmentId(departmentId);
                departments.setDepartmentName(departmentName);
                departments.setDepartmentType(departmentType);
                departments.setDepartmentIncharge(departmentIncharge);
                this.departmentRepository.save(departments);
                return "Deparment " + departmentName + " added successfully !";
    }

    @Override
    public String updateDepartment(int departmentId, String departmentName, String departmentType,
            String departmentIncharge) {
                logger.info("Entered Service updateDepartment()");
                try{
                    Departments departments = this.departmentRepository.findByDepartmentId(departmentId);
                    departments.setDepartmentType(departmentType);
                    departments.setDepartmentIncharge(departmentIncharge);
                    this.departmentRepository.save(departments);
                } catch(Exception e) {
                    throw new IdNotFoundException("Department Id not found");
                }
                return "Deparment updated successfully for "+departmentId +"!";
    }

    @Override
    public String deleteDepartment(int departmentId) {
        logger.info("Entered Service deleteDepartment()");
        try{
            this.departmentRepository.deleteByDepartmentId(departmentId);
        } catch(Exception e) {
            throw new IdNotFoundException("Staff not found");
        }
        return "Deparment is deleted for "+departmentId;
    }

    @Override
    public List<Departments> viewDepartments() {
        logger.info("Entered Service viewDepartments()");
        List<Departments> deptList = departmentRepository.findAll();
        if(deptList.isEmpty())
              throw new EmptyInputException("Department list is empty");
        return deptList;
    }
    
}
