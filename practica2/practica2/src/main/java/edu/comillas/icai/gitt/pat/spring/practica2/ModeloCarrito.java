package edu.comillas.icai.gitt.pat.spring.practica2;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public record ModeloCarrito(
        Long idCarrito,

        @NotNull(message = "Debe indicar el ID de usuario")
        Long idUsuario,

        @NotBlank(message = "Debe rellenar el correo del usuario")
        String correo,

        Long precioTotal
) {}
