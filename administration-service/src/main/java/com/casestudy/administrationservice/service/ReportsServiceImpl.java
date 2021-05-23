package com.casestudy.administrationservice.service;

import java.util.*;
import org.slf4j.*;

import com.casestudy.administrationservice.model.Reservation;
import com.casestudy.administrationservice.model.Guest;
import com.casestudy.administrationservice.model.Room;
import com.casestudy.administrationservice.model.Staffs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReportsServiceImpl implements ReportsService {

    Logger logger = LoggerFactory.getLogger(ReportsServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Staffs> viewStaffReports() {
        logger.info("Entered Service viewStaffReports()");
        //List<staff> newStaff = new ArrayList<>();
        var staff1 = this.restTemplate.getForObject("http://Management-Service/manager/viewstaff" ,List.class);
        //var s = staff1.getStaffReport().stream().collect(Collectors.toList());
        System.out.println(staff1);
        return staff1;

    }

    @Override
    public List<Room> viewRoomReports() {
        logger.info("Entered Service viewRoomDetails()");
        var room1 = this.restTemplate.getForObject("http://Management-Service/manager/viewrooms" ,List.class);
        return room1;
    }

    @Override
    public List<Guest> viewGuestDetails() {
        logger.info("Entered Service viewGuestDetails()");
        var guest1 = this.restTemplate.getForObject("http://RESERVATION-SERVICE/receptionist/viewall-guests" ,List.class);
        return guest1;
    }

    @Override
    public List<Reservation> viewReservationDetails() {
        logger.info("Entered Service viewvDetails()");
        var reservation1 = this.restTemplate.getForObject("http://RESERVATION-SERVICE/receptionist/viewall-reservations" ,List.class);
        return reservation1;
    }
    
}
