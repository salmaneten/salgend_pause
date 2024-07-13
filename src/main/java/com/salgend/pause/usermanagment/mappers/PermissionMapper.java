package com.salgend.pause.usermanagment.mappers;

import com.salgend.pause.usermanagment.dto.PermissionDto;
import com.salgend.pause.usermanagment.entities.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    Permission permissionDtoToPermission(PermissionDto permissionDto);
}
