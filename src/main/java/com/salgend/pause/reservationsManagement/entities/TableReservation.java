package com.salgend.pause.reservationsManagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class TableReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private Integer number;

    @NotNull
    private Integer numberOfGuests;

    public TableReservation(Integer numberOfGuests){
        this.numberOfGuests = numberOfGuests;
    }
}
