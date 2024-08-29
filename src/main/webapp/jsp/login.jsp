<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form method="POST" action="../AutenticarController?ruta=ingresar">
		<fieldset>
			<legend>Login</legend>
			
			<label>User: </label><br>
			<input type="text" name="usuario" />
			<br><br>
			
			<label>Password: </label><br>
			<input type="password" name="clave" />
			
			<br><br>
			<input type="submit" value="Ingresar"/>
			
		</fieldset>
	</form>
</body>
</html>