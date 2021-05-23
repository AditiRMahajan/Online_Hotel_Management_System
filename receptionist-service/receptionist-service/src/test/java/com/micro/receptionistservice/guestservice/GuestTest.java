package com.micro.receptionistservice.guestservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.micro.receptionistservice.models.Guest;
import com.micro.receptionistservice.services.GuestServicesImpl;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class GuestTest {

    GuestServicesImpl guestServiceImplMock = mock(GuestServicesImpl.class);
   
 
    @Test
    @DisplayName("Test for adding guest")
    public void addGuestTest(){
        when(guestServiceImplMock.addGuest("Poonam", "86004234516", "poo1@gmail.com", "Female", "Pune")).thenReturn("Successfully added");
        assertEquals("Successfully added", guestServiceImplMock.addGuest("Poonam", "86004234516", "poo1@gmail.com", "Female", "Pune"));
    }
 
    @Test
    @DisplayName("Test for updating guest")
    public void updateGuestTest(){
        when(guestServiceImplMock.updateGuest(1,"Poonam", "86004234516", "poo1@gmail.com", "Female", "Pune")).thenReturn("Successfully updated");
        assertEquals("Successfully updated", guestServiceImplMock.updateGuest(1,"Poonam", "86004234516", "poo1@gmail.com", "Female", "Pune"));
    }

    @Test
    @DisplayName("Test for deleting guest")
    public void deleteGuestTest(){
        when(guestServiceImplMock.deleteGuest(1)).thenReturn("Successfully deleted");
        assertEquals("Successfully deleted", guestServiceImplMock.deleteGuest(1));
    }

    @Test
    @DisplayName("Test for findAllGuests")
    public void findAllGuestsTest(){
        when(guestServiceImplMock.viewAllGuests()).thenReturn(Stream
        .of(new Guest(null, 1,"Poonam", "86004234516", "poo1@gmail.com", "Female", "Pune"), new Guest(null, 1,"Poonam", "86004234516", "poo1@gmail.com", "Female", "Pune"))
        .collect(Collectors.toList()));
        assertEquals(2, guestServiceImplMock.viewAllGuests().size());
    }
   
    
}
