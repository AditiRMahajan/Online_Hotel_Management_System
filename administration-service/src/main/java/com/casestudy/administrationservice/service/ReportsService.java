package com.casestudy.administrationservice.service;

import java.util.List;

import com.casestudy.administrationservice.model.Reservation;
import com.casestudy.administrationservice.model.Guest;
import com.casestudy.administrationservice.model.Room;
import com.casestudy.administrationservice.model.Staffs;

public interface ReportsService {

    public List<Staffs> viewStaffReports();
    public List<Room> viewRoomReports();
    public List<Guest> viewGuestDetails();
    public List<Reservation> viewReservationDetails();
}
