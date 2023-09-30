package backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import bdConnect.Conexion;

public class editar_huesped {
	
	Conexion conexion = new Conexion();
	Connection connection = null;
	Statement stm = null;
	ResultSet resultSet = null;
	
	public void editarHuesped(String id,String nombre, String apellido, String fechaN, String nacionalidad, String telefono, String numReserva) {
		if (nombre.isEmpty() || apellido.isEmpty() || fechaN.isEmpty() || nacionalidad.isEmpty() || telefono.isEmpty() || numReserva.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");
		} else {
			//Consulta
			String consultaString = "UPDATE huespedes SET nombre='"+nombre+"', apellido='"+apellido+"', fechaNacimiento='"+fechaN+"', nacionalidad='"+nacionalidad+"', telefono='"+telefono+"', numReserva='"+numReserva+"' WHERE idhuespedes="+id;
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
