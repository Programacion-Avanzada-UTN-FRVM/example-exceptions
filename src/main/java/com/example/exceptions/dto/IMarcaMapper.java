package com.example.exceptions.dto;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.example.exceptions.dto.marca.MarcaDto;
import com.example.exceptions.model.Marca;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
/**
 * Los Mappers de MapStruct son interfaces que funcionan como inyectables y
 * proveen métodos para convertir entidades en DTOs y viceversa.
 *
 * El poder de los mappers se visualiza cuando se requiere hacer cosas mas
 * complejas, como por ejemplo cambiar los valores o nombres de propiedad de los
 * DTOs y hacer las equivalencias con los de la entidad.
 * 
 * Marca.denominacion -> MarcaDto.denominacion
 * Marca.descripcion -> MarcaDto.descripcion
 * 
 * Pero, si deseo puedo hacer:
 * Marca.denominacion -> MarcaDto.name
 * Marca.descripcion -> MarcaDto.description
 * 
 * Ademas, puedo cambiar valores de las propiedades, como por ejemplo:
 * Marca.estado = ACTIVE -> MarcaDto.status = "Vigente"
 * Marca.estado = DELETED -> MarcaDto.status = "Eliminado"
 *
 * Todas estas utilidades y mucho mas las provee MapStruct.
 */
public interface IMarcaMapper {
    /**
     * Convierte una entidad de Marca en su equivalente de DTO.
     *
     * @param marca La entidad de Marca.
     *
     * @return La entidad de Marca convertida a DTO.
     */
    @Mapping(target = "type", source = "tipo")
    MarcaDto marcaToMarcaDto(Marca marca);

    /**
     * Convierte un DTO de Marca en su equivalente de entidad.
     *
     * @param marcaDto El DTO de Marca.
     *
     * @return El DTO de Marca convertido a entidad.
     */
    @Mapping(target = "tipo", source = "type")
    Marca marcaDtoToMarca(MarcaDto marcaDto);

    /**
     * Convierte una entidad de Marca a DTO eliminando el atributo de ID.
     *
     * @param marca La entidad de Marca.
     *
     * @return La entidad de Marca convertida a DTO.
     */
    @Mapping(target = "id", ignore = true)
    MarcaDto marcaToMarcaDtoWithoutId(Marca marca);
}