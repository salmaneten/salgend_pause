package com.salgend.pause.usermanagment;

import com.salgend.pause.usermanagment.dto.UserDto;
import com.salgend.pause.usermanagment.entities.Role;
import com.salgend.pause.usermanagment.entities.User;
import com.salgend.pause.usermanagment.repositories.RoleRepository;
import com.salgend.pause.usermanagment.services.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
public class UserCreationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testCreateAdminUser_RoleNotFound() {
        UserDto userDto = new UserDto("admin", "admin123", "admin@example.com", Collections.singleton("ADMIN"));

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(userDto));
    }

    @Test
    public void testCreateAdminUser_RoleFound() {
        Role adminRole = new Role("ADMIN");
        roleRepository.save(adminRole);
        UserDto userDto = new UserDto("admin", "admin123", "admin@example.com", Collections.singleton("ADMIN"));

        User createdUser = userService.createUser(userDto);

        assertNotNull(createdUser);
        assertEquals("admin", createdUser.getUsername());
        assertTrue(createdUser.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN")));
    }

    @Test
    public void testCreateManagerUser_RoleFound() {
        Role managerRole = new Role("MANAGER");
        roleRepository.save(managerRole);
        UserDto userDto = new UserDto("manager", "manager123", "manager@example.com", Collections.singleton("MANAGER"));

        User createdUser = userService.createUser(userDto);

        assertNotNull(createdUser);
        assertEquals("manager", createdUser.getUsername());
        assertTrue(createdUser.getRoles().stream().anyMatch(role -> role.getName().equals("MANAGER")));
    }

    @Test
    public void testCreateManagerAndAdminUser_ManagerRoleNotFound() {
        Role adminRole = new Role("ADMIN");
        roleRepository.save(adminRole);
        UserDto userDto = new UserDto("manager", "manager123", "manager@example.com", Set.of("MANAGER", "ADMIN"));

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(userDto));
    }
}
