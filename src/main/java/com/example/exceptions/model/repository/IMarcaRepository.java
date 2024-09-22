package com.example.exceptions.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exceptions.constant.EntityStatus;
import com.example.exceptions.model.Marca;

public interface IMarcaRepository extends JpaRepository<Marca, Long> {
    /**
     * Obtiene una marca por su ID.
     *
     * @param id     El identificador de la Marca.
     * @param estado El estado de la Marca a buscar.
     *
     * @return
     */
    Optional<Marca> findByIdAndEstado(Long id, EntityStatus estado);

    /**
     * Obtiene una marca por su denominación.
     *
     * @param denominacion La denominación de la Marca.
     * @param estado       El estado de la Marca a buscar.
     *
     * @return
     */
    Optional<Marca> findByDenominacionAndEstado(String denominacion, EntityStatus estado);

    /**
     * Obtiene una marca por su estado.
     *
     * @param estado El estado de la Marca a buscar.
     *
     * @return
     */
    List<Marca> findByEstado(EntityStatus estado);
}
