package com.micro.receptionistservice.services;

import java.util.List;
import org.slf4j.*;

import com.micro.receptionistservice.exception.EmptyInputException;
import com.micro.receptionistservice.exception.IdNotFoundException;
import com.micro.receptionistservice.models.Guest;
import com.micro.receptionistservice.repository.GuestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServicesImpl implements GuestServices{

    Logger logger = LoggerFactory.getLogger(GuestServicesImpl.class);

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public String addGuest(String name, String phoneNumber, String emailid, String gender,
            String address) {
        logger.info("Entered Service AddGuest()");
        Guest newguest = new Guest();
        newguest.setGuestid(this.guestRepository.findAll().size()+1);
        newguest.setName(name);
        newguest.setPhoneNumber(phoneNumber);
        newguest.setEmailid(emailid);
        newguest.setGender(gender);
        newguest.setAddress(address);
        this.guestRepository.save(newguest);
        return "Guest with id " +newguest.getGuestid() + " added successfully";
    }

    @Override
    public String updateGuest(int guestid, String name, String phoneNumber, String emailid, String gender,
            String address) {
        logger.info("Entered Service UpdateGuest()");
        try{
            Guest updateguest = this.guestRepository.findByGuestid(guestid);
            updateguest.setName(name);
            updateguest.setPhoneNumber(phoneNumber);
            updateguest.setEmailid(emailid);
            updateguest.setGender(gender);
            updateguest.setAddress(address);
            this.guestRepository.save(updateguest);
        } catch(Exception e) {
            throw new IdNotFoundException("Department Id not found");
        }
        return "Guest with id "+ guestid + " successfully updated!";
    }

    @Override
    public String deleteGuest(int guestid) {
        logger.info("Entered Service DeleteGuest()");
        try{
            this.guestRepository.deleteByGuestid(guestid);
        } catch(Exception e) {
            throw new IdNotFoundException("Guest not found");
        }
        return "GuestId "+ guestid +" successfully deleted";
    }

    @Override
    public List<Guest> viewAllGuests() {
        logger.info("Entered Service UpdateGuest()");
        List<Guest> guestList = this.guestRepository.findAll();
        if(guestList.isEmpty())
              throw new EmptyInputException("Staff list is empty");
        return guestList;
    }
    
}
