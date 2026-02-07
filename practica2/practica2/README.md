# Práctica 2 - API REST Carrito

Este proyecto implementa un servicio web REST mínimo para realizar operaciones CRUD sobre un recurso "Carrito", modelando un caso simplificado de e-commerce donde cada carrito solo puede contener un único producto.

## Alcance Funcional
El recurso **Carrito** consta de las siguientes propiedades:
- **idCarrito**: Identificador único del carrito.
- **idArticulo**: Identificador del artículo asociado.
- **descripcion**: Texto descriptivo del artículo.
- **unidades**: Número de unidades (mínimo 1).
- **precioFinal**: Importe final del carrito.

## Tabla de Endpoints

| Método | Ruta | Descripción | Cuerpo (JSON) | Éxito | Error |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **POST** | `/api/carrito` | Crea un nuevo carrito. | `ModeloCarrito` | 201 Created | 400 Bad Request |
| **GET** | `/api/carrito` | Lista todos los carritos almacenados. | Vacío | 200 OK | - |
| **GET** | `/api/carrito/{id}` | Obtiene los detalles de un carrito por ID. | Vacío | 200 OK | 404 Not Found |
| **PUT** | `/api/carrito/{id}` | Actualiza un carrito existente. | `ModeloCarrito` | 200 OK | 400 / 404 |
| **DELETE** | `/api/carrito/{id}` | Elimina un carrito del sistema. | Vacío | 204 No Content | 404 Not Found |

## Gestión de Errores
- **400 Bad Request**: Se devuelve cuando las validaciones de los campos (como `@NotBlank` o `@Min`) no se cumplen.
- **404 Not Found**: Se devuelve cuando se intenta acceder, modificar o eliminar un recurso con un ID que no existe en el sistema.

