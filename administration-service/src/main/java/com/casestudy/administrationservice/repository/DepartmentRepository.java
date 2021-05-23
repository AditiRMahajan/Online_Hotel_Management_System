package com.casestudy.administrationservice.repository;

import com.casestudy.administrationservice.model.Departments;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<Departments,ObjectId> {

    Departments findByDepartmentId(int departmentId);
    Departments deleteByDepartmentId(int departmentId);
    
}
