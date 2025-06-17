package com.salgend.pause.reservationsManagement.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
@Schema(description = "Table reservation data transfer object", 
        example = """
        {
          "id": 1,
          "number": 5,
          "numberOfGuests": 4
        }
        """)
public record TableReservationDTO(
        @Schema(description = "Unique table identifier", example = "1")
        Long id,
        
        @Schema(description = "Table number in restaurant", example = "5")
        Integer number,
        
        @Schema(description = "Guest capacity", example = "4", minimum = "3", maximum = "8")
        @Min(3) @Max(8)
        Integer numberOfGuests) {
        public static TableReservationDTO of( @Min(3) @Max(8) Integer numberOfGuests) {
                return new TableReservationDTO(null,null, numberOfGuests);
        }
        public static TableReservationDTO of( Long id, Integer number, @Min(3) @Max(8) Integer numberOfGuests) {
                return new TableReservationDTO(id,number, numberOfGuests);
        }
}
