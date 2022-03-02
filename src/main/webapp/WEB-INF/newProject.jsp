<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %> <!--Unicamente para update-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Create a Project</title>
		<!--BOOTSTRAP-->
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/css/styles.css">
	</head>
	<body>
		<div class="container">
			<h1>Crear Proyecto</h1>
			<form:form method="post" action="/projects" modelAttribute="proyecto">
				<form:errors class="d-block alert alert-danger" path="titulo"></form:errors>
				<form:errors class="d-block alert alert-danger" path="descripcion"></form:errors>
				<form:errors class="d-block alert alert-danger" path="fechaEntrega"></form:errors>
				<form:hidden path="creador" value="${id}"></form:hidden>
				<div class="form-group mb-3">
					<form:label path="titulo">Titulo del proyecto:</form:label>
					<form:input path="titulo" class="form-control"/>
				</div>
				<div class="form-group mb-3">
					<form:label path="descripcion">Descripción del proyecto:</form:label>
					<form:textarea path="descripcion" class="form-control" rows="5"/>
				</div>
				<div class="form-group mb-3">
					<form:label path="fechaEntrega">Fecha de entrega del proyecto:</form:label>
					<form:input path="fechaEntrega" class="form-control" type="date"/>
				</div>
				<div class="d-flex">
					<a href="/dashboard" class="btn btn-secondary">Cancelar</a>
					<button type="submit" class="btn btn-primary ms-3">Crear</button>
				</div>
			</form:form>
		</div>
		
		<!--BOOTSTRAP-->
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>