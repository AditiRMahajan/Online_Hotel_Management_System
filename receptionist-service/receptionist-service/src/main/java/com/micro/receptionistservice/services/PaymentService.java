package com.micro.receptionistservice.services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface PaymentService {

    public String addPayment(@PathVariable int reservationid, @RequestParam String payDate, @RequestParam String cardName,
                    @RequestParam String cardNumber, @RequestParam String expiryDate, @RequestParam int cvv );
    
}
