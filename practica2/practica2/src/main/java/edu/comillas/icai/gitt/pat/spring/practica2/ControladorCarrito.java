package edu.comillas.icai.gitt.pat.spring.practica2;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@RestController
@RequestMapping("/api/carrito") // Esto aplica "/api/carrito" a TODOS los métodos
public class ControladorCarrito {

    private final Map<String, ModeloCarrito> carritos = new HashMap<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModeloCarrito crea(@Valid @RequestBody ModeloCarrito carritoNuevo) {
        carritos.put(carritoNuevo.idCarrito(), carritoNuevo);
        return carritoNuevo;
    }

    @GetMapping
    public List<ModeloCarrito> listar() {
        return new ArrayList<>(carritos.values());}

    @GetMapping("/{id}")
    public ModeloCarrito leer(@PathVariable String id) {
        if (!carritos.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado");
        }
        return carritos.get(id);
    }

    @PutMapping("/{id}")
    public ModeloCarrito actualiza(@PathVariable String id, @Valid @RequestBody ModeloCarrito carritoActualizado) {
        if (!carritos.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el carrito para actualizar");
        }
        carritos.put(id, carritoActualizado);
        return carritoActualizado;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borra(@PathVariable String id) {
        if (!carritos.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el carrito a eliminar");
        }
        carritos.remove(id);
    }
}