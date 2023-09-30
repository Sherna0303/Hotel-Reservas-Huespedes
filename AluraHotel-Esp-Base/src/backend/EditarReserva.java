package backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.swing.JOptionPane;

import bdConnect.Conexion;

public class EditarReserva {
	
	Conexion conexion = new Conexion();
	Connection connection = null;
	Statement stm = null;
	ResultSet resultSet = null;
	
	public void editarReserv(String numReserva, String fechaEntrada, String fechaSalida, String costo,String formaPago) {
		if (numReserva.isEmpty() || fechaEntrada.isEmpty()|| fechaSalida.isEmpty() || costo.isEmpty() || formaPago.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");
		} else {
			// Consulta
			String consultaString = "UPDATE reservas SET numReserva='"+numReserva+"', fechaEntrada='"+fechaEntrada+"', fechaSalida='"+fechaSalida+"', costo='"+costo+"', formaPago='"+formaPago+"' WHERE numReserva="+numReserva;
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

}
