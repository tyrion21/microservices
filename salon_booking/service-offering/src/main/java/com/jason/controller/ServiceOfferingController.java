package com.jason.controller;

import com.jason.modal.ServiceOffering;
// import com.jason.payload.dto.CategoryDTO;
// import com.jason.payload.dto.SalonDTO;
// import com.jason.payload.dto.ServiceDTO;
// import com.jason.payload.dto.UserDTO;
import com.jason.service.ServiceOfferingService;
// import com.jason.service.clients.CategoryFeignClient;
// import com.jason.service.clients.SalonFeignClient;
// import com.jason.service.clients.UserFeignClient;
import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/api/service-offering")
@RestController
@RequiredArgsConstructor
public class ServiceOfferingController {


    private final ServiceOfferingService serviceOfferingService;


    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServicesBySalonId(
            @PathVariable Long salonId,
            @RequestParam(required = false) Long categoryId) {
        Set<ServiceOffering> services =  serviceOfferingService
                .getAllServicesBySalonId(salonId,categoryId);

            return ResponseEntity.ok(services);

    }


    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> getServiceById(@PathVariable Long serviceId) throws Exception {
        ServiceOffering service = serviceOfferingService
                .getServiceById(serviceId);
        if (service == null) {
            throw new Exception("service not found with id "+serviceId);
        }
            return ResponseEntity.ok(service);

    }

    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds(
            @PathVariable Set<Long> ids) {
        Set<ServiceOffering> services =  serviceOfferingService
                .getServicesByIds(ids);

        return ResponseEntity.ok(services);


    }


}
