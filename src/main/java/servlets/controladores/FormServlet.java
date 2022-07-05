package servlets.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlets.dal.DaoCoche;
import servlets.dal.DaoCocheMemoria;
import servlets.modelos.Coche;

import java.io.IOException;

@WebServlet("/admin/formulario")
public class FormServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final DaoCoche DaoCoche = DaoCocheMemoria.getInstancia();

    public FormServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String accion = "Añadir";
		
		if (id != null) {
			Coche coche = Globales.DAO_COCHE.obtenerPorId(Long.parseLong(id));
			accion = "Modificar";
			
			request.setAttribute("coche", coche);
		}

		request.setAttribute("accion", accion);
		request.getRequestDispatcher("/WEB-INF/vistas/formulario.jsp").forward(request, response);
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String matricula = request.getParameter("matricula");
		String marca = request.getParameter("marca");
		String modelo = request.getParameter("modelo");
		String color = request.getParameter("color");
		int potencia = Integer.parseInt(request.getParameter("potencia"));
		int cilindrada = Integer.parseInt(request.getParameter("cilindrada"));
		boolean reservado = false;
		
		String accion = "";
			
		Coche coche = new Coche(null, matricula, marca, modelo, color, potencia, cilindrada, reservado);
		
		if(coche.getErrores().size() > 0) {
			request.setAttribute("alertatexto", "No se ha podido añadir el coche. Datos inválidos.");
			request.setAttribute("alertanivel", "danger");
			
			request.setAttribute("coche", coche);
			request.getParameter("accion");
			request.getRequestDispatcher("/WEB-INF/vistas/formulario.jsp").forward(request, response);
			return;
		}
		
		try {
			if(id == null || id.trim().length() == 0) {
				
				if(Globales.DAO_COCHE.comprobarMatricula(matricula)) {
					request.setAttribute("alertatexto", "No se ha podido a�adir el coche. La matrícula ya existe.");
					request.setAttribute("alertanivel", "danger");
					
					request.setAttribute("coche", coche);
					request.getParameter("accion");
					request.getRequestDispatcher("/WEB-INF/vistas/formulario.jsp").forward(request, response);
					return;
				}
				
				Globales.DAO_COCHE.insertar(coche);
				accion = "añadido";
			} else {
				coche.setId(Long.parseLong(id));
				reservado = DaoCoche.obtenerReservaPorId(Long.parseLong(id));
				Globales.DAO_COCHE.modificar(coche);
				accion = "modificado";
			}
			
			request.setAttribute("alertatexto", "Se ha " + accion + " el registro correctamente");
			request.setAttribute("alertanivel", "success");
			
		} catch (Exception e) {
			request.setAttribute("alertatexto", "La opción de " + accion + " no ha funcionado");
			request.setAttribute("alertanivel", "danger");
		}
			
		request.getRequestDispatcher("/admin/coches").forward(request, response);
		
	}
	

}
