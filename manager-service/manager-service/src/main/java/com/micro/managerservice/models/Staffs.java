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
public class Staffs {
    
    @Id
    private ObjectId _id;

    @Min(value = 1, message = "staffid should not be less than 1")
    //@NotBlank(message = "staffid is mandatory")
    private int staffid;

    //@NotBlank(message = "staffname is mandatory")
    private String staffName;

    @Min(value = 1, message = "staffid should not be less than 1")
    private int staffNic;
    
    @Min(value = 1, message = "staffid should not be less than 1")
    private int staffSalary;

    @Min(value = 1, message = "staffid should not be less than 1")
    private int staffAge;

    //@NotBlank(message = "staffoccupation is mandatory")
    private String staffOccupation;

    //@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
    private String staffEmail;
}
