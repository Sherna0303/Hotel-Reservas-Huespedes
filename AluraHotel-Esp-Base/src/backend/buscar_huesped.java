package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import bdConnect.Conexion;

public class buscar_huesped {
	Conexion conexion = new Conexion();
	Connection connection = null;
	Statement stm = null;
	ResultSet resultSet = null;
	
	public DefaultTableModel buscarHuesped(String dato) {
		
		String [] nombresColumnas = {"Número de Huesped", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Telefono", "Número de Reserva"};
		String [] huespedes = new String[7];
		DefaultTableModel modelo = new DefaultTableModel(null, nombresColumnas);

		// Consulta
		String consultaString = "SELECT * FROM huespedes WHERE nombre LIKE '%"+dato+"%' OR apellido LIKE '%"+dato+"%' OR nacionalidad LIKE '%"+dato+"%'";
		
		try {
			connection = conexion.conectar();
			stm = connection.createStatement();
			resultSet = stm.executeQuery(consultaString);
			
			while (resultSet.next()) {
				huespedes[0] = resultSet.getString(1);
				huespedes[1] = resultSet.getString(2);
				huespedes[2] = resultSet.getString(3);
				huespedes[3] = resultSet.getString(4);
				huespedes[4] = resultSet.getString(5);
				huespedes[5] = resultSet.getString(6);
				huespedes[6] = resultSet.getString(7);
				modelo.addRow(huespedes);
			}
			
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
		
		return modelo;
	}
}
