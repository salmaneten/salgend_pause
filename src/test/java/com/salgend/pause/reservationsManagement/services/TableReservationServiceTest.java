package com.salgend.pause.reservationsManagement.services;
import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.entities.TableReservation;


import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import jakarta.validation.ConstraintViolationException;

@SpringBootTest
class TableReservationServiceTest {

    @Autowired
    TableReservationService tableReservationService;

    @Test
    void testCreateTable() {
        TableReservationDTO dto = TableReservationDTO.of( 5);

        TableReservation tableReservation = tableReservationService.createOrUpdate(dto);
        assertNotNull(tableReservation);
        assertEquals(1,tableReservation.getNumber());
        assertEquals(5, tableReservation.getNumberOfGuests());
    }
    @Test()
    void testCreateTable_NumberOfGuestsLessThanMinThree(){
        TableReservationDTO dto = TableReservationDTO.of( 1);
        assertThrowsExactly(ConstraintViolationException.class,  () ->{
            tableReservationService.createOrUpdate(dto);
        });
    }
    @Test()
    void testCreateTable_NumberOfGuestsGreatThanMaxEight(){
        TableReservationDTO dto = TableReservationDTO.of( 9);
        assertThrowsExactly(ConstraintViolationException.class,  () ->{
            tableReservationService.createOrUpdate(dto);
        });
    }

}