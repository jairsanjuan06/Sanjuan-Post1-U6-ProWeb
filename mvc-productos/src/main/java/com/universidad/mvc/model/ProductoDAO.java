package com.universidad.mvc.model;

import java.util.*;

public class ProductoDAO {

    // static: lista compartida entre todas las instancias del DAO
    private static final List<Producto> lista = new ArrayList<>();
    private static int contador = 3;

    // Bloque estático: se ejecuta una sola vez al cargar la clase
    static {
        lista.add(new Producto(1, "Laptop Pro 15",   "Computadoras", 1299.99, 10));
        lista.add(new Producto(2, "Monitor 27 4K",   "Monitores",     549.00, 25));
        lista.add(new Producto(3, "Teclado Mecánico","Periféricos",    89.99, 50));
    }

    /** Retorna lista inmutable para evitar modificaciones externas */
    public List<Producto> findAll() {
        return Collections.unmodifiableList(lista);
    }

    /** Busca por ID usando Stream API */
    public Producto findById(int id) {
        return lista.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /** Asigna ID autoincremental y agrega a la lista */
    public void save(Producto p) {
        p.setId(++contador);
        lista.add(p);
    }

    /** Reemplaza el producto existente con el mismo ID */
    public void update(Producto p) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == p.getId()) {
                lista.set(i, p);
                return;
            }
        }
    }

    /** Elimina el producto con el ID dado */
    public void delete(int id) {
        lista.removeIf(p -> p.getId() == id);
    }
}