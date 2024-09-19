package com.example.exceptions.model;

import com.example.exceptions.constant.EntityStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "marcas")
@Data
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Identificador de la marca.
     */
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 32)
    /**
     * Nombre de la marca.
     */
    private String denominacion;

    @Column(nullable = false)
    @Size(min = 1, max = 128)
    /**
     * Descripción de la marca.
     */
    private String descripcion;

    @Column(nullable = false)
    /**
     * Indica el estado de la marca.
     */
    private EntityStatus estado = EntityStatus.ACTIVE;

    /**
     * Marca a la entidad como eliminada.
     *
     * @return
     */
    public void eliminar() {
        this.estado = EntityStatus.DELETED;
    }
}
