<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %> <!--Unicamente para update-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Project Manager</title>
		<!--BOOTSTRAP-->
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/css/styles.css">
	</head>
	<body>
		<div class="container">
			<header class="row text-center">
				<h1 class="text-primary">Project Manager</h1>
				<p>A placer for teams to manage projects</p>
			</header>
			
			<main class="row">
				<div class="col-8">
					<h2 class="text-primary">Register</h2>
					<form:form action="/register" method="post" modelAttribute="newUser">
						<form:errors path="nombre" class="alert alert-danger d-block"></form:errors>
						<form:errors path="apellido" class="alert alert-danger d-block"></form:errors>
						<form:errors path="email" class="alert alert-danger d-block"></form:errors>
						<form:errors path="password" class="alert alert-danger d-block"></form:errors>
						<form:errors path="confirm" class="alert alert-danger d-block"></form:errors>
						<div class="form-group mb-3">
							<form:label path="nombre">Nombre:</form:label>
							<form:input path="nombre" class="form-control" />
						</div>
						<div class="form-group mb-3">
							<form:label path="apellido">Apellido:</form:label>
							<form:input path="apellido" class="form-control" />
						</div>
						<div class="form-group mb-3">
							<form:label path="email">Email:</form:label>
							<form:input path="email" class="form-control" />
						</div>
						<div class="form-group mb-3">
							<form:label path="password">Password:</form:label>
							<form:input path="password" class="form-control" type="password"/>
						</div>
						<div class="form-group mb-3">
							<form:label path="confirm">Confirm Password:</form:label>
							<form:input path="confirm" class="form-control" type="password"/>
						</div>
						<button type="submit" class="btn btn-primary">Register</button>
					</form:form>

				</div>
				<div class="col-4">
					<h2 class="text-primary">Log in</h2>
					<form:form action="/login" method="post" modelAttribute="newLogin">
						<form:errors path="email" class="alert alert-danger d-block"></form:errors>
						<form:errors path="password" class="alert alert-danger d-block"></form:errors>
						<div class="form-group mb-3">
							<form:label path="email">Email:</form:label>
							<form:input path="email" class="form-control" />
						</div>
						<div class="form-group mb-3">
							<form:label path="password">Password:</form:label>
							<form:input path="password" class="form-control" type="password"/>
						</div>
						
						<button type="submit" class="btn btn-primary">Log in</button>
					</form:form>
				</div>
			</main>
		</div>
		
		<!--BOOTSTRAP-->
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>