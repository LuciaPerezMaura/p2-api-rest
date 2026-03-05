package edu.comillas.icai.gitt.pat.spring.practica2;

import jakarta.persistence.*;

@Entity
public class LineaDeCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idLinea;

    @Column(nullable = false)
    public Long idArticulo;

    @Column(nullable = false)
    public Long precioUnitario;

    @Column(nullable = false)
    public Long nUnidades;

    @Column(nullable = false)
    public Long coste;

    @ManyToOne
    @JoinColumn(name = "idCarrito", nullable = false)
    public Carrito carrito;
}
