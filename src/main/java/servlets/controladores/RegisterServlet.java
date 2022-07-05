package servlets.controladores;

import java.io.IOException;

import servlets.modelos.Usuario;
import servlets.modelos.Usuario.Roles;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1587748451045724579L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/vistas/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String contra1 = request.getParameter("contra1");
		String contra2 = request.getParameter("contra2");
		String tel = request.getParameter("tel");
		
		Usuario usuario = new Usuario(null, email, contra1, tel, Roles.CLIENTE);
		
		if(usuario.getErrores().size() > 0 || (contra1.equalsIgnoreCase(contra2)) == false) {
			request.setAttribute("alertatexto", "No se ha podido registrar. Revise los datos.");
			request.setAttribute("alertanivel", "danger");			
			request.setAttribute("usuario", usuario);
			
			request.setAttribute("error", "Las contrase�as no coinciden");
			
			request.getRequestDispatcher("/WEB-INF/vistas/register.jsp").forward(request, response);
			return;
		}
		
		if(Globales.DAO_USUARIO.obtenerPorEmail(email) != null) {
			request.setAttribute("alertatexto", "El email ya está registrado.");
			request.setAttribute("alertanivel", "danger");			
			request.setAttribute("usuario", usuario);
			
			request.getRequestDispatcher("/WEB-INF/vistas/register.jsp").forward(request, response);
			return;
		}
		
		Globales.DAO_USUARIO.insertar(usuario);
		request.setAttribute("alertatexto", "Se ha registrado su usuario.");
		request.setAttribute("alertanivel", "success");
		request.getRequestDispatcher("/WEB-INF/vistas//login.jsp").forward(request, response);

	}

}
