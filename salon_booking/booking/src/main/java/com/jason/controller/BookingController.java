package com.jason.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jason.domain.BookingStatus;
import com.jason.mapper.BookingMapper;
import com.jason.modal.Booking;
import com.jason.modal.SalonReport;
import com.jason.payload.dto.BookedSlotsDTO;
import com.jason.payload.dto.BookingDTO;
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
    public ResponseEntity<Set<BookingDTO>> getBookingsByCustomer(

    ) {

        List<Booking> bookings = bookingService.getBookingsByCustomer(1L);

        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    private Set<BookingDTO> getBookingDTOs(List<Booking> bookings) {
        return bookings.stream()
                .map(BookingMapper::toDTO)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(
            @PathVariable Long bookingId) throws Exception {
        Booking booking = bookingService.getBookingById(bookingId);

        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(
            @PathVariable Long bookingId,
            @RequestParam BookingStatus status) throws Exception {
        Booking booking = bookingService.updateBooking(bookingId, status);

        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    @GetMapping("/slots/salon/{salonId}/date/{date}")
    public ResponseEntity<List<BookedSlotsDTO>> getBookedSlots (
            @PathVariable Long salonId,
            @PathVariable LocalDate date
            // @RequestHeader("Authorization") String jwt
    ) throws Exception {

        List<Booking> bookings = bookingService.getBookingsByDate(date,salonId);

        List<BookedSlotsDTO> slotsDTOS = bookings.stream()
                .map(booking -> {
                    BookedSlotsDTO slotDto = new BookedSlotsDTO();

                    slotDto.setStartTime(booking.getStartTime());
                    slotDto.setEndTime(booking.getEndTime());


                    return slotDto;
                })
                .toList();


        return ResponseEntity.ok(slotsDTOS);


    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDTO>> getBookingsBySalon(
            @RequestParam Long salonId) {

        List<Booking> bookings = bookingService.getBookingsBySalon(salonId);

        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport(
            // @RequestHeader("Authorization") String jwt
    ) throws Exception {

        // UserDTO user = userService.getUserFromJwtToken(jwt).getBody();

        // SalonDTO salon = salonService.getSalonByOwner(jwt).getBody();

        SalonReport report = bookingService.getSalonReport(1L);


        return ResponseEntity.ok(report);

    }

}
