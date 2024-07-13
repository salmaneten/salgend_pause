package com.salgend.pause.usermanagment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class RoleDto {
    private final String name;
    private final Set<String> permissions;
}
