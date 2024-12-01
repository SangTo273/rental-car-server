package net.codejava.domain.dto.booking;

import lombok.Builder;
import net.codejava.domain.entity.Customer;
import net.codejava.domain.enums.BookingStatus;
import net.codejava.domain.enums.PaymentMethod;

@Builder
public record BookingResponseForOwnerDTO(
        Integer id,
        Customer renterInfor,
        Customer driverInfor,
        String startDateTime,
        String endDateTime,
        PaymentMethod paymentMethod,
        BookingStatus status) {}
