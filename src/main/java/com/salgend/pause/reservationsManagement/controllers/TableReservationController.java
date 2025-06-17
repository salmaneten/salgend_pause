package com.salgend.pause.reservationsManagement.controllers;

import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.services.TableReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tables")
@Tag(name = "Table Management", description = "Operation for managing restaurant tables")
public class TableReservationController {
    private TableReservationService tableReservationService;

    @Operation(summary = "Create a new Table",
               description = "Creates a new Table in the Restaurant with specefic number of guests")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Table created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input - check guest count limits")
    })           
    @PostMapping
    public ResponseEntity<TableReservationDTO> addTableInRestaurant(@Valid @RequestBody TableReservationDTO dto) {
        return ResponseEntity.ok(tableReservationService.createOrUpdate(dto));

    }
    @Operation(summary = "Get table by ID", description = "Retrieve a specific table by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Table found and returned"),
        @ApiResponse(responseCode = "404", description = "Table not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TableReservationDTO> showTable( @PathVariable Long id){
        return ResponseEntity.ok(tableReservationService.findById(id));

    }
    @Operation(summary = "Get all tables", description = "Retrieve a list of all tables in the restaurant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tables found and returned"),
        @ApiResponse(responseCode = "404", description = "Tables not found")
    })
    @GetMapping("/all")
    public ResponseEntity<List<TableReservationDTO>> showAllTables(){
        return ResponseEntity.ok(tableReservationService.findAll());
    }
   
    @Operation(summary = "Get paginated tables", description = "Retrieve tables with pagination support. Returns a page containing table data with metadata like total count, page number, and page size.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page of tables retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    @GetMapping
    public ResponseEntity<Page<TableReservationDTO>> showTables(
            @Parameter(description = "Page number (0-based)", example = "0") @RequestParam int page,
            @Parameter(description = "Number of items per page", example = "10") @RequestParam int size) {
        return ResponseEntity.ok(tableReservationService.findAll(page, size));
    }
}
