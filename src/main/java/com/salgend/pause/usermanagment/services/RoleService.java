package com.salgend.pause.usermanagment.services;

import com.salgend.pause.usermanagment.dto.RoleDto;
import com.salgend.pause.usermanagment.entities.Role;
import com.salgend.pause.usermanagment.mappers.RoleMapper;
import com.salgend.pause.usermanagment.repositories.PermissionRepository;
import com.salgend.pause.usermanagment.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public Role createRole(RoleDto roleDto) {
        Role role = RoleMapper.INSTANCE.roleDtoToRole(roleDto, permissionRepository);
        return this.roleRepository.save(role);
    }
}
