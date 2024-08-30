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
	<div>
		<a href="GestionarUsuariosController?ruta=nuevo">Nuevo</a>
	</div>
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
					<th><a href="GestionarUsuariosController?ruta=actualizar&idPersona=${persona.id}">Actualizar</a> - 
					<a href="GestionarUsuariosController?ruta=eliminar&idPersona=${persona.id}">Eliminar</a></th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>