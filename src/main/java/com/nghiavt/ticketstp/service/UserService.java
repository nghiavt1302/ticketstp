package com.nghiavt.ticketstp.service;

import com.nghiavt.ticketstp.model.User;
import com.nghiavt.ticketstp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findUser(String username){
        List<User> users = userRepository.findAllByUsername(username);
        return users;
    }

}
