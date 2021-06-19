package com.casestudy.administrationservice.model;

import org.bson.types.ObjectId;
import javax.validation.constraints.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Departments {

    @Id
    private ObjectId _id;

    @Min(value = 0, message = "id should not be less than 1")
    //@NotBlank(message = "Dept id is mandatory")
    private int departmentId;

    //@NotBlank(message = "Dept name is mandatory")
    private String departmentName; //housekeeping/ food n beverage service/ kitchen/ front-office, marketting, HR, accounting
    
   // @NotBlank(message = "Dept type should be operational/functional")
    private String departmentType; //operational/functional

    //@NotBlank(message = "Incharge name is mandatory")
    private String departmentIncharge;
    
    
}
