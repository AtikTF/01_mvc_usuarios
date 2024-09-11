package modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

public class BddConnection {
	
	private static Connection cnn = null;

	public BddConnection() {
		String servidor = "127.0.0.1";
		String database = "usuarios";
		String usuario = "root";
		String password = "root";
		
		String url= "jdbc:mysql://" + servidor + "/" + database;
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			cnn = DriverManager.getConnection(url, usuario, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConexion() {
		if (cnn == null) {
			new BddConnection();
		}
		return cnn;
	}
	
	public static void cerrar (ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs = null;
	}
	
	public static void cerrar (PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Cerrar la Conexion activa
	public static void cerrar() {
		if (cnn != null) {
			try {
				cnn.close();
				cnn = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
