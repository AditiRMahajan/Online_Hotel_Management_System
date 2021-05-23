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
public class Guest {

    private int guestid;
    private String name;
    private String phoneNumber;
    private String emailid;
    private String gender;
    private String address;
    
}
