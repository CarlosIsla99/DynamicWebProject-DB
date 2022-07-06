package servlets.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/misReservas")
public class ReservasServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public ReservasServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("reservas", Globales.DAO_RESERVA.obtenerTodos());
		request.setAttribute("coches", Globales.DAO_COCHE.obtenerTodos());

		request.getRequestDispatcher("/WEB-INF/vistas/reservas.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
