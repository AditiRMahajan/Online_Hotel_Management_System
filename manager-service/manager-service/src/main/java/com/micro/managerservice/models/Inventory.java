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
public class Inventory {

    @Id
    private ObjectId _id;

    @Min(value = 0, message = "id should not be less than 0")
    //@NotBlank(message = "Inventory id is mandatory")
    private int inventoryId;

    //@NotBlank(message = "Inventory name is mandatory")
    private String inventoryName;

    //@NotBlank(message = "Inventory type should be master/peroidic/daily")
    private String category;  //master/peroidic/daily

    //@NotBlank(message = "roomType is mandatory")
    private String roomType;

    //@NotBlank(message = "inventoryStatus is mandatory")
    private String inventoryStatus;
    
}
