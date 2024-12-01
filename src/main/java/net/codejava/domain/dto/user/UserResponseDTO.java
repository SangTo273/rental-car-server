package net.codejava.domain.dto.user;

import lombok.Builder;
import net.codejava.domain.enums.EUserRole;

@Builder
public record UserResponseDTO(
        Integer userId,
        String username,
        String email,
        String phoneNumber,
        Double wallet,
        String avatar,
        EUserRole role) {}
