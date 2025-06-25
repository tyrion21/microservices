package com.jason.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.jason.domain.BookingStatus;
import com.jason.modal.Booking;
import com.jason.modal.SalonReport;
import com.jason.payload.dto.BookingRequest;
import com.jason.payload.dto.SalonDTO;
import com.jason.payload.dto.ServiceDTO;
import com.jason.payload.dto.UserDTO;

public interface BookingService {
    
    Booking createBooking(BookingRequest booking, 
                            UserDTO user, SalonDTO salon,
                            Set<ServiceDTO> serviceDTOSet) throws Exception;
    List<Booking> getBookingsByCustomer(Long customerId);
    List<Booking> getBookingsBySalon(Long salonId);
    Booking getBookingById(Long id);
    Booking updateBooking(Long bookingId, BookingStatus status);
    List<Booking> getBookingsByDate(LocalDate date, Long salonId);
    SalonReport getSalonReport(Long salonId);


}
