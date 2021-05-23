package com.micro.managerservice.repository;

import com.micro.managerservice.models.Staffs;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends MongoRepository<Staffs,ObjectId>{
    Staffs findByStaffid(int staffid);
    Staffs deleteByStaffid(int staffid);
    
}
