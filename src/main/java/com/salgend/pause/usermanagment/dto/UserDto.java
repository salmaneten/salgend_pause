package com.salgend.pause.usermanagment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private final String username;
    private final String password;
    private final String email;
    private final Set<String> roles;
}
