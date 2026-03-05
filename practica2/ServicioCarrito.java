package edu.comillas.icai.gitt.pat.spring.practica2;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ServicioCarrito {

    private final RepositorioCarrito repositorioCarrito;
    private final RepositorioLineaDeCarrito repositorioLinea;

    public ServicioCarrito(RepositorioCarrito repositorioCarrito,
                           RepositorioLineaDeCarrito repositorioLinea) {
        this.repositorioCarrito = repositorioCarrito;
        this.repositorioLinea = repositorioLinea;
    }

    public ModeloCarrito crear(ModeloCarrito modelo) {
        Carrito carrito = new Carrito();
        carrito.idUsuario = modelo.idUsuario();
        carrito.correo = modelo.correo();
        carrito.precioTotal = 0L;
        return toModelo(repositorioCarrito.save(carrito));
    }

    public List<ModeloCarrito> listar() {
        return repositorioCarrito.findAll().stream().map(this::toModelo).toList();
    }

    public ModeloCarrito leer(Long id) {
        return toModelo(buscarOFallar(id));
    }

    public ModeloCarrito actualizar(Long id, ModeloCarrito modelo) {
        Carrito carrito = buscarOFallar(id);
        carrito.idUsuario = modelo.idUsuario();
        carrito.correo = modelo.correo();
        return toModelo(repositorioCarrito.save(carrito));
    }

    public void borrar(Long id) {
        buscarOFallar(id);
        repositorioCarrito.deleteById(id);
    }

    public ModeloLineaDeCarrito añadirLinea(Long idCarrito, ModeloLineaDeCarrito modelo) {
        Carrito carrito = buscarOFallar(idCarrito);

        LineaDeCarrito linea = new LineaDeCarrito();
        linea.idArticulo = modelo.idArticulo();
        linea.precioUnitario = modelo.precioUnitario();
        linea.nUnidades = modelo.nUnidades();
        linea.coste = linea.precioUnitario * linea.nUnidades;
        linea.carrito = carrito;

        LineaDeCarrito guardada = repositorioLinea.save(linea);

        carrito.precioTotal += guardada.coste;
        repositorioCarrito.save(carrito);

        return toModeloLinea(guardada);
    }

    public void borrarLinea(Long idCarrito, Long idLinea) {
        Carrito carrito = buscarOFallar(idCarrito);
        LineaDeCarrito linea = repositorioLinea.findById(idLinea)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Línea no encontrada"));

        if (!linea.carrito.idCarrito.equals(idCarrito)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La línea no pertenece a este carrito");
        }

        carrito.precioTotal -= linea.coste;
        repositorioCarrito.save(carrito);
        repositorioLinea.delete(linea);
    }

    private Carrito buscarOFallar(Long id) {
        return repositorioCarrito.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado"));
    }

    private ModeloCarrito toModelo(Carrito c) {
        return new ModeloCarrito(c.idCarrito, c.idUsuario, c.correo, c.precioTotal);
    }

    private ModeloLineaDeCarrito toModeloLinea(LineaDeCarrito l) {
        return new ModeloLineaDeCarrito(l.idLinea, l.idArticulo, l.precioUnitario, l.nUnidades, l.coste);
    }
}
