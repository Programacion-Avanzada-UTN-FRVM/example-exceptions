package com.example.exceptions.dto.marca;

import com.example.exceptions.model.Marca.TipoMarca;

import lombok.Data;

@Data
public class MarcaDto {
    // Estos campos son identicos a los de la entidad, se mappean automaticamente.
    private Long id;
    private String denominacion;
    private String descripcion;

    // Este campo es diferente al de la entidad en nombre.
    // Revisar `IMarcaMapper` para ver su valor agregado.
    private TipoMarca type;
}