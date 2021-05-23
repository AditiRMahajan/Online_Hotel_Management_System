package com.micro.managerservice.repository;

import com.micro.managerservice.models.Room;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room,ObjectId>{
    Room findByRoomNumber(int roomNumber);
    Room deleteByRoomNumber(int roomNumber);
}
