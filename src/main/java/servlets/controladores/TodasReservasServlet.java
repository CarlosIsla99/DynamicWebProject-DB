package servlets.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlets.dal.DaoCoche;
import servlets.dal.DaoCocheMemoria;
import servlets.dal.DaoReserva;
import servlets.dal.DaoReservaMemoria;
import servlets.dal.DaoUsuario;
import servlets.dal.DaoUsuarioMemoria;

import java.io.IOException;

@WebServlet("/admin/todasReservas")
public class TodasReservasServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final DaoReserva DaoReserva = DaoReservaMemoria.getInstancia();
	private static final DaoCoche DaoCoche = DaoCocheMemoria.getInstancia();
	private static final DaoUsuario DaoUsuario = DaoUsuarioMemoria.getInstancia();

    public TodasReservasServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("reservas", DaoReserva.obtenerTodos());
		request.setAttribute("coches", DaoCoche.obtenerTodos());
		request.setAttribute("usuarios", DaoUsuario.obtenerTodos());
		request.getRequestDispatcher("/WEB-INF/vistas/todasReservas.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
