package com.micro.managerservice.services;

import java.util.List;

import com.micro.managerservice.models.Inventory;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface InventoryService {

    public String addInventory(@RequestParam int inventoryId, @RequestParam String inventoryName,@RequestParam String category, @RequestParam String roomType, @RequestParam String inventoryStatus);
    public String updateInventory(@PathVariable int inventoryId, @RequestParam String inventoryName,@RequestParam String category, @RequestParam String roomType, @RequestParam String inventoryStatus);
    public String deleteInventory(@PathVariable int inventoryId);
    public List<Inventory> findAllInventory();
}
