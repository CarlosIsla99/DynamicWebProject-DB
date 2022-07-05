<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="servlets.modelos.Coche"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

<h1 class="text-center mb-4">Todos los usuarios</h1>
<table class="table table-dark table-hover text-center">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">EMAIL</th>
      <th scope="col">PASSWORD</th>
      <th scope="col">TELÉFONO</th>
      <th scope="col">ROL</th>
      <th scope="col">DAR DE BAJA</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${usuarios}" var="usuario">
    <tr>
      <td>${usuario.id}</td>
      <td>${usuario.email}</td>
      <td>${usuario.password}</td>
      <td>${usuario.telefono}</td>
      <td>${usuario.rol}</td>
	  <td>
      	<a href="admin/borrarUsuario?id=${usuario.id}&email=${usuario.email}" type="button" class="btn btn-danger">Suspender</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>