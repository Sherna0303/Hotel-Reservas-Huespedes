package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bdConnect.Conexion;
import views.Exito;

public class registrar_huesped {
	Conexion conexion = new Conexion();
	Connection connection = null;
	PreparedStatement ps = null;
	
	public void registrarHuesped(String nombre, String apellido, String fechaN, String nacionalidad, String telefono, String numReserva) {
		try {
			// Concetamos con la base de datos
			connection = conexion.conectar();
			
			// Consulta
			String consultaString = "INSERT INTO huespedes (nombre, apellido, fechaNacimiento, nacionalidad, telefono, numReserva)VALUES(?,?,?,?,?,?)";
			
			// Agregamos los datos de la consulta
			ps = connection.prepareStatement(consultaString);
			ps.setString(1, nombre);
			ps.setString(2, apellido);
			ps.setString(3, fechaN);
			ps.setString(4, nacionalidad);
			ps.setString(5, telefono);
			ps.setString(6, numReserva);
			
			// Ejecutamos
			ps.execute();
			
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
