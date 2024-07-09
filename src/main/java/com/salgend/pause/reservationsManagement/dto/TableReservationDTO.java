package com.salgend.pause.reservationsManagement.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record TableReservationDTO(
        Integer number,
        @Min(3) @Max(8)
        Integer numberOfGuests) {
        public static TableReservationDTO of( @Min(3) @Max(8) Integer numberOfGuests) {
                return new TableReservationDTO(null, numberOfGuests);
        }
}
