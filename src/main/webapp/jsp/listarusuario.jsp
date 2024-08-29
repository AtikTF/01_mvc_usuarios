<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar Usuarios</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Contraseña</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${personas}" var="persona">
				<tr>
					<th>${persona.id}</th>
					<th>${persona.nombre}</th>
					<th>${persona.clave}</th>
					<th><a href="">Actualizar</a> - <a href="">Eliminar</a></th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a href="../GestionarUsuariosControler?ruta=nuevo">Nuevo</a>
	</div>
</body>
</html>