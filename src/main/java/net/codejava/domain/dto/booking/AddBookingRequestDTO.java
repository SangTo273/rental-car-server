package net.codejava.domain.dto.booking;

import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import net.codejava.annotation.AfterNowTime;
import net.codejava.annotation.RentalTimeMatching;
import net.codejava.constant.TimeFormatConstant;
import net.codejava.domain.entity.Customer;
import net.codejava.domain.enums.PaymentMethod;

@RentalTimeMatching(startTime = "startDateTime", endTime = "endDateTime")
public record AddBookingRequestDTO(
        Integer carId,
        Customer[] customers,
        PaymentMethod paymentMethod,
        @NotBlank(message = "The start rental time is not blank")
                @AfterNowTime(message = "The start rental time is after now")
                @JsonFormat(pattern = TimeFormatConstant.DATETIME_FORMAT)
                String startDateTime,
        @NotBlank(message = "The end rental time is not blank")
                @JsonFormat(pattern = TimeFormatConstant.DATETIME_FORMAT)
                String endDateTime) {}
