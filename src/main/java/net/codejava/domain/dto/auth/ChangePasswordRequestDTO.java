package net.codejava.domain.dto.auth;

public record ChangePasswordRequestDTO(String newPassword, String oldPassword) {}
