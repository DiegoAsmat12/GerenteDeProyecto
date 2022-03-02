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
		<title>Project Manager Dashboard</title>
		<!--BOOTSTRAP-->
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/css/styles.css">
	</head>
	<body>
		<div class="container">
			<header class="row">
				<div class="col-9">
					<h1 class="text-primary">Bienvenido, <c:out value="${usuario.getNombre()}"></c:out></h1>
				</div>
				<div class="col-3">
					<a href="/logout" class="d-block btn btn-link text-end">Log out</a>
				</div>
			</header>
			<main class="row">
				<h3 class="col-9">Todos los proyectos:</h3>
				<a href="/projects/new" class="col-3 btn btn-link text-end">Nuevo Proyecto</a>
				<div class="table-container col-12">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Proyectos</th>
								<th scope="col">Jefe del equipo</th>
								<th scope="col">Fecha de cierre</th>
								<th scope="col">Acciones</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="proyecto" items="${proyectosSinParticipar}">
								<tr>
									<td><a href="/projects/${proyecto.getId()}"><c:out value="${proyecto.getTitulo()}"></c:out></a></td>
									<td><c:out value="${proyecto.getCreador().getNombre()}"></c:out></td>
									<td><fmt:formatDate value="${proyecto.getFechaEntrega()}" pattern="MMM dd"/></td>
									<td>
										<form:form action="/projects/join" method="post">
											<input type="hidden" name="proyecto_id" value="${proyecto.getId()}">
											<button type="submit" class="btn btn-link">Unirse</button>
										</form:form>
									</td>
								</tr>
							</c:forEach>						
						</tbody>
					</table>
				</div>
				<div class="table-container col-12">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Proyectos</th>
								<th scope="col">Jefe del equipo</th>
								<th scope="col">Fecha de cierre</th>
								<th scope="col">Acciones</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="proyecto" items="${usuario.getProyectos()}">
								<tr>
									<td><a href="/projects/${proyecto.getId()}"><c:out value="${proyecto.getTitulo()}"></c:out></a></td>
									<td><c:out value="${proyecto.getCreador().getNombre()}"></c:out></td>
									<td><fmt:formatDate value="${proyecto.getFechaEntrega()}" pattern="MMM dd"/></td>
									<td>
										<c:choose>
											<c:when test="${id == proyecto.getCreador().getId()}">
												<a href="/projects/edit/${proyecto.getId()}" class="btn btn-link">Editar</a>
											</c:when>
											<c:otherwise>
												<form:form action="/projects/leave" method="post">
													<input type="hidden" name="proyecto_id" value="${proyecto.getId()}">
													<button type="submit" class="btn btn-link">Dejar</button>
												</form:form>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>						
						</tbody>
					</table>
				</div>
			</main>
		</div>
		
		<!--BOOTSTRAP-->
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>