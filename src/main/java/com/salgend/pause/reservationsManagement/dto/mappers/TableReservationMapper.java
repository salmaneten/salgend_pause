package com.salgend.pause.reservationsManagement.dto.mappers;

import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.entities.TableReservation;
import jakarta.validation.Valid;
import java.util.Objects;

public class TableReservationMapper {
    public static TableReservation toEntity(@Valid TableReservationDTO dto){
       TableReservation entity =  new TableReservation(dto.numberOfGuests());
       if(Objects.nonNull(dto.number())){
           entity.setNumber(dto.number());
       }

       return entity;
    }
    public static TableReservationDTO toDTO(@Valid TableReservation entity){
        return new TableReservationDTO(entity.getNumber(), entity.getNumberOfGuests());
    }
}
