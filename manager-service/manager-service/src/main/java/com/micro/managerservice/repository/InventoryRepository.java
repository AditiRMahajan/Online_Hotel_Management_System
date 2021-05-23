package com.micro.managerservice.repository;

import com.micro.managerservice.models.Inventory;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, ObjectId> {
    Inventory findByInventoryId(int inventoryId);
    Inventory deleteByInventoryId(int inventoryId);
}
