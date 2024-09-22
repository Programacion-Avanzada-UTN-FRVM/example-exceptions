package com.example.exceptions.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.exceptions.constant.EntityStatus;
import com.example.exceptions.dto.IMarcaMapper;
import com.example.exceptions.dto.marca.CreateMarcaDto;
import com.example.exceptions.dto.marca.MarcaDto;
import com.example.exceptions.interfaces.IMarcaService;
import com.example.exceptions.model.Marca;
import com.example.exceptions.model.repository.IMarcaRepository;

@Service
public class MarcaService implements IMarcaService {
    @Autowired
    private IMarcaRepository repository;

    @Autowired
    private IMarcaMapper mapper;

    @Override
    public ResponseEntity<List<MarcaDto>> obtenerMarcas() {
        // Obtener la lista de marcas.
        List<Marca> marcas = this.repository.findByEstado(EntityStatus.ACTIVE);

        // Utilizar MapStruct para mapear las marcas a DTO.
        List<MarcaDto> dtos = new ArrayList<>();
        marcas.forEach(marca -> {
            dtos.add(this.mapper.marcaToMarcaDto(marca));

            // ALTERNATIVA: Eliminar IDs de los DTOs con el Mapper.
            //dtos.add(this.mapper.marcaToMarcaDtoWithoutId(marca));
        });

        // Mappear las marcas a ResponseEntity.
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MarcaDto> obtenerMarca(Long id) {
        // Obtener la marca por su ID.
        Optional<Marca> marca = this.repository.findByIdAndEstado(id, EntityStatus.ACTIVE);

        if (marca.isEmpty())
            // Lanzar una excepción de NotFound.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La marca especificada no existe.");

        // Utilizar MapStruct para mapear la marca a DTO.
        MarcaDto dto = this.mapper.marcaToMarcaDto(marca.get());

        // Mappear la marca a ResponseEntity.
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MarcaDto> crearMarca(CreateMarcaDto marca) {
        // Existe la marca con esta denominacion?
        Optional<Marca> marcaExistente = this.repository.findByDenominacionAndEstado(marca.getDenominacion(),
                EntityStatus.ACTIVE);

        if (marcaExistente.isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La marca especificada ya existe.");

        // Parsear la marca a una entidad.
        Marca entity = new Marca();
        entity.setDenominacion(marca.getDenominacion());
        entity.setDescripcion(marca.getDescripcion());

        // Guardar la marca en la base de datos.
        entity = this.repository.save(entity);

        // Utilizar MapStruct para mapear la marca a DTO.
        MarcaDto dto = this.mapper.marcaToMarcaDto(entity);

        // Mappear la marca a ResponseEntity.
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<MarcaDto> actualizarMarca(Long id, CreateMarcaDto marca) {
        // Obtener la marca por su ID.
        Marca entity = this.mapper.marcaDtoToMarca(this.obtenerMarca(id).getBody());

        // Actualizar la marca.
        entity.setDenominacion(marca.getDenominacion());
        entity.setDescripcion(marca.getDescripcion());

        // Guardar los cambios en la base de datos.
        entity = this.repository.save(entity);

        // Utilizar MapStruct para mapear la marca a DTO.
        MarcaDto dto = this.mapper.marcaToMarcaDto(entity);

        // Mappear la marca a ResponseEntity.
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public void eliminarMarca(Long id) {
        // Obtener la marca por su ID.
        Marca entity = this.mapper.marcaDtoToMarca(this.obtenerMarca(id).getBody());

        // Verificar si la marca ya fue eliminada.
        if (entity.getEstado() == EntityStatus.DELETED)
            // Lanzar una excepción.
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La marca especificada no existe/está eliminada.");

        // Eliminar la marca.
        entity.eliminar();

        // Guardar los cambios en la base de datos.
        this.repository.save(entity);
    }
}
