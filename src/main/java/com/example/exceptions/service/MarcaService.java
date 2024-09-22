package com.example.exceptions.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.exceptions.constant.EntityStatus;
import com.example.exceptions.dto.marca.CreateMarcaDto;
import com.example.exceptions.interfaces.IMarcaService;
import com.example.exceptions.model.Marca;
import com.example.exceptions.model.repository.IMarcaRepository;

@Service
public class MarcaService implements IMarcaService {
    @Autowired
    private IMarcaRepository repository;

    @Override
    public ResponseEntity<List<Marca>> obtenerMarcas() {
        // Obtener la lista de marcas.
        List<Marca> marcas = this.repository.findByEstado(EntityStatus.ACTIVE);

        // Mappear las marcas a ResponseEntity.
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Marca> obtenerMarca(Long id) {
        // Obtener la marca por su ID.
        Optional<Marca> marca = this.repository.findByIdAndEstado(id, EntityStatus.ACTIVE);

        if (marca.isEmpty())
            // Lanzar una excepción de NotFound.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La marca especificada no existe.");

        // Mappear la marca a ResponseEntity.
        return new ResponseEntity<>(marca.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Marca> crearMarca(CreateMarcaDto marca) {
        // Parsear la marca a una entidad.
        Marca entity = new Marca();
        entity.setDenominacion(marca.getDenominacion());
        entity.setDescripcion(marca.getDescripcion());

        // Guardar la marca en la base de datos.
        entity = this.repository.save(entity);

        // Mappear la marca a ResponseEntity.
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Marca> actualizarMarca(Long id, CreateMarcaDto marca) {
        // Obtener la marca por su ID.
        Marca entity = this.obtenerMarca(id).getBody();

        // Actualizar la marca.
        entity.setDenominacion(marca.getDenominacion());
        entity.setDescripcion(marca.getDescripcion());

        // Guardar los cambios en la base de datos.
        entity = this.repository.save(entity);

        // Mappear la marca a ResponseEntity.
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Override
    public void eliminarMarca(Long id) {
        // Obtener la marca por su ID.
        Marca entity = this.obtenerMarca(id).getBody();

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
