package com.micro.receptionistservice.models;

import javax.validation.constraints.*;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reservation {

    @Id
    private ObjectId _id;

    @Min(value = 1, message = "reservationid should not be less than 1")
    @NotNull(message = "reservationid is mandatory")
    private int reservationid;

    private Guest guests;
    private Room rooms;

    @Min(value = 1, message = "roomnumber should not be less than 1")
    @NotNull(message = "roomnumber is mandatory")
    private int roomNumber;

    @Min(value = 0, message = "childrens should not be less than 0")
    @NotNull(message = "childrens is mandatory")
    private int numberOfChildrens;

    @Min(value = 1, message = "adults should not be less than 1")
    @NotNull(message = "adults is mandatory")
    private int numberOfAdults;

    @NotNull(message = "adults is mandatory")
    private String paymentStatus;

    
    private int numberOfDays;

    private double rate;

    
}
