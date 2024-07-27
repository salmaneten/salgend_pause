package com.salgend.pause.reservationsManagement.services;


import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.dto.mappers.TableReservationMapper;
import com.salgend.pause.reservationsManagement.entities.TableReservation;
import com.salgend.pause.reservationsManagement.repositories.ITableReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TableReservationService {
    private ITableReservationRepository tableReservationRepository;

    public TableReservationDTO createOrUpdate(@Valid TableReservationDTO tableReservationDto){
        TableReservation tableReservation = TableReservationMapper.toEntity(tableReservationDto);
        if(Objects.isNull(tableReservation.getNumber())){
            tableReservation.setNumber(tableReservationRepository.findMaxNumber() + 1);
        }
        TableReservation table = tableReservationRepository.save(tableReservation);
        return TableReservationMapper.toDTO(table);
    }
    public TableReservationDTO findById(Long id){
      Optional<TableReservation> table= tableReservationRepository.findById(id);
        return table.map(TableReservationMapper::toDTO).orElseThrow(() -> new EntityNotFoundException("tableReservation not found with id "+ id));
    }

    public List<TableReservationDTO> findAll(){
        List<TableReservation> tables = tableReservationRepository.findAll();
        return TableReservationMapper.toDTOs(tables);
    }

    public Page<TableReservationDTO> findAll(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<TableReservation> tables = tableReservationRepository.findAll(pageRequest);
        return tables.map(TableReservationMapper::toDTO);
    }

}
