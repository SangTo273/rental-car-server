package net.codejava.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ECustomerType {
    RENTER("Renter"),
    DRIVER("Driver");
    private final String title;
}
