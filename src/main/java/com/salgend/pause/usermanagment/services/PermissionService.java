package com.salgend.pause.usermanagment.services;

import com.salgend.pause.usermanagment.dto.PermissionDto;
import com.salgend.pause.usermanagment.entities.Permission;
import com.salgend.pause.usermanagment.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.salgend.pause.usermanagment.mappers.PermissionMapper;

@RequiredArgsConstructor
@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public Permission createPermission(PermissionDto permissionDto) {
        Permission permission = PermissionMapper.INSTANCE.permissionDtoToPermission(permissionDto);
        return this.permissionRepository.save(permission);
    }
}
