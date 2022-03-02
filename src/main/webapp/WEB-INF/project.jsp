<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %> <!--Unicamente para update-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %> <!-- Date formatter -->
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Project Details</title>
		<!--BOOTSTRAP-->
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/css/styles.css">
	</head>
	<body>
		<div class="container">
			<header class="row">
				<div class="col-9">
					<h1 class="text-primary">Detalles del Proyecto</h1>
				</div>
				<div class="col-3">
					<a href="/dashboard" class="d-block btn btn-link text-end">Volver al dashboard</a>
				</div>
			</header>
			<main class="row">
				<p>Proyecto: <c:out value="${proyecto.getTitulo()}"></c:out></p>
				<p>Descripcion: <c:out value="${proyecto.getDescripcion()}"></c:out></p>
				<p>Fecha de cierre: <fmt:formatDate value="${proyecto.getFechaEntrega()}" pattern="dd/MM/yyyy"/></p>
			
				<div class="d-flex">
					<c:if test="${addTask}">
						<a class="btn btn-primary me-3" href="/projects/${proyecto.getId()}/tasks">Ver Tareas</a>
					</c:if>
					<c:if test="${id == proyecto.getCreador().getId()}">
						<form:form method="post" action="/projects/${proyecto.getId()}">
							<input type="hidden" name="_method" value="delete">
							<button class="btn btn-danger">Borrar</button>
						</form:form>
					</c:if>
				</div>
			</main>
		</div>
		
		<!--BOOTSTRAP-->
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>