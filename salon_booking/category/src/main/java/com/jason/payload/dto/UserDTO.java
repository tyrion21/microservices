package com.jason.payload.dto;

// import com.jason.domain.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String fullName;
    private String email;
    private  String role;

}
