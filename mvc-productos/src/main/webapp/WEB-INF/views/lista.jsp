<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inventario de Productos</title>
    <link rel="stylesheet" href="<c:url value='/css/estilos.css'/>">
</head>
<body>

<h1>📦 Inventario de Productos</h1>

<%-- Mensaje de éxito tras operación POST/Redirect/GET --%>
<c:if test="${not empty mensaje}">
    <p class="alert-success">✅ ${mensaje}</p>
</c:if>

<a href="<c:url value='/productos?accion=formulario'/>"
   class="btn-nuevo">+ Nuevo Producto</a>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Categoría</th>
        <th>Precio</th>
        <th>Stock</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${empty productos}">
            <tr><td colspan="6">No hay productos registrados.</td></tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="p" items="${productos}" varStatus="s">
                <tr class="${s.index % 2 == 0 ? 'par' : 'impar'}">
                    <td>${p.id}</td>
                    <td><c:out value="${p.nombre}"/></td>
                    <td><c:out value="${p.categoria}"/></td>
                    <td>
                        <c:set var="precioFmt" value="${p.precio}" />
                        <fmt:formatNumber value="${precioFmt}"
                                          type="currency"
                                          currencySymbol="$"
                                          minFractionDigits="2"
                                          maxFractionDigits="2"/>
                    </td>
                    <td>${p.stock}</td>
                    <td>
                        <a href="<c:url value='/productos?accion=editar&id=${p.id}'/>">
                            ✏️ Editar
                        </a> |
                        <a href="<c:url value='/productos?accion=eliminar&id=${p.id}'/>"
                           onclick="return confirm('¿Eliminar ${p.nombre}?')">
                            🗑 Eliminar
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

</body>
</html>