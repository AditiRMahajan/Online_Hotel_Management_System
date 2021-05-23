package com.casestudy.administrationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reservation {

    private int reservationid;
    private Guest guests;
    private Room rooms;
    private int roomnumber;
    private int numberOfChildrens;
    private int numberOfAdults;
    private String paymentStatus;
    private int numberOfDays;
    private double rate;

    
}
