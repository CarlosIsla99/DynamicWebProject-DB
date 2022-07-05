<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="servlets.modelos.Coche"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

<h1 class="text-center my-3">${accion} reserva</h1>

<form class="text-center" autocomplete="off" method="POST" action="admin/reservaFormulario?admin=${sessionScope.usuario.rol}">
  <div class="form-row row justify-content-center">
  
    <div class="col-5 mt-2">
    <label>Nombre</label>
      <input type="text" name="nombre" value="${reserva.nombre}" class="form-control" required="required" placeholder="Nombre">
      <span class="text-danger">${reserva.errores.nombre}</span>
    </div>
    
	<div class="col-5 mt-2">
	<label>Email</label>
 		<input type="email" name="email" value="${sessionScope.usuario.email}" class="form-control" id="exampleInputEmail" readonly>
 		<span class="text-danger">${reserva.errores.email}</span>
	</div>
	
	<div class="col-5 mt-2">
	<label>Coche</label>
		<select class="form-select" name="cocheId" aria-label="Default select example">
		<c:forEach items="${coches}" var="coche">
			<c:choose>
         		<c:when test="${coche.id == idCoche}">
					<option value="${coche.id}" selected>${coche.marca} ${coche.modelo}</option>
				</c:when>
				<c:otherwise>
	  				<option value="${coche.id}">${coche.marca} ${coche.modelo}</option>
	  			</c:otherwise>
	  		</c:choose>
  		</c:forEach>
		</select>
	</div>

    <div class="col-5 mt-2">
    <label>Fecha y hora</label>
      <input type="datetime-local" name="datetime" value="${reserva.fechaHora}" class="form-control" required="required">
      <span class="text-danger">${reserva.errores.fechaHora}</span>
    </div>
    
    <div class="col-5 mt-2">
    <label>Nº de personas</label>
      <input type="number" name="numPersonas" min="1" max="5" value="${reserva.numeroPersonas}" class="form-control" required="required" placeholder="Número de personas">
      <span class="text-danger">${reserva.errores.numeroPersonas}</span>
    </div>
    
    <div class="col-5 mt-2">
    <label>Comentario</label>
      <textarea name="comentario" class="form-control" maxlength="75" placeholder="Comentario">${reserva.comentario}</textarea>
      <span class="text-danger">${reserva.errores.comentario}</span>
    </div>
    
  </div>
  <button type="submit" name="id" value="${reserva.id}" class="btn btn-primary mt-3">Enviar</button>
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>