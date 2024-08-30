package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Usuario implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nombre;
	private String clave;
	private boolean isAdmin;
	
	private static List<Usuario> usuarios = null;
	
	public Usuario() {
		super();
	}
	
	public Usuario(int id, String nombre, String clave, boolean isAdmin) {
		this.id = id;
		this.nombre = nombre;
		this.clave = clave;
		this.isAdmin = isAdmin;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	//**** Metodos del Negocio *****///
	
	public static Usuario authenticate(String username, String password ) {
		Usuario usuarioAutorizado = null;
		
		List<Usuario> usuarios = getUsuario();
		
		for (Usuario usuario : usuarios) {
			if (usuario.getNombre().equals(username) && usuario.getClave().equals(password)) {
				usuarioAutorizado = usuario;
				break;
			}
		}
		return usuarioAutorizado;
	}
	
	public static List<Usuario> getUsuario(){
		if (usuarios == null) {
			usuarios = new ArrayList<Usuario>();
			usuarios.add(new Usuario(1, "Atik", "atik123", true));
			usuarios.add(new Usuario(2, "Juan", "juan123", false));
		}
		return usuarios;
	}
	
	public static Usuario getUsuarioById(int idUsuario) {
		
		for (Usuario usuario : getUsuario()) {
			if (usuario.getId() == idUsuario) {
				return usuario;
			}
		}
		
		return null;
	}
	
	public static Boolean create(Usuario usuario) {
		int max = 0;
		for (Usuario usuarioIterado : getUsuario()) {
			if (max < usuarioIterado.getId()) {
				max = usuarioIterado.getId();
			}
		}
		max += 1;
		usuario.setId(max);
		getUsuario().add(usuario);
		
		return true;
	}
	
	public static Boolean update(Usuario usuario) {
		List<Usuario> listaUsuarios = getUsuario();
		
		for (int i = 0; i < listaUsuarios.size(); i++) {
			if (listaUsuarios.get(i).getId() == usuario.getId()) {
				listaUsuarios.get(i).setClave(usuario.getClave());
				listaUsuarios.get(i).setNombre(usuario.getNombre());
				break;
			}
		}
		return true;
	}
	
	public static Boolean delete(int idUsuario) throws Exception {
		Usuario usuarioEncontrado = getUsuarioById(idUsuario);
		
		if (usuarioEncontrado.isAdmin) {
			throw new Exception("El administrador no puede ser eliminado ");
		}else {
			int indice = 0;
			
			List<Usuario> listaUsuario = getUsuario();
			
			for(int i = 0;i < listaUsuario.size(); i++) {
				if (listaUsuario.get(i).getId() == idUsuario) {
					indice = i;
					break;
				}
			}
			
			listaUsuario.remove(indice);
		}
		
		return true;
	}
	
}
