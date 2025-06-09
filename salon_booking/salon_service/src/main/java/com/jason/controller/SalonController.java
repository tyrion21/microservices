package com.jason.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jason.mapper.SalonMapper;
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

    // http://localhost:5002/api/salons

    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO) {
        UserDTO userDTO = new UserDTO(); // This should be replaced with actual user retrieval logic
        userDTO.setId(1L); // Example user ID, replace with actual logic to get the logged-in user
        Salon salon = salonService.createSalon(salonDTO, userDTO);
        SalonDTO salonDTO1 = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO1);
    }

    // http://localhost:5002/api/salons/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<SalonDTO> updateSalon(
            @PathVariable("id") Long salonId,
            @RequestBody SalonDTO salonDTO) throws Exception {
        UserDTO userDTO = new UserDTO(); // This should be replaced with actual user retrieval logic
        userDTO.setId(1L); // Example user ID, replace with actual logic to get the logged-in user
        Salon salon = salonService.updateSalon(salonDTO, userDTO, salonId);
        SalonDTO salonDTO1 = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO1);
    }

    // http://localhost:5002/api/salons
    @GetMapping()
    public ResponseEntity<List<SalonDTO>> getSalons() {
        UserDTO userDTO = new UserDTO(); // This should be replaced with actual user retrieval logic
        userDTO.setId(1L); // Example user ID, replace with actual logic to get the logged-in user
        List<Salon> salons = salonService.getAllSalons();

        List<SalonDTO> salonDTOs = salons.stream().map(SalonMapper::mapToDTO).toList();
        return ResponseEntity.ok(salonDTOs);
    }

    // http://localhost:5002/api/salons/{id}
    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable Long salonId) throws Exception {
        // Simulación de un usuario (si no es necesario, esto se puede eliminar)
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        // Obtener el salón desde el servicio
        Salon salon = salonService.getSalonById(salonId);

        // Mapear entidad a DTO
        SalonDTO salonDTO = SalonMapper.mapToDTO(salon);

        // Retornar respuesta HTTP 200 con el objeto
        return ResponseEntity.ok(salonDTO);
    }
    // http://localhost:5002/api/salons/search?city=cityName
    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> searchSalons(
        @RequestParam("city") String city
    ) throws Exception {
        UserDTO userDTO = new UserDTO(); // This should be replaced with actual user retrieval logic
        userDTO.setId(1L); // Example user ID, replace with actual logic to get the logged-in user
        List<Salon> salons = salonService.getSalonsByCity(city);

        List<SalonDTO> salonDTOs = salons.stream().map(SalonMapper::mapToDTO).toList();
        return ResponseEntity.ok(salonDTOs);
    }

    @GetMapping("/owner")
    public ResponseEntity<SalonDTO> getSalonByOwnerId(
        @PathVariable Long salonId
    ) throws Exception {
        UserDTO userDTO = new UserDTO(); // This should be replaced with actual user retrieval logic
        userDTO.setId(1L); // Example user ID, replace with actual logic to get the logged-in user
        Salon salon = salonService.getSalonByOwnerId(userDTO.getId());

        SalonDTO salonDTO = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO);
    }
}
