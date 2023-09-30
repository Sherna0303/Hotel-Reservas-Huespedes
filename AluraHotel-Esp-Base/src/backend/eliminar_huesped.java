package backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import bdConnect.Conexion;

public class eliminar_huesped {
	
	Conexion conexion = new Conexion();
	Connection connection = null;
	Statement stm = null;
	ResultSet resultSet = null;
	
	public void eliminarHuesped(String id) {
		// Consulta
		String consultaString = "DELETE FROM huespedes WHERE idhuespedes="+id;
		try {
			connection = conexion.conectar();
			stm = connection.createStatement();
			stm.executeUpdate(consultaString);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet!= null) {
					resultSet.close();
				}
				
				if (stm != null) {
					stm.close();
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
