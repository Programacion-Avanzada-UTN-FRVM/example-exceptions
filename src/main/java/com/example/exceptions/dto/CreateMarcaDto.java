package com.example.exceptions.constant.marca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateMarcaDto {
    @Size(min = 2, max = 32, message = "La denominación debe tener entre 2 y 32 caracteres.")
    @NotEmpty(message = "La denominación no puede estar vacía.")
    private String denominacion;

    @Size(min = 1, max = 128, message = "La descripción debe tener entre 1 y 128 caracteres.")
    @NotEmpty(message = "La descripción no puede estar vacía.")
    private String descripcion;
}
