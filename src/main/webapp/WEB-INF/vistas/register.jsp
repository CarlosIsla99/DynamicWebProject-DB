<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="servlets.modelos.Coche"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

<h1 class="text-center my-3">Registrarse</h1>

<form class="text-center" autocomplete="off" method="POST" action="register">
  <div class="form-row row justify-content-center">
    <div class="form-group col-5">
      <label for="inputEmail4">Email</label>
      <input type="email" name="email" class="form-control" placeholder="Email" required="required">
      <span class="text-danger">${usuario.errores.email}</span>
    </div>
    <div class="form-group col-5">
      <label for="inputPassword4">Contraseña</label>
      <input type="password" name="contra1" class="form-control" placeholder="Contraseña" required="required">
      <span class="text-danger">${usuario.errores.password}</span>
    </div>
    <div class="form-group col-5">
      <label for="inputPassword4">Confirmar Contraseña</label>
      <input type="password" name="contra2" class="form-control" placeholder="Confirmar Contraseña" required="required">
	  <span class="text-danger">${error}</span>
    </div>
    <div class="form-group col-5">
    	<label for="inputAddress">Teléfono</label>
    	<input type="text" name="tel" class="form-control" placeholder="Teléfono" required="required">
    	<span class="text-danger">${usuario.errores.telefono}</span>
  	</div>
  </div>
  <div class="buton">
    <button type="submit" class="btn btn-primary mt-3">Enviar</button>
  </div>
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>