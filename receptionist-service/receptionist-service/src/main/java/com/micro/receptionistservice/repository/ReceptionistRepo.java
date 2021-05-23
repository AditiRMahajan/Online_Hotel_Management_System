package com.micro.receptionistservice.repository;

import com.micro.receptionistservice.models.Receptionist;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionistRepo extends MongoRepository<Receptionist,Integer> {
    Receptionist findByUsername(String username);
    
}
