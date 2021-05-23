package com.casestudy.administrationservice.reportsservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
 
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.casestudy.administrationservice.model.Reservation;
import com.casestudy.administrationservice.model.Guest;
import com.casestudy.administrationservice.model.Room;
import com.casestudy.administrationservice.model.Staffs;
import com.casestudy.administrationservice.service.ReportsServiceImpl;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class ReportsTest {

    ReportsServiceImpl reportsServiceImplMock = mock(ReportsServiceImpl.class);

    @Test
    @DisplayName("Test for viewRoomReports")
    public void viewRoomReportsTest(){
        when(reportsServiceImplMock.viewRoomReports()).thenReturn(Stream
        .of(new Room(100, "Deluxe", "Available"), new Room(101, "Royal", "Available"))
        .collect(Collectors.toList()));
        assertEquals(2, reportsServiceImplMock.viewRoomReports().size());
    }

    @Test
    @DisplayName("Test for viewStaffReports")
    public void viewStaffReportsTest(){
        when(reportsServiceImplMock.viewStaffReports()).thenReturn(Stream
        .of(new Staffs(11, "Advait", 200, 25000, 25, "BA", "ad12@gmail.com"), new Staffs(12, "Aditya", 200, 25000, 25, "BA", "adi12@gmail.com"))
        .collect(Collectors.toList()));
        assertEquals(2, reportsServiceImplMock.viewStaffReports().size());
    }

    @Test
    @DisplayName("Test for viewGuestReports")
    public void viewGuestReportsTest(){
        when(reportsServiceImplMock.viewGuestDetails()).thenReturn(Stream
        .of(new Guest(1,"Poonam", "86004234516", "poo1@gmail.com", "Female", "Pune"), new Guest(2,"Poonam", "86004234516", "poo1@gmail.com", "Female", "Pune"))
        .collect(Collectors.toList()));
        assertEquals(2, reportsServiceImplMock.viewGuestDetails().size());
    }

    @Test
    @DisplayName("Test for viewReservationReports")
    public void viewReservationReportsTest(){
        when(reportsServiceImplMock.viewReservationDetails()).thenReturn(Stream
        .of(new Reservation(1, new Guest(1,"Poonam", "86004234516", "poo1@gmail.com", "Female", "Pune"), new Room(100, "Deluxe", "Available"), 101, 1, 2, "pending", 2, 3000),
            new Reservation(1, new Guest(2,"Poonam", "86004234516", "poo1@gmail.com", "Female", "Pune"), new Room(101, "Royal", "Available"), 101, 1, 2, "pending", 2, 3000))
        .collect(Collectors.toList()));
        assertEquals(2, reportsServiceImplMock.viewReservationDetails().size());
    }
    
}
