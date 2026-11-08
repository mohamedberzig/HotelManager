package com.hotelmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.hotelmanager.model.TypeChambre;
import com.hotelmanager.service.TypeChambreService;

@RestController
@RequestMapping("/api/types-chambres")
public class TypeChambreController {

    private final TypeChambreService typeChambreService;

    public TypeChambreController(TypeChambreService typeChambreService) {
        this.typeChambreService = typeChambreService;
    }

    @GetMapping
    public List<TypeChambre> getAllTypesChambres() {
        return typeChambreService.getAllTypesChambres();
    }

    @GetMapping("/{id}")
    public TypeChambre getTypeChambreById(@PathVariable Long id) {
        return typeChambreService.getTypeChambreById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TypeChambre createTypeChambre(@Valid @RequestBody TypeChambre typeChambre) {
        return typeChambreService.createTypeChambre(typeChambre);
    }

    @PutMapping("/{id}")
    public TypeChambre updateTypeChambre(
            @PathVariable Long id,
            @Valid @RequestBody TypeChambre typeChambre) {
        return typeChambreService.updateTypeChambre(id, typeChambre);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTypeChambre(@PathVariable Long id) {
        typeChambreService.deleteTypeChambre(id);
    }
}