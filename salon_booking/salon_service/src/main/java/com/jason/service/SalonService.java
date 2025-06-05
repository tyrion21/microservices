package com.jason.service;
import java.util.List;

import com.jason.model.Salon;
import com.jason.payload.DTO.SalonDTO;
import com.jason.payload.DTO.UserDTO;

public interface SalonService {
    Salon createSalon(SalonDTO salon, UserDTO user);
    Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId);
    List<Salon> getAllSalons();
    Salon getSalonById(Long salonId);
    Salon getSalonByOwnerId(Long ownerId);
    List<Salon> getSalonsByCity(String city);
    void deleteSalon(Long salonId);
}
