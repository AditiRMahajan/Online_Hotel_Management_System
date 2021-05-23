package com.micro.managerservice.repository;

import com.micro.managerservice.models.Manager;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends MongoRepository<Manager,Integer> {
    Manager findByUsername(String username);
}
