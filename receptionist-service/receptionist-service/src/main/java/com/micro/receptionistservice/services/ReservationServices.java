package com.micro.receptionistservice.services;

//import java.time.LocalDateTime;
import java.util.List;

import com.micro.receptionistservice.models.Reservation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReservationServices {
    public String makeReservation(@PathVariable int roomNumber,@PathVariable int guestid,
                        @RequestParam int numberOfChildrens,@RequestParam int numberOfAdults, @RequestParam int numberOfDays,
                        @RequestParam double rate, @RequestParam String checkInDate,@RequestParam String checkOutDate);
    public Reservation findByReservationid(@PathVariable int reservationid);
    public List<Reservation> viewAllReservations();
}
