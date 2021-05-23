package com.micro.receptionistservice.repository;

import com.micro.receptionistservice.models.Payment;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Payment,ObjectId> {
    
}
