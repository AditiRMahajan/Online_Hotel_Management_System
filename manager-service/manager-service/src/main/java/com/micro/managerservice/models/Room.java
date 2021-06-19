package com.micro.managerservice.models;

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
public class Room {

    @Id
    private ObjectId _id;

    @Min(value = 1, message = "roomnumber should not be less than 1")
    //@NotBlank(message = "roomnumber is mandatory")
    private int roomNumber;

    //@NotBlank(message = "roomType is mandatory")
    private String roomType;

    //@NotBlank(message = "Status is mandatory")
    private String status;
    
    
}
