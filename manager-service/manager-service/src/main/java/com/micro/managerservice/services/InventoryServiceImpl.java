package com.micro.managerservice.services;

import org.slf4j.*;

import java.util.List;

import com.micro.managerservice.exception.EmptyInputException;
import com.micro.managerservice.exception.IdNotFoundException;
import com.micro.managerservice.models.Inventory;
import com.micro.managerservice.repository.InventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public String addInventory(int inventoryId, String inventoryName, String category, String roomType,
            String inventoryStatus) {
                logger.info("Entered Service addInventory()");

                Inventory inventory = new Inventory();
                inventory.setInventoryId(inventoryId);
                inventory.setInventoryName(inventoryName);
                inventory.setCategory(category);
                inventory.setRoomType(roomType);
                inventory.setInventoryStatus(inventoryStatus);
                this.inventoryRepository.save(inventory);
                return "Inventory " + inventoryName + " added successfully!";
    }

    @Override
    public String updateInventory(int inventoryId, String inventoryName, String category, String roomType,
            String inventoryStatus) {
                logger.info("Entered Service updateInventory()");
                try{
                    Inventory inventory = this.inventoryRepository.findByInventoryId(inventoryId);
                    inventory.setInventoryName(inventoryName);
                    inventory.setCategory(category);
                    inventory.setRoomType(roomType);
                    inventory.setInventoryStatus(inventoryStatus);
                    this.inventoryRepository.save(inventory);
                } catch(Exception e) {
                    throw new IdNotFoundException("Inventory Id not found");
                }
                return "Inventory is updated for " + inventoryId;
       
    }

    @Override
    public String deleteInventory(int inventoryId) {
        logger.info("Entered Service deleteInventory()");
        try{
            this.inventoryRepository.deleteByInventoryId(inventoryId);
        } catch(Exception e) {
            throw new IdNotFoundException("Inventory Id not found");
        }
        return "Inventory is deleted for " + inventoryId;
    }

    @Override
    public List<Inventory> findAllInventory() {
        logger.info("Entered Service findAllInventory()");
        List<Inventory> inventoryList = this.inventoryRepository.findAll();
        if(inventoryList.isEmpty())
          throw new EmptyInputException("Inventory list is empty");
        return inventoryList;
    }
    
}
