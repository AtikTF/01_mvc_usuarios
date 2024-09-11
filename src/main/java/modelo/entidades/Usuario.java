package modelo.entidades;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import modelo.conexion.BddConnection;

@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "nombre")
	private String nombre;
	
	@Column (name = "clave")
	private String clave;
	
	@Column (name = "isAdmin")
	private boolean isAdmin;
	
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nombre + " "  + this.clave;
	}
	
}
