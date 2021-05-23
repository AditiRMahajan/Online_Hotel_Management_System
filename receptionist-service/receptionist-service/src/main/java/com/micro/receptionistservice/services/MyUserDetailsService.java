package com.micro.receptionistservice.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.micro.receptionistservice.models.Receptionist;
import com.micro.receptionistservice.repository.ReceptionistRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ReceptionistRepo receptionistRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // return new User("foo","foo",new ArrayList<>());
        Receptionist receptionist = this.receptionistRepo.findByUsername(userName);
        return new User(receptionist.getUsername(),receptionist.getPassword(),new ArrayList<>());
    }
    
}
