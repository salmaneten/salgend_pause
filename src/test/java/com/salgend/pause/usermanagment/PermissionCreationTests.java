package com.salgend.pause.usermanagment;

import com.salgend.pause.usermanagment.dto.PermissionDto;
import com.salgend.pause.usermanagment.services.PermissionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
class PermissionCreationTests {

    @Autowired
    private PermissionService permissionService;

    @Test
    void testCreatePermission() {
        PermissionDto createUserPermission = new PermissionDto("CREATE_USER");
        var createdPermission = permissionService.createPermission(createUserPermission);

        assertNotNull(createdPermission);
        assertEquals("CREATE_USER", createdPermission.getName());
    }

    @Test
    void testCreatePermission_WithSameName() {
        PermissionDto createUserPermission = new PermissionDto("CREATE_USER");
        permissionService.createPermission(createUserPermission);

        assertThrows(DataIntegrityViolationException.class, () -> permissionService.createPermission(createUserPermission));
    }
}
