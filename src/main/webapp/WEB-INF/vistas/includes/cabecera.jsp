<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="servlets.modelos.Coche"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coches</title>
<base href="${pageContext.request.contextPath}/">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/e6a50891da.js" crossorigin="anonymous"></script>
</head>
<body>
<header>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark py-3">
  <a class="navbar-brand px-2" href="admin/coches">COCHES LOGO</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    <c:if test="${sessionScope.usuario.rol == 'ADMIN'}">
      <li class="nav-item active">
		<div class="text-center">
			<a class="nav-link mx-2" href="admin/coches">Administrar coches</a>
		</div>
	  </li>
      <li class="nav-item active">
		<div class="text-center">
			<a class="nav-link mx-2" href="admin/todasReservas">Ver todas las reservas</a>
		</div>
      </li>
      <li class="nav-item active">
		<div class="text-center">
			<a class="nav-link mx-2" href="admin/formulario">Añadir un coche</a>
		</div>
      </li>
      <li class="nav-item active">
		<div class="text-center">
			<a class="nav-link mx-2" href="admin/usuarios">Usuarios</a>
		</div>
      </li>
      </c:if>
    </ul>
  </div>
		<ul class="navbar-nav ml-3 mb-2 mb-lg-0">
			<c:choose>
				<c:when test="${sessionScope.usuario == null}">
					<div class="text-center">
						<a href="register" type="button" class="btn btn-outline-primary text-center px-3">Register</a>
						<a href="login" type="button" class="btn btn-outline-primary text-center px-3 mx-3">Login</a>
					</div>
				</c:when>
				<c:otherwise>
				<span class="navbar-text mx-3"> ${sessionScope.usuario.email} </span>
					<div class="d-flex flex-row">
						<c:if test="${sessionScope.usuario.rol == 'CLIENTE'}">
							<td>
      							<div class="text-center">
									<a href="admin/misReservas" type="button" class="btn btn-outline-primary">Mis Reservas</a>
								</div>
      						</td>
	  					</c:if>
						<a href="logout" type="button" class="btn btn-outline-danger text-center px-3 mx-4">Logout</a>
					</div>
				</c:otherwise>
			</c:choose>
		</ul>
</nav>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-6 mt-4">
			<c:if test="${alertatexto != null}">
				<div class="alert alert-${alertanivel} alert-dismissible fade show" role="alert"> ${alertatexto}
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
			</c:if>
		</div>
	</div>
</div>
</header>
<main>
