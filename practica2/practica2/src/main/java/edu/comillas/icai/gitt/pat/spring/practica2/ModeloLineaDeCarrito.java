package edu.comillas.icai.gitt.pat.spring.practica2;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ModeloLineaDeCarrito(
        Long idLinea,

        @NotNull(message = "Debe indicar el ID del artículo")
        Long idArticulo,

        @NotNull @Min(value = 1, message = "El precio unitario debe ser mayor que 0")
        Long precioUnitario,

        @NotNull @Min(value = 1, message = "Debe haber al menos una unidad")
        Long nUnidades,

        Long coste
) {}
