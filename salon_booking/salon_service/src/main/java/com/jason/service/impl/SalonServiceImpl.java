package com.jason.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jason.model.Salon;
import com.jason.payload.DTO.SalonDTO;
import com.jason.payload.DTO.UserDTO;
import com.jason.repository.SalonRepository;
import com.jason.service.SalonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public Salon createSalon(SalonDTO req, UserDTO user) {
        Salon salon = new Salon();
        salon.setName(req.getName());
        salon.setAddress(req.getAddress());
        salon.setPhone(req.getPhone());
        salon.setEmail(req.getEmail());
        salon.setCity(req.getCity());
        salon.setOwnerId(user.getId());
        salon.setOpeningTime(req.getOpeningTime());
        salon.setClosingTime(req.getClosingTime());
        salon.setImages(req.getImages());

        return salonRepository.save(salon); // Return the created salon object
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws RuntimeException {

        Salon existingSalon = salonRepository.findById(salonId).orElse(null);
        if (existingSalon != null && salon.getOwnerId().equals(user.getId())) {
            existingSalon.setName(salon.getName());
            existingSalon.setAddress(salon.getAddress());
            existingSalon.setPhone(salon.getPhone());
            existingSalon.setEmail(salon.getEmail());
            existingSalon.setCity(salon.getCity());
            existingSalon.setOwnerId(user.getId());
            existingSalon.setOpeningTime(salon.getOpeningTime());
            existingSalon.setClosingTime(salon.getClosingTime());
            existingSalon.setImages(salon.getImages());
            return salonRepository.save(existingSalon); // Return the updated salon object
        }
        throw new RuntimeException("Salon not found with id: " + salonId);
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) {
        Salon salon = salonRepository.findById(salonId).orElse(null);
        if (salon == null) {
            throw new RuntimeException("Salon not found with id: " + salonId);
        }
        return salon;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        Salon salon = salonRepository.findByOwnerId(ownerId);
        if (salon == null) {
            throw new RuntimeException("Salon not found with owner id: " + ownerId);
        }
        return salon;
    }

    @Override
    public List<Salon> getSalonsByCity(String city) {
        List<Salon> salons = salonRepository.searchSalons(city);
        if (salons.isEmpty()) {
            throw new RuntimeException("No salons found in city: " + city);
        }
        return salons;
    }

    @Override
    public void deleteSalon(Long salonId) {
        if (!salonRepository.existsById(salonId)) {
            throw new RuntimeException("Salon not found with id: " + salonId);
        }
        salonRepository.deleteById(salonId);
    }
}
