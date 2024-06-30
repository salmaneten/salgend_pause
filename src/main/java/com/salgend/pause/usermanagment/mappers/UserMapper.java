package com.salgend.pause.usermanagment.mappers;

import com.salgend.pause.usermanagment.dto.UserDto;
import com.salgend.pause.usermanagment.entities.Role;
import com.salgend.pause.usermanagment.entities.User;
import com.salgend.pause.usermanagment.repositories.RoleRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "roleListToString")
    User userDtoToUser(UserDto userDto, @Context RoleRepository roleRepository);

    @Named("roleListToString")
    default Set<Role> roleListToString(Set<String> roles, @Context RoleRepository roleRepository) {
        return roles.stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName)))
                .collect(Collectors.toSet());
    }
}