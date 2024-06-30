package com.salgend.pause.usermanagment.repositories;

import com.salgend.pause.usermanagment.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
