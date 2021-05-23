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
public class Staffs {

    private int staffid;
    private String staffName;
    private int staffNic;
    
    private int staffSalary;
    private int staffAge;
    private String staffOccupation;
    private String staffEmail;
    
}
