package net.codejava.domain.dto.user;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import net.codejava.domain.enums.EUserRole;

@Builder
public record UserDetailResponseDTO(
        Integer id,
        String username,
        String email,
        @Temporal(TemporalType.DATE) @DateTimeFormat(pattern = "yyyy/MM/dd") Date birthday,
        String citizenId,
        String phoneNumber,
        String address,
        String drivingLicense,
        double wallet,
        Date createdAt,
        Date updatedAt,
        boolean isActive,
        String avatar,
        EUserRole role) {}
