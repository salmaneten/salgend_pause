package com.salgend.pause.reservationsManagement.services;
import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.entities.TableReservation;


import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testFindById_NotFound(){
        assertThrowsExactly(EntityNotFoundException.class,
                ( )-> tableReservationService.findById(19L));
    }

}