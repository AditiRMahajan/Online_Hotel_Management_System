package com.micro.receptionistservice.models;

//import java.time.LocalDateTime;

import javax.validation.constraints.*;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.format.annotation.DateTimeFormat;

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
public class Room {

    @Id
    private ObjectId _id;

    @Min(value = 1, message = "roomnumber should not be less than 1")
    @NotNull(message = "roomnumber is mandatory")
    private int roomNumber;

    @NotNull(message = "roomType is mandatory")
    private String roomType;

    @NotNull(message = "Status is mandatory")
    private String status;

    //@Future
    @NotNull(message = "checkin is mandatory")
    private String checkInDate;

    @NotNull(message = "checkout is mandatory")
    private String checkOutDate;
    
}