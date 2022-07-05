<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="servlets.modelos.Coche"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

<h1 class="text-center mb-5">Todas las reservas</h1>
<table class="table table-dark table-hover text-center">
  <thead>
    <tr>
      <th scope="col">NOMBRE</th>
      <th scope="col">EMAIL</th>
      <th scope="col">COCHE</th>
      <th scope="col">FECHA Y HORA</th>
      <th scope="col">Nº DE PERSONAS</th>
      <th scope="col">COMENTARIO</th>
      <th scope="col">OPCIONES</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${reservas}" var="reserva">
    <tr>
      <td>${reserva.nombre}</td>
      <td>${reserva.email}</td>
      <c:forEach items="${coches}" var="coche">
      	<c:if test="${coche.id == reserva.idCoche}">
      		<td>${coche.marca} ${coche.modelo}</td>
      	</c:if>
      </c:forEach>
      <td>${reserva.fechaHora}</td>
      <td>${reserva.numeroPersonas}</td>
      <td>${reserva.comentario}</td>
	  <td>
         <a href="admin/borrarReserva?id=${reserva.id}&admin=${sessionScope.usuario.rol}" type="button" class="btn btn-danger">Cancelar</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>