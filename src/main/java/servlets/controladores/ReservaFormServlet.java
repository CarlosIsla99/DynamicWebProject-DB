package servlets.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlets.modelos.Reserva;
import servlets.modelos.Usuario;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/admin/reservaFormulario")
public class ReservaFormServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public ReservaFormServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		Long idCoche = Long.parseLong(request.getParameter("idCoche"));
		String accion = "Realizar";
		
		if (id != null) {
			Reserva reserva = Globales.DAO_RESERVA.obtenerPorId(Long.parseLong(id));
			accion = "Modificar";
			
			request.setAttribute("reserva", reserva);
		} else if(Globales.DAO_COCHE.obtenerReservaPorId(idCoche) == true) {
			request.setAttribute("alertatexto", "No se ha podido realizar la reserva. El coche ya está reservado.");
			request.setAttribute("alertanivel", "danger");
			request.getRequestDispatcher("/admin/coches").forward(request, response);
		}

		request.setAttribute("accion", accion);
		request.setAttribute("idCoche", idCoche);
		request.setAttribute("coches", Globales.DAO_COCHE.obtenerTodos());
		request.getRequestDispatcher("/WEB-INF/vistas/reservaFormulario.jsp").forward(request, response);
		
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		Long idCoche = Long.parseLong(request.getParameter("cocheId"));
		String nombre = request.getParameter("nombre");
		
		String email = request.getParameter("email");
		Usuario usuario = Globales.DAO_USUARIO.obtenerPorEmail(email);
		
		LocalDateTime datetime = LocalDateTime.parse(request.getParameter("datetime"));
		int numPersonas = Integer.parseInt(request.getParameter("numPersonas"));
		String comentario = request.getParameter("comentario");
		
		if(comentario.length() == 0 || comentario == null) {
			comentario = "'Sin comentario'";
		}
		
		String accion = "";
		
		Reserva reserva = new Reserva(null, nombre, datetime, numPersonas, comentario, usuario.getId(), idCoche);
		
		if(reserva.getErrores().size() > 0) {
			request.setAttribute("alertatexto", "No se ha podido realizar la reserva. Revise los datos.");
			request.setAttribute("alertanivel", "danger");
			
			request.setAttribute("reserva", reserva);
			
			request.getRequestDispatcher("/WEB-INF/vistas/reservaFormulario.jsp").forward(request, response);
			return;
		}

		try {
			if(id == null || id.trim().length() == 0) {
				Globales.DAO_RESERVA.insertar(reserva);
				Globales.DAO_COCHE.setTrueReserva(idCoche);
				accion = "realizada";
			} else {
				reserva.setId(Long.parseLong(id));
				Globales.DAO_RESERVA.modificar(reserva);
				accion = "modificada";
			}
			
			request.setAttribute("alertatexto", "Reserva " + accion + " corréctamente");
			request.setAttribute("alertanivel", "success");
			
		} catch (Exception e) {
			request.setAttribute("alertatexto", "Ha ocurrido un error a la hora de reservar");
			request.setAttribute("alertanivel", "danger");
		}
			
		request.getRequestDispatcher("/admin/misReservas").forward(request, response);
		
		
		
	}

}
