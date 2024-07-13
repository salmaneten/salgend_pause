package com.salgend.pause.usermanagment;

import com.salgend.pause.usermanagment.dto.RoleDto;
import com.salgend.pause.usermanagment.entities.Permission;
import com.salgend.pause.usermanagment.entities.Role;
import com.salgend.pause.usermanagment.repositories.PermissionRepository;
import com.salgend.pause.usermanagment.services.RoleService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
class RoleCreationTests {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    void testCreateAdminUser_RoleNotFound() {
        RoleDto roleDto = new RoleDto("ADMIN",  Collections.singleton("CREATE_USER"));

        assertThrows(IllegalArgumentException.class, () -> roleService.createRole(roleDto));
    }

    @Test
    void testCreateAdminUser_RoleFound() {
        Permission createUserPermission = new Permission("CREATE_USER");
        permissionRepository.save(createUserPermission);
        RoleDto roleDto = new RoleDto("ADMIN", Collections.singleton("CREATE_USER"));

        Role createdRole = roleService.createRole(roleDto);

        assertNotNull(createdRole);
        assertEquals("ADMIN", createdRole.getName());
        assertTrue(createdRole.getPermissions().stream().anyMatch(role -> role.getName().equals("CREATE_USER")));
    }
}

