package com.salgend.pause.reservationsManagement.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private Integer duration;
    @ManyToOne
    private Customer customer;
    @OneToMany
    @JoinTable(name = "reservation_tables_asso")
    private List<TableReservation> tablesReservation;


}
