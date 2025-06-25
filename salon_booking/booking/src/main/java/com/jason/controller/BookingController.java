package com.jason.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jason.modal.Booking;
import com.jason.payload.dto.BookingRequest;
import com.jason.payload.dto.SalonDTO;
import com.jason.payload.dto.ServiceDTO;
import com.jason.payload.dto.UserDTO;
import com.jason.service.BookingService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestParam Long salonId,
            @RequestBody BookingRequest bookingRequest) throws Exception {

        UserDTO user = new UserDTO();
        user.setId(1L); // This should be replaced with actual user ID from authentication context

        SalonDTO salon = new SalonDTO();
        salon.setId(salonId); // This should be replaced with actual salon ID from request

        Set<ServiceDTO> serviceDTOSet = new HashSet<>();

        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(1L); // This should be replaced with actual service ID from request
        serviceDTO.setPrice(399);
        serviceDTO.setDuration(45);
        serviceDTO.setName("Haircut for men");

        serviceDTOSet.add(serviceDTO);

        Booking booking = bookingService.createBooking(
                bookingRequest,
                user,
                salon,
                serviceDTOSet);

        return ResponseEntity.ok(booking);
    }


 // create getBookingsByCustomer
    @PostMapping("/customer")
    public ResponseEntity<Set<Booking>> getBookingsByCustomer(@RequestParam Long customerId) {
        Set<Booking> bookings = bookingService.getBookingsByCustomer(customerId);
        return ResponseEntity.ok(bookings);
}
