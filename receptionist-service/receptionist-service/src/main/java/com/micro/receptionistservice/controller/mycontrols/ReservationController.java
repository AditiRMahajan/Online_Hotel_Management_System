package com.micro.receptionistservice.controller.mycontrols;

import java.util.*;
import org.slf4j.*;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.micro.receptionistservice.models.AuthenticationRequest;
import com.micro.receptionistservice.models.AuthenticationResponse;
import com.micro.receptionistservice.models.AvailableRoom;
import com.micro.receptionistservice.models.Receptionist;
import com.micro.receptionistservice.models.Reservation;
import com.micro.receptionistservice.models.Guest;
import com.micro.receptionistservice.repository.ReceptionistRepo;
import com.micro.receptionistservice.services.GuestServices;
import com.micro.receptionistservice.services.MyUserDetailsService;
import com.micro.receptionistservice.services.PaymentService;
import com.micro.receptionistservice.services.ReservationServices;
import com.micro.receptionistservice.services.RoomServices;
import com.micro.receptionistservice.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receptionist")
public class ReservationController {

    Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private GuestServices guestServices;

    @Autowired
    private RoomServices roomServices;

    @Autowired
    private ReservationServices reservationService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    ReceptionistRepo receptionistRepo;

    @Autowired
    JwtUtil jwtTokenUtil;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/logindata")
    public String logindata(@RequestBody Receptionist receptionist){
        this.receptionistRepo.save(receptionist);
        return "Data added succesfully";
    }
    
    @PostMapping("/addguest")
    public String addGuest(@RequestParam @Valid String name,
                            @RequestParam @Valid String phonenumber, @RequestParam @Valid String emailid, @RequestParam @Valid String gender,
                            @RequestParam @Valid String address){
        logger.info("Entered AddGuest()");
        return this.guestServices.addGuest(name, phonenumber, emailid, gender, address);

    }

    @PutMapping("/updateguest/{guestid}")
    public String updateGuest(@PathVariable int guestid, @RequestParam @Valid String name,
                                    @RequestParam @Valid String phonenumber, @RequestParam @Valid String emailid, @RequestParam @Valid String gender,
                                    @RequestParam @Valid String address ){
        logger.info("Entered UpdateGuest()");
        return this.guestServices.updateGuest(guestid, name, phonenumber, emailid, gender, address);
    }

    @DeleteMapping("/deleteguest/{guestid}")
    public String deleteGuest(@PathVariable int guestid){
        logger.info("Entered DeleteGuest()");
        return this.guestServices.deleteGuest(guestid);
    }

    @GetMapping("/viewall-guests")
    public List<Guest> viewAllGuests() {
        logger.info("Entered viewAllGuests()");
        return this.guestServices.viewAllGuests();
    }

    // ************************Search Rooms*******************************//

    @GetMapping("/availableroom")
    public AvailableRoom searchRoom(@RequestParam String status){
        logger.info("Entered SearchRoom");
        return this.roomServices.searchRoom(status);
    }

    // ************************Make Reservations*******************************//
    @GetMapping("/reservation/{roomNumber}/{guestid}")
    public String makeReservation(@PathVariable int roomNumber, @PathVariable int guestid, @RequestParam @Valid int numberOfChildrens,
                            @RequestParam @Valid int numberOfAdults, @RequestParam int numberOfDays, @RequestParam double rate,
                            @RequestParam String checkInDate, @RequestParam String checkOutDate) {
        logger.info("Entered MakeReservation");
        return this.reservationService.makeReservation(roomNumber, guestid, numberOfChildrens, numberOfAdults, numberOfDays, rate, checkInDate, checkOutDate);
    }

    @GetMapping("/getReservation/{reservationid}")
    public Reservation getReservation(@PathVariable int reservationid) {
        return this.reservationService.findByReservationid(reservationid);
    }

    @GetMapping("/viewall-reservations")
    public List<Reservation> viewAllReservations() {
        logger.info("Entered viewAllReservations");
        return this.reservationService.viewAllReservations();
    }

   // ******************Add Payment******************
   @GetMapping("/payment/{reservationid}")
   public String addPayment(@PathVariable int reservationid, @RequestParam String payDate, @RequestParam String cardName,
   @RequestParam String cardNumber, @RequestParam String expiryDate, @RequestParam int cvv) {
       logger.info("Entered Add Payment ");
       return this.paymentService.addPayment(reservationid, payDate, cardName, cardNumber, expiryDate, cvv);
   }



    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e){
            throw new Exception("Incorrect UserName or Password !",e);
        }

        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    
    
}
