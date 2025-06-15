package com.salgend.pause.reservationsManagement.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.services.TableReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;


@WebMvcTest(TableReservationController.class)
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
        mock(TableReservationService.class);
        when(tableReservationService.createOrUpdate(any(TableReservationDTO.class))).thenReturn(TableReservationDTO.of(6));

        mockMvc.perform(MockMvcRequestBuilders.post("/tables")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.numberOfGuests").value(6));

    }
    @Test
    void addTableInRestaurant_invalid() throws Exception {
        TableReservationDTO invalidDTO = TableReservationDTO.of(10);
        mockMvc.perform(MockMvcRequestBuilders.post("/tables")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void showTable() throws Exception {
        TableReservationDTO dto = TableReservationDTO.of(1L, null, 4);
        when(tableReservationService.findById(1L)).thenReturn(dto);

        mockMvc.perform(get("/tables/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfGuests").value(4));
    }

    @Test
     void showTables() throws Exception {
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        TableReservationDTO tableReservationDTO = TableReservationDTO.of(4); // Assuming a default constructor
        Page<TableReservationDTO> tableReservationDTOPage = new PageImpl<>(
                Collections.singletonList(tableReservationDTO), pageable, 1);

        when(tableReservationService.findAll(page, size)).thenReturn(tableReservationDTOPage);

        mockMvc.perform(get("/tables")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1));
    }
}