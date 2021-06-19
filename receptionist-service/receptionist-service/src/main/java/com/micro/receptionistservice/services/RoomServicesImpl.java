package com.micro.receptionistservice.services;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.*;
//import java.util.stream.Collectors;

import com.micro.receptionistservice.exception.EmptyInputException;
import com.micro.receptionistservice.models.AvailableRoom;
import com.micro.receptionistservice.models.Room;
import com.micro.receptionistservice.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoomServicesImpl implements RoomServices{

    Logger logger = LoggerFactory.getLogger(RoomServicesImpl.class);

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public AvailableRoom searchRoom(String status) {
        logger.info("Entered Service SearchRoom()");
        List<Room> allRooms = new ArrayList<>();
        for(var roomList:this.roomRepository.findAll()) {
            if(roomList.getStatus().isEmpty()) {
                throw new EmptyInputException("Rooms are not available!");
            }
            else if(roomList.getStatus().equals(status)) {
                allRooms.add(roomList);
            }
        }
        AvailableRoom availableRoom = new AvailableRoom(allRooms);
        return availableRoom ;
    }
    
}
