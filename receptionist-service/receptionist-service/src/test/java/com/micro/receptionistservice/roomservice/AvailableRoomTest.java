package com.micro.receptionistservice.roomservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.micro.receptionistservice.models.AvailableRoom;
import com.micro.receptionistservice.models.Room;
import com.micro.receptionistservice.services.RoomServicesImpl;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class AvailableRoomTest {

   RoomServicesImpl roomServiceImplMock = mock(RoomServicesImpl.class);

    @Test
    @DisplayName("Test for searching rooms")
    public void searchRoomTest() {

        AvailableRoom room = new AvailableRoom(Stream
        .of(new Room(null, 1,"Double Type", "Available", "25-05-2021", "27-05-2021"), new Room(null, 2, "Double Type", "Available","25-05-2021", "27-05-2021" )).collect(Collectors.toList()));
        when(roomServiceImplMock.searchRoom("Available")).thenReturn(room);
        assertEquals(room,roomServiceImplMock.searchRoom("Available"));


    }
    
}
