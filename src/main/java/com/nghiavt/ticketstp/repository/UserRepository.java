package com.nghiavt.ticketstp.repository;

import com.nghiavt.ticketstp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByUsername(String username);
}
