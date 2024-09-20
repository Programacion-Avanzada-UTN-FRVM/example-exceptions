package com.example.exceptions.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.exceptions.dto.CreateMarcaDto;
import com.example.exceptions.model.Marca;

public interface IMarcaService {
    /**
     * Obtiene un listado de todas las marcas del dominio.
     *
     * @return
     */
    public ResponseEntity<List<Marca>> obtenerMarcas();

    /**
     * Obtiene una marca por su identificador.
     *
     * @param id El identificador de la Marca.
     *
     * @return La Marca encontrada, si hubo éxito
     */
    public ResponseEntity<Marca> obtenerMarca(Long id);

    /**
     * Crea una nueva marca en el dominio.
     *
     * @param marca La marca a crear.
     *
     * @return La marca creada, si hubo éxito.
     */
    public ResponseEntity<Marca> crearMarca(CreateMarcaDto marca);

    /**
     * Actualiza una marca en el dominio.
     *
     * @param id    El identificador de la marca a actualizar.
     * @param marca La marca actualizada.
     *
     * @return La marca actualizada, si hubo éxito.
     */
    public ResponseEntity<Marca> actualizarMarca(Long id, CreateMarcaDto marca);

    /**
     * Elimina una marca del dominio.
     *
     * @param id El identificador de la marca a eliminar.
     *
     * @return La marca eliminada, si hubo éxito.
     */
    public void eliminarMarca(Long id);
}
