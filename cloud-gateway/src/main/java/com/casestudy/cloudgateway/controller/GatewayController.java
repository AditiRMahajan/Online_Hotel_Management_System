package com.casestudy.cloudgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class GatewayController {

    @GetMapping("/message")
    public String hotelFallback() {
        return "Requested service is down at this moment, please try again later!";
    }
    
}
