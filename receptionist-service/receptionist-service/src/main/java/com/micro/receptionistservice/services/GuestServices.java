package com.micro.receptionistservice.services;

import java.util.List;

import com.micro.receptionistservice.models.Guest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface GuestServices {
    public String addGuest(@RequestParam String name,
                            @RequestParam String phoneNumber,@RequestParam String emailid,@RequestParam String gender,
                            @RequestParam String address);

    public String updateGuest(@PathVariable int guestid, @RequestParam String name,
                            @RequestParam String phoneNumber,@RequestParam String emailid,@RequestParam String gender,
                            @RequestParam String address );

    public String deleteGuest(@PathVariable int guestid);
    public List<Guest> viewAllGuests();
}
