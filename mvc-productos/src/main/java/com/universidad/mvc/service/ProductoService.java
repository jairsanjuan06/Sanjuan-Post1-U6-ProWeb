package com.universidad.mvc.service;

import com.universidad.mvc.model.Producto;
import com.universidad.mvc.model.ProductoDAO;

import java.util.List;


public class ProductoService {

    private final ProductoDAO dao = new ProductoDAO();

    public List<Producto> obtenerTodos() {
        return dao.findAll();
    }

    public Producto obtenerPorId(int id) {
        return dao.findById(id);
    }

    /**
     * Valida y guarda un producto nuevo.
     * Lanza IllegalArgumentException si los datos son inválidos.
     */
    public void guardar(Producto p) {
        validar(p);
        dao.save(p);
    }

    /**
     * Valida y actualiza un producto existente.
     */
    public void actualizar(Producto p) {
        if (dao.findById(p.getId()) == null)
            throw new IllegalArgumentException("Producto no encontrado.");
        validar(p);
        dao.update(p);
    }

    public void eliminar(int id) {
        dao.delete(id);
    }

    // --- Validaciones de negocio reutilizables ---
    private void validar(Producto p) {
        if (p.getNombre() == null || p.getNombre().trim().isEmpty())
            throw new IllegalArgumentException("El nombre es obligatorio.");
        if (p.getPrecio() < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        if (p.getStock() < 0)
            throw new IllegalArgumentException("El stock no puede ser negativo.");
    }
}