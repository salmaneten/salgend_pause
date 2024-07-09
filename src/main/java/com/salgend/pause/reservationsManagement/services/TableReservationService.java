package com.salgend.pause.reservationsManagement.services;


import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.dto.mappers.TableReservationMapper;
import com.salgend.pause.reservationsManagement.entities.TableReservation;
import com.salgend.pause.reservationsManagement.repositories.ITableReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TableReservationService {
    private ITableReservationRepository tableReservationRepository;

    public TableReservation createOrUpdate(@Valid TableReservationDTO tableReservationDto){
        TableReservation tableReservation = TableReservationMapper.toEntity(tableReservationDto);
        if(Objects.isNull(tableReservation.getNumber())){
            tableReservation.setNumber(tableReservationRepository.findMaxNumber() + 1);
        }

       return tableReservationRepository.save(tableReservation);
    }
    public TableReservationDTO findById(Long id){
      Optional<TableReservation> table= tableReservationRepository.findById(id);
        return table.map(TableReservationMapper::toDTO).orElseThrow(() -> new EntityNotFoundException("tableReservation not found with id "+ id));
    }


}
