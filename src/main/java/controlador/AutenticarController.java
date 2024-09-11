package controlador;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.dao.UsuarioDAO;
import modelo.entidades.Usuario;

@WebServlet("/AutenticarController")
public class AutenticarController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.ruteador(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.ruteador(req, resp);
	}
	
	private void ruteador(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		
		String ruta = (req.getParameter("ruta") == null)?"iniciar": req.getParameter("ruta");
		
		switch (ruta) {
		
		case "ingresar": 
			this.ingresar(req, resp);
			break;
		case "iniciar":
			this.iniciar(req, resp);
			break;
		}
	}

	private void iniciar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. Obtener Parametros
		// 2. Hablar con el modelo
		// 3. Llamar a la vista
		resp.sendRedirect("jsp/login.jsp");
	}

	private void ingresar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. Obtener Parametros
		String usuario = req.getParameter("usuario");
		String clave = req.getParameter("clave");
		
		// 2. Hablar con el modelo
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario resultado  = usuarioDAO.authenticate(usuario, clave);
		
		// 3. Llamar a la vista
		if (resultado == null) {
			resp.sendRedirect("jsp/login.jsp");
		}else {
			//Creo una sesion
			HttpSession sesionSitio = req.getSession();
			sesionSitio.setAttribute("autorizado", resultado);
			
			//Permito el paso 
			resp.sendRedirect("GestionarUsuariosController?ruta=listar");
		}
	}
}
