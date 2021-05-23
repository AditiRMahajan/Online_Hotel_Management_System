package com.micro.managerservice.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import org.slf4j.*;
import javax.validation.Valid;

import com.micro.managerservice.models.AuthenticationRequest;
import com.micro.managerservice.models.AuthenticationResponse;
import com.micro.managerservice.models.Inventory;
import com.micro.managerservice.models.Manager;
import com.micro.managerservice.models.Room;
import com.micro.managerservice.models.Staffs;
import com.micro.managerservice.repository.ManagerRepository;
import com.micro.managerservice.services.InventoryService;
import com.micro.managerservice.services.MyUserDetailsService;
import com.micro.managerservice.services.RoomServices;
import com.micro.managerservice.services.StaffServices;
import com.micro.managerservice.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    private RoomServices roomService;

    @Autowired
    private StaffServices staffServices;

    @Autowired
    JwtUtil jwtTokenUtil;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/logindata")
    public String logindata(@RequestBody Manager manager){
        this.managerRepository.save(manager);
        return "Data added succesfully";
    }
    
    @PostMapping("/addroom")
    public String addRoom(@RequestParam @Valid int roomNumber, @RequestParam @Valid String roomType,@RequestParam @Valid String status) {
        logger.info("Entered addRoom()");
        return this.roomService.addRoom(roomNumber, roomType, status);
    }

    @PutMapping("/updateroom/{roomNumber}")
    public String updateRoom(@PathVariable int roomNumber,@RequestParam @Valid String roomType,@RequestParam @Valid String status){
        logger.info("Entered updateRoom()");
        return this.roomService.updateRoom(roomNumber, roomType, status);
    }

    @DeleteMapping("/delete-room/{roomNumber}")
    public String deleteRoom(@PathVariable int roomNumber){
        logger.info("Entered deleteRoom()");
        return this.roomService.deleteRoom(roomNumber);
    }

    @GetMapping("/viewrooms")
    public List<Room> viewAllRooms() {
        logger.info("Entered viewAllRooms()");
        return this.roomService.findAllRooms();
    }

    // ************************Staff Details*******************************//
    @PostMapping("/addstaff")
    public String addStaff(@RequestBody @Valid Staffs staff){
        logger.info("Entered AddStaff");
        return this.staffServices.addStaff(staff);
    }

    @PutMapping("/updatestaff/{staffid}")
    public String updateStaff(@PathVariable int staffid,@RequestBody @Valid Staffs staff){
        logger.info("Entered UpdateStaff");
        return this.staffServices.updateStaff(staffid, staff);
    }

    @DeleteMapping("/delete-staff/{staffid}")
    public String deleteStaff(@PathVariable int staffid){
        logger.info("Entered DeleteStaff");
        return this.staffServices.deleteStaff(staffid);
    }

    @GetMapping("/viewstaff")
    public List<Staffs> viewAllStaffs() {
        logger.info("Entered viewAllStaffs");
        return this.staffServices.viewAllStaffs();
    }

    // ************************Inventory Details*****************************//
    @PostMapping("/add-inventory")
    public String addInventory(@RequestParam @Valid int inventoryId, @RequestParam @Valid String inventoryName, @RequestParam @Valid String category, @RequestParam @Valid String roomType,
    @RequestParam String inventoryStatus) {
        logger.info("Entered addInventory");
        return this.inventoryService.addInventory(inventoryId, inventoryName, category, roomType, inventoryStatus);
    }

    @PutMapping("/update-inventory/{inventoryId}")
    public String updateInventory(@PathVariable int inventoryId, @RequestParam @Valid String inventoryName, @RequestParam @Valid String category, @RequestParam @Valid String roomType,
    @RequestParam @Valid 
    String inventoryStatus) {
        logger.info("Entered updateInventory");
        return this.inventoryService.updateInventory(inventoryId, inventoryName, category, roomType, inventoryStatus);
    }

    @DeleteMapping("/delete-inventory/{inventoryId}")
    public String deleteInventory(@PathVariable int inventoryId){
        logger.info("Entered deleteInventory");
        return this.inventoryService.deleteInventory(inventoryId);
    }

    @GetMapping("/view-inventory")
    public List<Inventory> viewAllInventory() {
        logger.info("Entered viewAllInventory");
        return this.inventoryService.findAllInventory();
    }
    

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e){
            throw new Exception("Incorrect UserName or Password !",e);
        }

        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    
}
