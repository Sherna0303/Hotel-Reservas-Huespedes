package backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bdConnect.Conexion;

public class EliminarReserva {
	
	Conexion conexion = new Conexion();
	Connection connection = null;
	Statement stm = null;
	ResultSet resultSet = null;
	
	public void elimnarReserva(String numReserva) {
		// Consulta
		String consultaString = "DELETE FROM reservas WHERE numReserva="+numReserva;
		
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
