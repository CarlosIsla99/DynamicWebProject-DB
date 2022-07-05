<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="servlets.modelos.Coche"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

<h1 class="text-center my-3">Coche #${coche.id}</h1>
<table class="table table-dark table-hover text-center">
  <thead>
    <tr>
      <th scope="col">MATRÍCULA</th>
      <th scope="col">MARCA</th>
      <th scope="col">MODELO</th>
      <th scope="col">COLOR</th>
      <th scope="col">POTENCIA</th>
      <th scope="col">CILINDRADA</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>${coche.matricula}</td>
      <td>${coche.marca}</td>
      <td>${coche.modelo}</td>
      <td>${coche.color}</td>
      <td>${coche.potencia}</td>
      <td>${coche.cilindrada}</td>
    </tr>
  </tbody>
</table>
<div class="text-center">
	<a href="admin/coches" type="button" class="btn btn-primary text-center mt-3">Volver</a>
</div>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>