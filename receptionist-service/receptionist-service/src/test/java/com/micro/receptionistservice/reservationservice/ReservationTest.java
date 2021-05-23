package com.micro.receptionistservice.reservationservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.micro.receptionistservice.models.Reservation;
import com.micro.receptionistservice.models.Guest;
import com.micro.receptionistservice.models.Room;
import com.micro.receptionistservice.services.ReservationServicesImpl;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class ReservationTest {

    ReservationServicesImpl reserveServiceImplMock = mock(ReservationServicesImpl.class);

    @Test
    @DisplayName("Test for makeReservation")
    public void makeReservationTest(){
        when(reserveServiceImplMock.makeReservation(1, 1, 1, 1, 1, 1000, "25-05-2021", "27-05-2021")).thenReturn("Reservation id is:" +1);
        assertEquals("Reservation id is:" +1,reserveServiceImplMock.makeReservation(1, 1, 1, 1, 1, 1000, "25-05-2021", "27-05-2021"));
    }

    @Test
    @DisplayName("Test for viewAllReservation")
    public void viewAllReservationTest(){
        when(reserveServiceImplMock.viewAllReservations()).thenReturn(Stream
        .of(new Reservation(null, 1, new Guest(null, 1,"Poonam", "86004234516", "poo1@gmail.com", "Female", "Pune"), new Room(null, 100, "Deluxe", "Available", "21-05-2021", "27-05-2021"), 101, 1, 2, "pending", 2, 3000),
            new Reservation(null, 1, new Guest(null, 2,"Poonam", "86004234516", "poo1@gmail.com", "Female", "Pune"), new Room(null, 101, "Royal", "Available", "21-05-2021", "27-05-2021"), 101, 1, 2, "pending", 2, 3000))
        .collect(Collectors.toList()));
        assertEquals(2, reserveServiceImplMock.viewAllReservations().size());
    }

    @Test
    @DisplayName("Test for viewReservation")
    public void viewReservationTest(){
        Reservation reservation = new Reservation(null, 1, new Guest(null, 1,"Poonam", "86004234516", "poo1@gmail.com", "Female", "Pune"), new Room(null, 100, "Deluxe", "Available", "21-05-2021", "27-05-2021"), 101, 1, 2, "pending", 2, 3000);
        when(reserveServiceImplMock.findByReservationid(1)).thenReturn(reservation);
        assertEquals(reservation
        , reserveServiceImplMock.findByReservationid(1));
    }
    
}
