package com.jason.controller;

import com.jason.modal.ServiceOffering;
import com.jason.payload.dto.CategoryDTO;
import com.jason.payload.dto.SalonDTO;
import com.jason.payload.dto.ServiceDTO;
// import com.jason.payload.dto.UserDTO;
import com.jason.service.ServiceOfferingService;
// import com.jason.service.clients.CategoryFeignClient;
// import com.jason.service.clients.SalonFeignClient;
// import com.jason.service.clients.UserFeignClient;
import lombok.RequiredArgsConstructor;

// import java.security.Provider.Service;

// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service-offering/salon-owner")
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;
    // private final SalonFeignClient salonService;
    // private final CategoryFeignClient categoryService;

    @PostMapping
    public ResponseEntity<ServiceOffering> createService(
            // @RequestHeader("Authorization") String jwt,
            @RequestBody ServiceDTO serviceDTO)
    // throws Exception
    {

        // SalonDTO salon=salonService.getSalonByOwner(jwt).getBody();
        SalonDTO salon = new SalonDTO();
        salon.setId(1L);

        // CategoryDTO category=categoryService
        // .getCategoryById(service.getCategory()).getBody();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(serviceDTO.getCategoryId());

        ServiceOffering serviceOfferings = serviceOfferingService
                .createService(serviceDTO, salon, categoryDTO);

        return ResponseEntity.ok(serviceOfferings);
    }

    // @PatchMapping("/{serviceId}")
    // public ResponseEntity<ServiceOffering> updateService(
    // @PathVariable Long serviceId,
    // @RequestBody ServiceOffering service) throws Exception {
    // ServiceOffering updatedService = serviceOfferingService
    // .updateService(serviceId, service);
    // if (updatedService != null) {
    // return new ResponseEntity<>(updatedService, HttpStatus.OK);
    // }
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }

    @PostMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateService(
            @PathVariable Long id,
            @RequestBody ServiceOffering serviceOffering) throws Exception {

        ServiceOffering serviceOfferings = serviceOfferingService.updateService(id, serviceOffering);
        return ResponseEntity.ok(serviceOfferings);
    }

}
