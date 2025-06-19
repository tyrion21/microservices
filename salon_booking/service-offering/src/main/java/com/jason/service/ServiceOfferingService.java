package com.jason.service;

import com.jason.modal.ServiceOffering;
import com.jason.payload.dto.CategoryDTO;
import com.jason.payload.dto.SalonDTO;
import com.jason.payload.dto.ServiceDTO;

import java.util.List;
import java.util.Set;

public interface ServiceOfferingService {


    ServiceOffering createService(
            ServiceDTO service,
            SalonDTO salon,
            CategoryDTO category
    );

    com.jason.modal.ServiceOffering updateService(
            Long serviceId,
            ServiceOffering service
    ) throws Exception;

    Set<ServiceOffering> getAllServicesBySalonId(Long salonId,Long categoryId);

    com.jason.modal.ServiceOffering getServiceById(Long serviceId);

    Set<ServiceOffering> getServicesByIds(Set<Long> ids);
}
