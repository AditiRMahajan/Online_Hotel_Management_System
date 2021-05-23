package com.casestudy.administrationservice.service;

import java.util.ArrayList;

import com.casestudy.administrationservice.model.Admin;
import com.casestudy.administrationservice.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // return new User("foo","foo",new ArrayList<>());
        Admin admin = this.adminRepository.findByUsername(userName);
        return new User(admin.getUsername(),admin.getPassword(),new ArrayList<>());
    }
    
}
