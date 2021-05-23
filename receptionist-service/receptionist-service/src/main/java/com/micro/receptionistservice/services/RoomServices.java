package com.micro.receptionistservice.services;

import com.micro.receptionistservice.models.AvailableRoom;

import org.springframework.web.bind.annotation.RequestParam;

public interface RoomServices{
    public AvailableRoom searchRoom(@RequestParam String status);
    
}
