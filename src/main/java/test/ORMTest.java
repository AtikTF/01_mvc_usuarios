package test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import modelo.entidades.Usuario;

public class ORMTest {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
		EntityManager em = emf.createEntityManager();

		//insertar(em);
		
		//leer(em);
		
		//actualizar(em);
		
		//eliminar(em);
		
		//autenticar(em);
		
		//--------------Autenticacion Nativa----------------
		autenticarNativa(em);
	}

	private static void autenticarNativa(EntityManager em) {
		String sentenciaSQL  = "Select u.* from Usuario u Where nombre = 'Luis' AND clave='luis123'";
		
		Query consulta = em.createNativeQuery(sentenciaSQL, Usuario.class);
		
		Usuario usuarioAutenticado = (Usuario)consulta.getSingleResult();
		System.out.println(usuarioAutenticado);
	}

	private static void autenticar(EntityManager em) {
		String sentenciaJPQL  = "Select u from Usuario u Where u.nombre= :username AND u.clave= :password";
		Query consulta = em.createQuery(sentenciaJPQL);
		consulta.setParameter("username", "Luis");
		consulta.setParameter("password", "luis123");
		
		Usuario usuarioAutenticado = (Usuario)consulta.getSingleResult();
		System.out.println(usuarioAutenticado);
	}

	private static void eliminar(EntityManager em) {
		Usuario atik = em.find(Usuario.class, 1);
		
		
		em.getTransaction().begin();
		em.remove(atik);
		em.getTransaction().commit();
	}

	private static void actualizar(EntityManager em) {
		Usuario atik = em.find(Usuario.class, 1);
		atik.setClave("atik456");
		
		em.getTransaction().begin();
		em.merge(atik);
		em.getTransaction().commit();
	}

	private static void leer(EntityManager em) {
		Usuario atik = em.find(Usuario.class, 1);
		System.out.println(atik);
	}

	private static void insertar(EntityManager em) {
		// Insertar
		Usuario atik = new Usuario(0, "Luis", "luis123", true);

		em.getTransaction().begin();
		em.persist(atik);
		em.getTransaction().commit();

	}
}
