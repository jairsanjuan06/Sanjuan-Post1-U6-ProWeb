package com.universidad.mvc.controller;

import com.universidad.mvc.model.Producto;
import com.universidad.mvc.service.ProductoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "ProductoServlet", urlPatterns = {"/productos"})
public class ProductoServlet extends HttpServlet {

    private final ProductoService service = new ProductoService();

    /**
     * GET /productos?accion=...
     * Acciones: listar (default), formulario, editar, eliminar
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accion = req.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar"     -> listar(req, resp);
            case "formulario" -> mostrarFormulario(req, resp);
            case "editar"     -> mostrarEdicion(req, resp);
            case "eliminar"   -> eliminar(req, resp);
            default           -> resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * POST /productos?accion=...
     * Acciones: guardar, actualizar
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String accion = req.getParameter("accion");

        if ("guardar".equals(accion))       guardar(req, resp);
        else if ("actualizar".equals(accion)) actualizar(req, resp);
        else resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    // =========================================================
    // Métodos privados — uno por caso de uso
    // =========================================================

    /** Carga la lista y hace forward a lista.jsp */
    private void listar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("productos", service.obtenerTodos());
        // Pasar mensaje de éxito si viene en el parámetro (desde redirect PRG)
        String msg = req.getParameter("mensaje");
        if (msg != null) req.setAttribute("mensaje", msg);
        forward(req, resp, "/WEB-INF/views/lista.jsp");
    }

    /** Muestra formulario vacío para nuevo producto */
    private void mostrarFormulario(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        forward(req, resp, "/WEB-INF/views/formulario.jsp");
    }

    /** Carga el producto a editar y muestra el formulario precargado */
    private void mostrarEdicion(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Producto p = service.obtenerPorId(id);
        if (p == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto no encontrado");
            return;
        }
        req.setAttribute("producto", p);
        forward(req, resp, "/WEB-INF/views/formulario.jsp");
    }

    /** Guarda nuevo producto y redirige (PRG) */
    private void guardar(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Producto p = extraerProducto(req, 0);
        service.guardar(p);
        resp.sendRedirect(req.getContextPath()
                + "/productos?mensaje=Producto+guardado+exitosamente");
    }

    /** Actualiza producto existente y redirige (PRG) */
    private void actualizar(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Producto p = extraerProducto(req, id);
        service.actualizar(p);
        resp.sendRedirect(req.getContextPath()
                + "/productos?mensaje=Producto+actualizado+exitosamente");
    }

    /** Elimina producto y redirige (PRG) */
    private void eliminar(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        service.eliminar(id);
        resp.sendRedirect(req.getContextPath()
                + "/productos?mensaje=Producto+eliminado+exitosamente");
    }

    /**
     * Extrae y convierte los parámetros del formulario a un objeto Producto.
     * Centraliza el parseo para evitar repetición en guardar/actualizar.
     */
    private Producto extraerProducto(HttpServletRequest req, int id) {
        return new Producto(
                id,
                req.getParameter("nombre"),
                req.getParameter("categoria"),
                Double.parseDouble(req.getParameter("precio")),
                Integer.parseInt(req.getParameter("stock"))
        );
    }

    /** Helper para reducir repetición al hacer forward */
    private void forward(HttpServletRequest req, HttpServletResponse resp,
                         String path) throws ServletException, IOException {
        req.getRequestDispatcher(path).forward(req, resp);
    }
}