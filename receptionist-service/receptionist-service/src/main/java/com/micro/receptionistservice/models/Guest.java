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
public class Guest {

    @Id
    private ObjectId _id;

    @Min(value = 1, message = "guestid should not be less than 1")
    @NotNull(message = "guestid is mandatory")
    private int guestid;

    @NotNull(message = "name is mandatory")
    private String name;

     //@Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
    @Min(value = 10, message = "phonenumber should be more than 10 digits")
    private String phoneNumber;

    @NotNull(message = "Email is mandatory")
	@Email(message = "Email should be valid")
    private String emailid;

    @NotNull(message = "gender is mandatory")
    private String gender;

    @NotNull(message = "address is mandatory")
    private String address;
    
}
