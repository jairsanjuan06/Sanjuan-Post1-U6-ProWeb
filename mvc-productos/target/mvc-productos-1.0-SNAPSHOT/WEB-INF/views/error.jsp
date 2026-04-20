<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
<h1>⚠️ Ha ocurrido un error</h1>
<p>${pageContext.exception.message}</p>
<a href="${pageContext.request.contextPath}/productos">← Volver al listado</a>
</body>
</html>