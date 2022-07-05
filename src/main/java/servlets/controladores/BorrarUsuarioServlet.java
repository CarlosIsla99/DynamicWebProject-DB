package servlets.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/borrarUsuario")
public class BorrarUsuarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public BorrarUsuarioServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.parseLong(request.getParameter("id"));
		String email = request.getParameter("email");
		
		Globales.DAO_USUARIO.borrar(id);
		
		request.setAttribute("alertatexto", "El usuario " + email + " ha sido eliminado");
		request.setAttribute("alertanivel", "success");
		
		request.getRequestDispatcher("/admin/usuarios").forward(request, response);
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
