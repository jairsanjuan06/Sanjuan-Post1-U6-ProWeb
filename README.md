# Taller Unidad 6 - CRUD Productos MVC con JSP y Servlets

## Descripción

Aplicación web Java que implementa el patrón MVC usando Servlets como
controlador, JSP con JSTL y Expression Language como vista, y clases
Java como modelo. Se desarrolla un CRUD completo de productos con
Post/Redirect/Get.

## Objetivo

-   Implementar patrón MVC
-   CRUD completo de productos
-   Uso de JSP + JSTL + EL
-   Implementar PRG (Post/Redirect/Get)

## Tecnologías

-   Java JDK 11+
-   Apache Tomcat 9 o 10
-   JSP
-   JSTL
-   Servlets
-   Maven
-   HTML + CSS

## Estructura del Proyecto

    mvc-productos/
    ├── src/main/java/com/universidad/mvc/
    │   ├── model/
    │   │   ├── Producto.java
    │   │   └── ProductoDAO.java
    │   ├── service/
    │   │   └── ProductoService.java
    │   └── controller/
    │       └── ProductoServlet.java
    ├── src/main/webapp/
    │   ├── WEB-INF/views/
    │   │   ├── lista.jsp
    │   │   ├── formulario.jsp
    │   │   └── error.jsp
    │   └── css/
    │       └── estilos.css
    └── pom.xml

## Funcionalidades

-   Listar productos
-   Crear producto
-   Editar producto
-   Eliminar producto
-   Validaciones básicas
-   Patrón Post/Redirect/Get

## Flujo MVC

    Cliente -> Servlet -> Service -> DAO -> Modelo
                           |
                           v
                         JSP

## Ejecución

1.  Clonar repositorio

```{=html}
<!-- -->
```
    git clone <repo>

2.  Importar en IntelliJ o Eclipse como proyecto Maven

3.  Desplegar en Tomcat

4.  Abrir navegador:

```{=html}
<!-- -->
```
    http://localhost:8080/mvc-productos/productos

## Endpoints

    GET  /productos
    GET  /productos?accion=formulario
    GET  /productos?accion=editar&id={id}
    GET  /productos?accion=eliminar&id={id}
    POST /productos (guardar)
    POST /productos (actualizar)



## Validaciones

-   Nombre obligatorio
-   Precio mayor o igual a 0
-   Stock mayor o igual a 0

## Entregables

-   Repositorio GitHub
-   README.md
-   Código fuente
-   Carpeta capturas

## Capturas requeridas

-   Lista productos
-   Formulario crear
-   Formulario editar
-   Eliminación producto

## Commits mínimos

1.  Modelo y DAO
2.  Servicio y Servlet
3.  Vistas JSP

## Autor
Jair sanjuan
Ingeniería de Sistemas 
Programación Web Unidad 6
