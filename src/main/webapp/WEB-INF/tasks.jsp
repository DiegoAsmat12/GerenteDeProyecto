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
		<title>Tasks Page</title>
		<!--BOOTSTRAP-->
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/css/styles.css">
	</head>
	<body>
		<div class="container">
			<header class="row">
				<div class="col-9">
					<h1 class="text-primary">Proyecto: <c:out value="${proyecto.getTitulo()}"></c:out></h1>
					<p>Jefe de equipo: <c:out value="${proyecto.getCreador().getNombre()}"></c:out></p>
				</div>
				<div class="col-3">
					<a href="/dashboard" class="d-block btn btn-link text-end">Volver al dashboard</a>
				</div>
			</header>
			<main class="row">
				<form:form modelAttribute="newTask" method="post" action="/projects/${proyecto.getId()}/tasks">
					<form:errors class="d-block alert alert-danger" path="descripcion"></form:errors>
					<form:hidden path="creador" value="${id}"/>
					<form:hidden path="proyecto" value="${proyecto.getId()}"/>
					<div class="form-group mb-3">
						<form:label path="descripcion">Añadir una tarea:</form:label>
						<form:textarea path="descripcion" class="form-control" rows="3"/>
					</div>
					<button class="btn btn-primary">Agregar Tarea</button>
				</form:form>
				<c:forEach var="tarea" items="${tareas}">
					<h3>Agregado por: <c:out value="${tarea.getCreador().getNombre()}"></c:out> a las
					<fmt:formatDate value="${tarea.getCreatedAt()}" pattern="h:mm a MMM d"/></h3>
					<p><c:out value="${tarea.getDescripcion()}"></c:out></p>
				</c:forEach>
			</main>
		</div>
		
		<!--BOOTSTRAP-->
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>