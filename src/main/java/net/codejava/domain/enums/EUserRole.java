package net.codejava.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EUserRole {
    ADMIN("Administrator"),
    CUSTOMER("Customer"),
    OWNER("Car owner");
    private final String title;
}
