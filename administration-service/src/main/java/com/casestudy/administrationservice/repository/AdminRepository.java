package com.casestudy.administrationservice.repository;

import com.casestudy.administrationservice.model.Admin;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin,Integer> {
    Admin findByUsername(String username);
}
