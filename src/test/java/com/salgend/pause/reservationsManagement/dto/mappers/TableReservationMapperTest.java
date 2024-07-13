package com.salgend.pause.reservationsManagement.dto.mappers;

import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.entities.TableReservation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TableReservationMapperTest {

    @Test
    void toEntity() {
        TableReservationDTO dto = new TableReservationDTO(19, 8);
        TableReservation entity = TableReservationMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(19, entity.getNumber());
        assertEquals(9, entity.getNumberOfGuests());

    }


    @Test
    void toDTOWithoutNumber() {
        TableReservation entity = new TableReservation(4);
        TableReservationDTO dto = TableReservationMapper.toDTO(entity);

        assertNotNull(dto);
        assertNull(dto.number());
        assertEquals(4, entity.getNumberOfGuests());

    }
}