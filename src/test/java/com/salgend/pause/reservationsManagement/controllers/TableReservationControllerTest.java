package com.salgend.pause.reservationsManagement.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.entities.TableReservation;
import com.salgend.pause.reservationsManagement.services.TableReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



@SpringBootTest
//@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class TableReservationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TableReservationService tableReservationService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addTableInRestaurant_valid() throws Exception {
        TableReservationDTO validDTO = TableReservationDTO.of(6);
        TableReservationService tableReservationServiceMock = mock(TableReservationService.class);
        when(tableReservationService.createOrUpdate(any(TableReservationDTO.class))).thenReturn(new TableReservation(6));

        mockMvc.perform(MockMvcRequestBuilders.post("/restauration/tables")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.numberOfGuests").value(6));

    }
    @Test
    void addTableInRestaurant_invalid() throws Exception {
        TableReservationDTO invalidDTO = TableReservationDTO.of(10);
        mockMvc.perform(MockMvcRequestBuilders.post("/restauration/tables")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void showTable() throws Exception {
        TableReservationDTO dto = new TableReservationDTO(1, 4);
        when(tableReservationService.findById(1L)).thenReturn(dto);

        mockMvc.perform(get("/restauration/tables/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfGuests").value(4));
    }
}