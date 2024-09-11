package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import modelo.conexion.BddConnection;
import modelo.entidades.Usuario;

public class UsuarioDAO {
	
	EntityManager em = null;
	
	public UsuarioDAO() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
		this.em = emf.createEntityManager();
	}
	
	//**** Metodos del Negocio *****///
	
		public Usuario authenticate(String username, String password ) {
			
			//-------ORM (JPA - EclipseLink) ---- 
			
			String sentenciaJPQL  = "Select u from Usuario u Where u.nombre= :usuario AND u.clave= :clave";
			Query consulta = em.createQuery(sentenciaJPQL);
			consulta.setParameter("usuario", "Luis");
			consulta.setParameter("clave", "luis123");
			
			return(Usuario) consulta.getSingleResult();
			
			//----------JDBC---------
			/*
			String _SQL_AUTORIZAR_ = "Select * from usuario where nombre =? and clave =?";
			
			try {
				PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(_SQL_AUTORIZAR_);
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				
				ResultSet rs = pstmt.executeQuery();
				
				Usuario usuarioAutorizado = null;
				
				while(rs.next()) {
					usuarioAutorizado = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("clave"), rs.getBoolean("isAdmin"));
					
				}
				
				BddConnection.cerrar(rs);
				BddConnection.cerrar(pstmt);
				BddConnection.cerrar();
				
				return usuarioAutorizado;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			/*
			
			//-------Datos Quemados
			
			/*List<Usuario> usuarios = getUsuario();
			
			for (Usuario usuario : usuarios) {
				if (usuario.getNombre().equals(username) && usuario.getClave().equals(password)) {
					usuarioAutorizado = usuario;
					break;
				}
			}
			return usuarioAutorizado;*/
		}
		
		public List<Usuario> getUsuario(){
			String _SQL_GET_ALL = "Select * from usuario";
			
			try {
				PreparedStatement pstmt =  BddConnection.getConexion().prepareStatement(_SQL_GET_ALL);
				ResultSet rs = pstmt.executeQuery();
				
				List<Usuario> usuarios = new ArrayList<Usuario>();
				
				while (rs.next()) {
					usuarios.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("clave"), rs.getBoolean("isAdmin")));	
				}
				
				BddConnection.cerrar(rs);
				BddConnection.cerrar(pstmt);
				BddConnection.cerrar();
				
				return usuarios;
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			/*if (usuarios == null) {
				usuarios = new ArrayList<Usuario>();
				usuarios.add(new Usuario(1, "Atik", "atik123", true));
				usuarios.add(new Usuario(2, "Juan", "juan123", false));
			}
			return usuarios;*/
		}
		
		public Usuario getUsuarioById(int idUsuario) {
			
			//-------Con ORM (JPA - EclipseLink)-------
			
			return this.em.find(Usuario.class, idUsuario);
			
			/*
			for (Usuario usuario : getUsuario()) {
				if (usuario.getId() == idUsuario) {
					return usuario;
				}
			}
			
			return null;
			*/
		}
		
		public Boolean create(Usuario usuario) {
			
			// ------Insertar con ORM (JPA y EclipseLink)------
			
			em.getTransaction().begin();
			try {
				em.persist(usuario);
				em.getTransaction().commit();
				return true;
			} catch (Exception e) {
				System.out.println("----Error: Creación de Usuario");
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				return false;
			}
			
			/*
			int max = 0;
			for (Usuario usuarioIterado : getUsuario()) {
				if (max < usuarioIterado.getId()) {
					max = usuarioIterado.getId();
				}
			}
			max += 1;
			usuario.setId(max);
			getUsuario().add(usuario);
			
			return true;*/
		}
		
		public Boolean update(Usuario usuario) {
			
			//----Con ORM (JPA - EclipseLink)-----
			
			em.getTransaction().begin();
			try {
				em.merge(usuario);
				em.getTransaction().commit();
				return true;
			} catch (Exception e) {
				System.out.println("----Error: Actualización de Usuario");
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				return false;
			}
			
			
			/*
			
			List<Usuario> listaUsuarios = getUsuario();
			
			for (int i = 0; i < listaUsuarios.size(); i++) {
				if (listaUsuarios.get(i).getId() == usuario.getId()) {
					listaUsuarios.get(i).setClave(usuario.getClave());
					listaUsuarios.get(i).setNombre(usuario.getNombre());
					break;
				}
			}
			return true;
			
			*/
		}
		
		public Boolean delete(int idUsuario) throws Exception {
			
			Usuario usuarioEliminar = this.getUsuarioById(idUsuario);
			
			
			em.getTransaction().begin();
			try {
				em.remove(usuarioEliminar);
				em.getTransaction().commit();
				return true;
			} catch (Exception e) {
				System.out.println("----Error al Eliminar el Usuario");
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				return false;
			}
			
			
			/*
			
			Usuario usuarioEncontrado = getUsuarioById(idUsuario);
			
			if (usuarioEncontrado.isAdmin()) {
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
			
			*/
		}
}
