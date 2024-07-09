package com.salgend.pause.reservationsManagement.controllers;

import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.services.TableReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TableReservationController {
    private TableReservationService tableReservationService;

    @PostMapping("restauration/tables")
    public ResponseEntity addTableInRestauration(@Valid @RequestBody TableReservationDTO dto) {
        return ResponseEntity.ok(tableReservationService.createOrUpdate(dto));

    }
    @GetMapping("restauration/tables/{id}")
    public ResponseEntity showTable( @PathVariable Long id){
        return ResponseEntity.ok(tableReservationService.findById(id));

    }
}
