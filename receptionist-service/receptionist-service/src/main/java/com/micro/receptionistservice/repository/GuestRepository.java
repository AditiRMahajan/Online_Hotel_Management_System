package com.micro.receptionistservice.repository;

import com.micro.receptionistservice.models.Guest;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends MongoRepository<Guest,ObjectId> {

    Guest findByGuestid(int guestid);
    Guest deleteByGuestid(int guestid);
    
}
