package com.salgend.pause.reservationsManagement.entities;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}
