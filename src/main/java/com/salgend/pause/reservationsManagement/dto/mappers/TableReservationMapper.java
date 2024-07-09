package com.salgend.pause.reservationsManagement.dto.mappers;

import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.entities.TableReservation;
import java.util.Objects;

public class TableReservationMapper {
    public static TableReservation toEntity( TableReservationDTO dto){
       TableReservation entity =  new TableReservation(dto.numberOfGuests());
       if(Objects.nonNull(dto.number())){
           entity.setNumber(dto.number());
       }

       return entity;
    }
    public static TableReservationDTO toDTO( TableReservation entity){
        return new TableReservationDTO(entity.getNumber(), entity.getNumberOfGuests());
    }
}
