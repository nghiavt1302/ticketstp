package com.nghiavt.ticketstp.service;

import com.nghiavt.ticketstp.model.Ticket;
import com.nghiavt.ticketstp.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    public void enableTicket(Ticket ticket){
        ticket.setEnable(true);
        ticket.setState(true);
        ticketRepository.save(ticket);
    }

    public void inTicket(Ticket ticket){
        ticket.setState(true);
        ticketRepository.save(ticket);
    }
    public void outTicket(Ticket ticket){
        ticket.setState(false);
        ticketRepository.save(ticket);
    }
}
