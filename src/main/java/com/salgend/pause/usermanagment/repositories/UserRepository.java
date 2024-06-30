package com.salgend.pause.usermanagment.repositories;

import com.salgend.pause.usermanagment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
