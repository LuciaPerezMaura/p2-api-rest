package edu.comillas.icai.gitt.pat.spring.practica2;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public record ModeloCarrito(
        @NotBlank(message="Debe rellenar el identificador")
        String idCarrito,

        @NotBlank(message="Debe rellenar el ID del artículo")
        String idArticulo,

        String descripcion,

        @Min(value = 1)
        int unidades,

        @NotNull
        Float precioFinal
) {}