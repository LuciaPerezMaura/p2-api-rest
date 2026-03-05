package edu.comillas.icai.gitt.pat.spring.practica2;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idCarrito;

    @Column(nullable = false)
    public Long idUsuario;

    @Column(nullable = false)
    public String correo;

    @Column(nullable = false)
    public Long precioTotal = 0L;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<LineaDeCarrito> lineas = new ArrayList<>();
}

