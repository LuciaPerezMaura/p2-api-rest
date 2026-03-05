package edu.comillas.icai.gitt.pat.spring.practica2;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@RestController //Sin esta anotación, la clase sería invisible para la web.
@RequestMapping("/api/carrito") // Esto aplica "/api/carrito" a TODOS los métodos

public class ControladorCarrito {

    private final ServicioCarrito servicio;

    public ControladorCarrito(ServicioCarrito servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModeloCarrito crear(@Valid @RequestBody ModeloCarrito modelo) {
        return servicio.crear(modelo);
    }

    @GetMapping
    public List<ModeloCarrito> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public ModeloCarrito leer(@PathVariable Long id) {
        return servicio.leer(id);
    }

    @PutMapping("/{id}")
    public ModeloCarrito actualizar(@PathVariable Long id, @Valid @RequestBody ModeloCarrito modelo) {
        return servicio.actualizar(id, modelo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable Long id) {
        servicio.borrar(id);
    }

    @PostMapping("/{id}/lineas")
    @ResponseStatus(HttpStatus.CREATED)
    public ModeloLineaDeCarrito añadirLinea(@PathVariable Long id,
                                            @Valid @RequestBody ModeloLineaDeCarrito modelo) {
        return servicio.añadirLinea(id, modelo);
    }

    @DeleteMapping("/{id}/lineas/{idLinea}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarLinea(@PathVariable Long id, @PathVariable Long idLinea) {
        servicio.borrarLinea(id, idLinea);
    }
}
