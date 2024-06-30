package com.salgend.pause.reservationsManagement.services;


import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.dto.mappers.TableReservationMapper;
import com.salgend.pause.reservationsManagement.entities.TableReservation;
import com.salgend.pause.reservationsManagement.repositories.ITableReservationRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Objects;

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
    public TableReservationDTO getById(Long id){
      TableReservation tableReservation = tableReservationRepository.getReferenceById(id);
        return  TableReservationMapper.toDTO(tableReservation);
    }



}
