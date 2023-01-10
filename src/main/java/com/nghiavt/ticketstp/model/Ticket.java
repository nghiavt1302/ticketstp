package com.nghiavt.ticketstp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ticket")
@Getter
@Setter
public class Ticket {
    @Id
    private Integer id;
    private boolean enable;
    private boolean state;
}
