package net.codejava.domain.dto.booking;

import net.codejava.domain.entity.Customer;

public record UpdBookingRequestDTO(Customer renterInfor, Customer driverInfor) {}
