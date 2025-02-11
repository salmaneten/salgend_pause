package com.salgend.pause.reservationsManagement.dto.mappers;

import com.salgend.pause.reservationsManagement.dto.TableReservationDTO;
import com.salgend.pause.reservationsManagement.entities.TableReservation;


import java.util.List;
import java.util.Objects;


public class TableReservationMapper {
    public static TableReservation toEntity( TableReservationDTO dto){
       TableReservation entity =  new TableReservation(dto.numberOfGuests());
       if(Objects.nonNull(dto.id())){
           entity.setId(dto.id());
       }if(Objects.nonNull(dto.number())){
           entity.setNumber(dto.number());
       }
       return entity;
    }
    public static TableReservationDTO toDTO( TableReservation entity){
        return new TableReservationDTO(entity.getId(),entity.getNumber(), entity.getNumberOfGuests());
    }
    public static List<TableReservationDTO> toDTOs(List<TableReservation> entities){
      return entities.stream().map(TableReservationMapper::toDTO).toList();

    }
}
