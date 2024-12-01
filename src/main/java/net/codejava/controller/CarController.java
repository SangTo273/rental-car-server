package net.codejava.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.codejava.constant.Endpoint;
import net.codejava.domain.dto.booking.BookingResponseForOwnerDTO;
import net.codejava.domain.dto.car.*;
import net.codejava.domain.dto.meta.MetaRequestDTO;
import net.codejava.domain.dto.meta.MetaResponseDTO;
import net.codejava.domain.entity.User;
import net.codejava.responses.MetaResponse;
import net.codejava.responses.Response;
import net.codejava.service.BookingService;
import net.codejava.service.CarService;
import net.codejava.utility.AuthUtil;
import net.codejava.utility.JwtTokenUtil;

@Tag(name = "Cars")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CarController {
    private final JwtTokenUtil jwtTokenUtil;
    private final CarService carService;
    private final BookingService bookingService;

    @GetMapping(Endpoint.V1.Car.BASE)
    public ResponseEntity<MetaResponse<MetaResponseDTO, List<CarResponseDTO>>> searchCars(
            @ParameterObject @Valid SearchCarRequestDTO requestDTO, @ParameterObject MetaRequestDTO metaRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.searchCarV2(
                        requestDTO.address(), requestDTO.startTime(), requestDTO.endTime(), metaRequestDTO));
    }

    @PostMapping(Endpoint.V1.Car.BASE)
    public ResponseEntity<Response<CarDetailResponseDTO>> addCar(
            HttpServletRequest servletRequest, @RequestBody @Valid AddCarRequestDTO requestDTO) {
        Integer idToken =
                Integer.valueOf(jwtTokenUtil.getAccountId(servletRequest.getHeader(HttpHeaders.AUTHORIZATION)));
        return ResponseEntity.status(HttpStatus.OK).body(carService.addCar(idToken, requestDTO));
    }

    @GetMapping(Endpoint.V1.Car.GET_LIST_FOR_OWNER)
    public ResponseEntity<MetaResponse<MetaResponseDTO, List<CarResponseDTO>>> getListCarForOwner(
            HttpServletRequest servletRequest, @ParameterObject MetaRequestDTO requestDTO) {
        Integer ownerId =
                Integer.valueOf(jwtTokenUtil.getAccountId(servletRequest.getHeader(HttpHeaders.AUTHORIZATION)));
        return ResponseEntity.status(HttpStatus.OK).body(carService.getListCarByOwner(requestDTO, ownerId));
    }

    @GetMapping(Endpoint.V1.Car.DETAILS)
    public ResponseEntity<Response<CarDetailResponseDTO>> getCarDetail(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getCarDetail(id));
    }

    @GetMapping(Endpoint.V1.Car.DETAILS_FOR_OWNER)
    public ResponseEntity<Response<CarDetailResponseForOwnerDTO>> getCarDetailForOwner(
            @PathVariable(name = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getCarDetailForOwner(id));
    }

    @PatchMapping(Endpoint.V1.Car.DETAILS_FOR_OWNER)
    public ResponseEntity<Response<CarDetailResponseDTO>> updateCar(
            @PathVariable(name = "id") Integer id, @RequestBody @Valid UpdCarRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.updateCar(id, requestDTO));
    }

    @PatchMapping(Endpoint.V1.Car.STATUS)
    public ResponseEntity<Response<String>> changeStatus(@PathVariable(name = "id") Integer carId) {
        User user =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(carService.stopRentingCar(carId));
    }

    @GetMapping(Endpoint.V1.Car.LIST_CAR_BOOKINGS)
    public ResponseEntity<MetaResponse<MetaResponseDTO, List<BookingResponseForOwnerDTO>>> getListBookingByCarId(
            @ParameterObject MetaRequestDTO metaRequestDTO, @PathVariable(name = "id") Integer carId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookingService.getListBookingByCarId(
                        metaRequestDTO, carId, AuthUtil.getRequestedUser().getId()));
    }
}
