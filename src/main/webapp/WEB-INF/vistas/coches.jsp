<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="servlets.modelos.Coche"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

<h1 class="text-center mb-4">Todos los coches</h1>
<table class="table table-dark table-hover text-center">
  <thead>
    <tr>
    <c:if test="${sessionScope.usuario.rol == 'ADMIN'}">
    <th scope="col">ID</th>
    </c:if>
      <th scope="col">MATRÍCULA</th>
      <th scope="col">MARCA</th>
      <th scope="col">MODELO</th>
      <th scope="col">COLOR</th>
      <th scope="col">POTENCIA</th>
      <th scope="col">CILINDRADA</th>
      <c:choose>
      	<c:when test="${sessionScope.usuario.rol == 'ADMIN'}"><th scope="col">RESERVADO</th></c:when>
      	<c:otherwise><th scope="col">DISPONIBILIDAD</th></c:otherwise>
      </c:choose>
      <th scope="col">OPCIONES</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${coches}" var="coche">
    <tr>
    <c:if test="${sessionScope.usuario.rol == 'ADMIN'}">
      <td>${coche.id}</td>
    </c:if>
      <td>${coche.matricula}</td>
      <td>${coche.marca}</td>
      <td>${coche.modelo}</td>
      <td>${coche.color}</td>
      <td>${coche.potencia}</td>
      <td>${coche.cilindrada}</td>
      <td>
      <c:choose>
      	<c:when test="${sessionScope.usuario.rol == 'ADMIN'}">
      		<c:choose>
      			<c:when test="${coche.reserva == true}">SI</c:when>
      			<c:otherwise>NO</c:otherwise>
      		</c:choose>
      	</c:when>
      	<c:otherwise>
      		<c:choose>
      			<c:when test="${coche.reserva == true}"><i class="fa-solid fa-square-xmark fa-2x"></i></c:when>
      			<c:otherwise><i class="fa-solid fa-square-check fa-2x"></i></c:otherwise>
      		</c:choose>
      	</c:otherwise>
      </c:choose>
      </td>
		<td>
		<c:choose>
         <c:when test="${sessionScope.usuario.rol == 'ADMIN'}">
            <a href="admin/formulario?id=${coche.id}" type="button" class="btn btn-primary">Editar</a>
      		<a href="admin/borrar?id=${coche.id}" type="button" class="btn btn-danger">Borrar</a>
         </c:when>
         <c:otherwise>
           <a href="admin/coche?id=${coche.id}" type="button" class="btn btn-secondary">Ver</a>
           <a href="admin/reservaFormulario?email=${sessionScope.usuario.email}&idCoche=${coche.id}" type="button" class="btn btn-success">Reservar</a>
         </c:otherwise>
      	</c:choose>
      	</td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>