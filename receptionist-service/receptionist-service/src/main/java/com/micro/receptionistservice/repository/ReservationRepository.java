package com.micro.receptionistservice.repository;

import com.micro.receptionistservice.models.Reservation;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation,ObjectId>{
    Reservation findByReservationid(int reservationid);

}
