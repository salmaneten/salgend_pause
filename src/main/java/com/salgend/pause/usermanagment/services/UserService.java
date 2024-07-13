package com.salgend.pause.usermanagment.services;

import com.salgend.pause.usermanagment.dto.UserDto;
import com.salgend.pause.usermanagment.entities.User;
import com.salgend.pause.usermanagment.mappers.UserMapper;
import com.salgend.pause.usermanagment.repositories.RoleRepository;
import com.salgend.pause.usermanagment.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User createUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.userDtoToUser(userDto, roleRepository);
        return this.userRepository.save(user);
    }
}
