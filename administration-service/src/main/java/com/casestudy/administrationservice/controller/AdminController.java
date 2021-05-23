package com.casestudy.administrationservice.controller;

import java.util.*;
import org.slf4j.*;
import javax.validation.Valid;

import com.casestudy.administrationservice.model.Admin;
import com.casestudy.administrationservice.model.AuthenticationRequest;
import com.casestudy.administrationservice.model.AuthenticationResponse;
import com.casestudy.administrationservice.model.Departments;
import com.casestudy.administrationservice.model.Reservation;
import com.casestudy.administrationservice.model.Guest;
import com.casestudy.administrationservice.model.Room;
import com.casestudy.administrationservice.model.Staffs;
import com.casestudy.administrationservice.repository.AdminRepository;
import com.casestudy.administrationservice.service.DepartmentService;
import com.casestudy.administrationservice.service.MyUserDetailsService;
import com.casestudy.administrationservice.service.ReportsService;
import com.casestudy.administrationservice.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ReportsService reportsService;

    @Autowired
    JwtUtil jwtTokenUtil;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AdminRepository adminRepository;

    @PostMapping("/logindata")
    public String logindata(@RequestBody Admin admin){
        this.adminRepository.save(admin);
        return "Data added succesfully";
    }

    @PostMapping("/add-department")
    public String addDepartment(@RequestParam @Valid int departmentId, @RequestParam @Valid String departmentName, @RequestParam @Valid String departmentType, @RequestParam
    @Valid String departmentIncharge) {
        logger.info("Entered addDepartment()");
        return this.departmentService.addDepartment(departmentId, departmentName, departmentType, departmentIncharge);
    }

    @PutMapping("/update-department/{departmentId}")
    public String updateDepartment(@PathVariable int departmentId, @RequestParam @Valid String departmentName, @RequestParam @Valid String departmentType, @RequestParam
    @Valid String departmentIncharge){
        logger.info("Entered updateDepartment()");
        return this.departmentService.updateDepartment(departmentId, departmentName, departmentType, departmentIncharge);
    }

    @DeleteMapping("/delete-department/{departmentId}")
    public String deleteDepartment(@PathVariable int departmentId){
        logger.info("Entered deleteDepartment()");
        return this.departmentService.deleteDepartment(departmentId);
    }

   // **********************View Reports******************************
    @GetMapping("/view-departments")
    public List<Departments> viewDepartments() {
        logger.info("Entered viewDepartments()");
        return this.departmentService.viewDepartments();
    }

    @GetMapping("/view-staff-reports")
    public List<Staffs> viewStaffReports() {
        logger.info("Entered viewStaffReports()");
        return this.reportsService.viewStaffReports();
    }

    @GetMapping("/view-room-details")
    public List<Room> viewRoomDetails() {
        logger.info("Entered viewRoomDetails()");
        return this.reportsService.viewRoomReports();
    }

    @GetMapping("/view-guest-details")
    public List<Guest> viewGuestDetails() {
        logger.info("Entered viewGuestDetails()");
        return this.reportsService.viewGuestDetails();
    }

    @GetMapping("/view-booking-details")
    public List<Reservation> viewBookingDetails() {
        logger.info("Entered viewvDetails()");
        return this.reportsService.viewReservationDetails();
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
