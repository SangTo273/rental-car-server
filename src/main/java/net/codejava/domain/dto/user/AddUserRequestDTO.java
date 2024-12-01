package net.codejava.domain.dto.user;

import net.codejava.annotation.EnumValid;
import net.codejava.domain.enums.EUserRole;

public record AddUserRequestDTO(
        String password,
        String username,
        String email,
        String phoneNumber,
        @EnumValid(name = "role", enumClass = EUserRole.class) String role) {}
