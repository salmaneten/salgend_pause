package com.salgend.pause.reservationsManagement.controllers;

import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.services.TableReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tables")
public class TableReservationController {
    private TableReservationService tableReservationService;

    @PostMapping
    public ResponseEntity<TableReservationDTO> addTableInRestaurant(@Valid @RequestBody TableReservationDTO dto) {
        return ResponseEntity.ok(tableReservationService.createOrUpdate(dto));

    }
    @GetMapping("/{id}")
    public ResponseEntity<TableReservationDTO> showTable( @PathVariable Long id){
        return ResponseEntity.ok(tableReservationService.findById(id));

    }
    @GetMapping("/all")
    public ResponseEntity<List<TableReservationDTO>> showAllTables(){
        return ResponseEntity.ok(tableReservationService.findAll());
    }
    @GetMapping
    public ResponseEntity<Page<TableReservationDTO>> showTables(@RequestParam int page, @RequestParam int size){
        return ResponseEntity.ok(tableReservationService.findAll(page,size));
    }
}
