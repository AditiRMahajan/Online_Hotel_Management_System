package com.micro.receptionistservice.models;

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
public class Payment {

    @Id
    private ObjectId _id;

    private int paymentid;
    private int reservationid;
    private double totalRate;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private String payDate;
    private String cardName;
	private String cardNumber;
	private String expiryDate;
    private int cvv;
    Reservation reservation;
    
}
