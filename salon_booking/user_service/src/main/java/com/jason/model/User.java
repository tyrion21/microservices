package com.jason.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank(message = "Nombre Completo es requerido")
    private String fullName;

    @NotBlank(message = "Usuario es requerido")
    private String username;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email es requerido")
    @Email(message = "Email no es valido")
    private String email;

    private String phone;

    @NotBlank(message = "Role es requerido")
    private String role;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @NotBlank(message = "Password es requerido")
    private String password;
}

