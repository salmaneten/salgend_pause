package com.salgend.pause.usermanagment.mappers;

import com.salgend.pause.usermanagment.dto.RoleDto;
import com.salgend.pause.usermanagment.entities.Permission;
import com.salgend.pause.usermanagment.entities.Role;
import com.salgend.pause.usermanagment.repositories.PermissionRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(target = "permissions", source = "permissions", qualifiedByName = "permissionListToString")
    Role roleDtoToRole(RoleDto roleDto, @Context PermissionRepository permissionRepository);

    @Named("permissionListToString")
    default Set<Permission> permissionListToString(Set<String> permissions, @Context PermissionRepository permissionRepository) {
        return permissions.stream()
                .map(permissionName -> permissionRepository.findByName(permissionName)
                        .orElseThrow(() -> new IllegalArgumentException("Permission not found: " + permissionName)))
                .collect(Collectors.toSet());
    }
}
