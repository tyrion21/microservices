package com.jason.payload.DTO;

import java.time.LocalTime;
import java.util.List;
import lombok.Data;

@Data
public class SalonDTO {
    
    private Long id;
    private String name;
    private List<String> images;
    private String address;
    private String phone;
    private String email;
    private String city;
    private Long ownerId;
    private UserDTO owner;
    private LocalTime openingTime;
    private LocalTime closingTime;
}
