package com.jason.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jason.model.Salon;
import com.jason.payload.DTO.SalonDTO;
import com.jason.payload.DTO.UserDTO;
import com.jason.service.SalonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/salons")
public class SalonController {

    private final SalonService salonService;

    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO){
        UserDTO userDTO = new UserDTO(); // This should be replaced with actual user retrieval logic
        userDTO.setId(1L); // Example user ID, replace with actual logic to get the logged-in user
        Salon salon = salonService.createSalon(salonDTO, userDTO);
        return null;
    }
    
}
