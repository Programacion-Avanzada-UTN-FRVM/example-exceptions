package com.example.exceptions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exceptions.dto.marca.CreateMarcaDto;
import com.example.exceptions.dto.marca.MarcaDto;
import com.example.exceptions.dto.marca.UpdateMarcaDto;
import com.example.exceptions.interfaces.IMarcaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "${api.version}/marca")
public class MarcaController {
    @Autowired
    private IMarcaService service;

    @GetMapping
    public ResponseEntity<List<MarcaDto>> getAllMarcas() {
        return this.service.obtenerMarcas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDto> getMarcaById(@PathVariable Long id) {
        return this.service.obtenerMarca(id);
    }

    @PostMapping
    public ResponseEntity<MarcaDto> createMarca(@Valid @RequestBody CreateMarcaDto body) {
        return this.service.crearMarca(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaDto> updateMarca(@PathVariable Long id, @Valid @RequestBody UpdateMarcaDto body) {
        return this.service.actualizarMarca(id, body);
    }

    @DeleteMapping("/{id}")
    public void deleteMarca(@PathVariable Long id) {
        this.service.eliminarMarca(id);
    }
}
