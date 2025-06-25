package com.jason.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HomeController {
    
    @GetMapping("/")
    public String HomeControllerHandler() {
        return "Booking microservice for salon booking system";
    }
    
}
