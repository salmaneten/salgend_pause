package com.salgend.pause.reservationsManagement.dto.mappers;

import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.entities.TableReservation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableReservationMapperTest {

    @Test
    void toEntity() {
        TableReservationDTO dto = new TableReservationDTO(19, 8);
        TableReservation entity = TableReservationMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(19, entity.getNumber());
        assertEquals(8, entity.getNumberOfGuests());

    }


    @Test
    void toDTOWithoutNumber() {
        TableReservation entity = new TableReservation(4);
        TableReservationDTO dto = TableReservationMapper.toDTO(entity);

        assertNotNull(dto);
        assertNull(dto.number());
        assertEquals(4, entity.getNumberOfGuests());

    }
    @Test
    void toDTOs(){
        List<TableReservation> entities = new ArrayList<>();
        entities.add(new TableReservation(4));
        entities.add(new TableReservation(6));
        List<TableReservationDTO> dtos = TableReservationMapper.toDTOs(entities);

        assertNotNull(dtos);
        assertEquals(entities.size(), dtos.size());
        assertEquals(entities.get(0).getNumber(), dtos.get(0).number());
        assertEquals(entities.get(0).getNumberOfGuests(), dtos.get(0).numberOfGuests());

    }
}