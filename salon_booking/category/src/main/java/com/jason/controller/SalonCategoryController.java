package com.jason.controller;

import com.jason.modal.Category;
import com.jason.payload.dto.SalonDTO;
// import com.jason.payload.dto.UserDTO;
import com.jason.service.CategoryService;
import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories/salon-owner")
@RequiredArgsConstructor
public class SalonCategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(
            @RequestBody Category category
            // @RequestHeader("Authorization") String jwt) throws Exception 
    ){
       
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        // Category savedCategory = categoryService.saveCategory(category, salon);
        Category savedCategory = categoryService.saveCategory(category, salonDTO);
        // return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
        return ResponseEntity.ok(savedCategory);
    }
}
