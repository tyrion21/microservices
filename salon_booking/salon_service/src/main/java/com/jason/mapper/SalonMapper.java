package com.jason.mapper;

import com.jason.model.Salon;
import com.jason.payload.DTO.SalonDTO;

public class SalonMapper {
    
    public static SalonDTO mapToDTO(Salon salon) {
        if (salon == null) {
            return null;
        }
        
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(salon.getId());
        salonDTO.setName(salon.getName());
        salonDTO.setAddress(salon.getAddress());
        salonDTO.setPhone(salon.getPhone());
        salonDTO.setEmail(salon.getEmail());
        salonDTO.setCity(salon.getCity());
        salonDTO.setOwnerId(salon.getOwnerId());
        salonDTO.setOpeningTime(salon.getOpeningTime());
        salonDTO.setClosingTime(salon.getClosingTime());
        salonDTO.setImages(salon.getImages());
        
        return salonDTO;
    }

}
