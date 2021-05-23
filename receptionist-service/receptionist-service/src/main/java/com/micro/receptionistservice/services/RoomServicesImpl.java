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
        for(var i:this.roomRepository.findAll()) {
            if(i.getStatus().isEmpty()) {
                throw new EmptyInputException("Rooms are not available!");
            }
            else if(i.getStatus().equals(status)) {
                allRooms.add(i);
            }
        }
        AvailableRoom p = new AvailableRoom(allRooms);
        return p;
    }
    
}
