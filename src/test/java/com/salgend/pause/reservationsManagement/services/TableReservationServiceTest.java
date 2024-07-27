package com.salgend.pause.reservationsManagement.services;
import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.entities.TableReservation;


import com.salgend.pause.reservationsManagement.repositories.ITableReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TableReservationServiceTest {

    @Autowired
    TableReservationService tableReservationService;
    @MockBean
    private ITableReservationRepository tableReservationRepository;

    @Test
    void testCreateTable() {
        TableReservationDTO dto = TableReservationDTO.of( 5);

        TableReservationDTO tableReservationDTO = tableReservationService.createOrUpdate(dto);
        assertNotNull(tableReservationDTO);
        assertEquals(1,tableReservationDTO.number());
        assertEquals(5, tableReservationDTO.numberOfGuests());
    }

    @Test
    void testFindById_NotFound(){
        assertThrowsExactly(EntityNotFoundException.class,
                ( )-> tableReservationService.findById(19L));
    }

    @Test
    void testFindAll(){
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        TableReservation table = new TableReservation(5);
        List<TableReservation> tables = Collections.singletonList(table);
        Page<TableReservation> tableReservationPage = new PageImpl<>(tables, pageable, tables.size());
        when(tableReservationRepository.findAll(pageable)).thenReturn(tableReservationPage);

        Page<TableReservationDTO> result = tableReservationService.findAll(page, size);
        assertNotNull(result);
        assertThat(result.getContent()).hasSize(1);
        verify(tableReservationRepository, times(1)).findAll(pageable);

    }
}