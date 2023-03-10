package com.nghiavt.ticketstp.controller;

import com.nghiavt.ticketstp.model.Ticket;
import com.nghiavt.ticketstp.model.User;
import com.nghiavt.ticketstp.repository.TicketRepository;
import com.nghiavt.ticketstp.repository.UserRepository;
import com.nghiavt.ticketstp.service.LogService;
import com.nghiavt.ticketstp.service.TicketService;
import com.nghiavt.ticketstp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
@CrossOrigin
@RestController("/")
public class TicketController {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TicketService ticketService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Autowired
    LogService logService;

    @CrossOrigin
    @GetMapping("/login/{username}/{pwd}")
    public ResponseEntity<String> login(@PathVariable String username,
                                @PathVariable String pwd){
        ResponseEntity response = null;
        List<User> users = userService.findUser(username);
        if (users.isEmpty()){
            response = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("User không tồn tại.");
        }else {
            User user = users.get(0);
            if (!pwd.equals(user.getPwd())){
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Sai mật khẩu.");
            }else {
                response = ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body("Login thành công.");
            }
        }
        return response;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public String scanNewTicket(@PathVariable Integer id){
        List<Ticket> tickets = ticketRepository.findAllById(Collections.singleton(id));
        if (tickets.size()>0){
            Ticket ticket = tickets.get(0);
            if (!ticket.isEnable()){
                ticketService.enableTicket(ticket);
                logService.addLog(id, "Kich hoat");
                return "Vé " + id + " đã kích hoạt thành công.";
            }
            else {
                return "Vé " + id + " đã kích hoạt trước đây.";
            }
        }
        else {
            return "ID vé không tồn tại.";
        }
    }

    @CrossOrigin
    @GetMapping("/state/{id}")
    public String setState(@PathVariable Integer id){
        List<Ticket> tickets = ticketRepository.findAllById(Collections.singleton(id));
        if (tickets.size()>0){
            Ticket ticket = tickets.get(0);
            if (ticket.isEnable()){
                if (ticket.isState()){
                    ticketService.outTicket(ticket);
                    logService.addLog(id, "Ra ngoai");
                    return "Vé " + id + " đã huỷ kích hoạt.";
                }else {
//                    ticketService.inTicket(ticket);
//                    logService.addLog(id, "Vao trong");
                    return "Vé " + id + " đã huỷ kích hoạt rồi.";
                }
            }else {
                return "Vé chưa được kích hoạt.";
            }
        }else {
            return "ID vé không tồn tại.";
        }
    }
}
