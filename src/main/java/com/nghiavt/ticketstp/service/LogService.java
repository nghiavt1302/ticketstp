package com.nghiavt.ticketstp.service;

import com.nghiavt.ticketstp.model.Log;
import com.nghiavt.ticketstp.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class LogService {
    @Autowired
    LogRepository logRepository;

    public void addLog(Integer id, String act){
        Log log = new Log();
        log.setTicket_id(id);
        log.setAction(act);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        log.setTime(time);
        logRepository.save(log);
    }
}
