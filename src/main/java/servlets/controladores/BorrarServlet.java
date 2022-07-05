package servlets.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/borrar")
public class BorrarServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public BorrarServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.parseLong(request.getParameter("id"));
		
		Globales.DAO_COCHE.borrar(id);
		
		request.setAttribute("alertatexto", "Se ha borrado el registro #" + id + " correctamente");
		request.setAttribute("alertanivel", "success");
		
		request.getRequestDispatcher("/admin/coches").forward(request, response);
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
