package controlador;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.dao.UsuarioDAO;
import modelo.entidades.Usuario;

@WebServlet("/GestionarUsuariosController")
public class GestionarUsuariosController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.ruteador(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.ruteador(req, resp);
	}

	private void ruteador(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String ruta = (req.getParameter("ruta") == null)? "listar": req.getParameter("ruta");

		switch (ruta) {
		case "nuevo":
			this.nuevo(req, resp);
			break;
		case "listar":
			this.listar(req, resp);
			break;
		case "guardarNuevo":
			this.guardarNuevo(req, resp);
			break;
		case "guardarExistente":
			this.guardarExistente(req, resp);
			break;
		case "actualizar":
			this.actualizar(req, resp);
			break;
		case "eliminar":
			this.eliminar(req, resp);
			break;
		}
	}

	private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. Obtener Parametros
		
		// 2. Hablar con el modelo
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> personas = usuarioDAO.getUsuario();
		
		// 3. Llamar a la vista
		req.setAttribute("personas", personas);
		req.getRequestDispatcher("jsp/listarusuario.jsp").forward(req, resp);
	}

	private void nuevo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. Obtener Parametros
		
		// 2. Hablar con el modelo
		
		// 3. Llamar a la vista
		resp.sendRedirect("jsp/crearusuario.jsp");
	}

	private void guardarNuevo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. Obtener Parametros
		String nombre = req.getParameter("txtNombre");
		String clave = req.getParameter("txtClave");
		
		Usuario usuario = new Usuario(0, nombre,clave, false);
		
		// 2. Hablar con el modelo
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		boolean resultado = usuarioDAO.create(usuario);
		
		// 3. Llamar a la vista
		if (resultado == true) {
			resp.sendRedirect("GestionarUsuariosController?ruta=listar");
		}else {
			req.setAttribute("mensaje", "No se pudo guardar el usuario nuevo");
			req.getRequestDispatcher("jsp/error.jsp").forward(req, resp);
		}
	}

	private void actualizar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. Obtener Parametros
		int idPersona = Integer.parseInt(req.getParameter("idPersona"));
		
		// 2. Hablar con el modelo
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario persona = usuarioDAO.getUsuarioById(idPersona);
		
		// 3. Llamar a la vista
		req.setAttribute("persona", persona);
		req.getRequestDispatcher("jsp/actualizarusuario.jsp").forward(req, resp);
	}

	private void guardarExistente(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 1. Obtener Parametros
		int id = Integer.parseInt(req.getParameter("txtId"));
		String nombre = req.getParameter("txtNombre");
		String clave = req.getParameter("txtClave");
		
		Usuario usuario = new Usuario(id, nombre, clave, false);
		// 2. Hablar con el modelo
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		boolean respuesta = usuarioDAO.update(usuario);
		
		// 3. Llamar a la vista
		if ( respuesta == true) {
			resp.sendRedirect("GestionarUsuariosController?ruta=listar");
		}else {
			req.setAttribute("mensaje", "Error al actualizar el usuario");
			req.getRequestDispatcher("jsp/error.jpp").forward(req, resp);
		}
		
	}

	private void eliminar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. Obtener Parametros
		int idPersona = Integer.parseInt(req.getParameter("idPersona"));
	
		try {
			// 2. Hablar con el modelo
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.delete(idPersona);
			
			// 3. Llamar a la vista
			resp.sendRedirect("GestionarUsuariosController?ruta=listar");
			
		} catch (Exception e) {
			req.setAttribute("mensaje", e.getMessage());
			req.getRequestDispatcher("jsp/error.jsp").forward(req, resp);
		}		
		
	}
}
