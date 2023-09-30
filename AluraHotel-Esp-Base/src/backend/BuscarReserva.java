package backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import bdConnect.Conexion;

public class BuscarReserva {
	
	Conexion conexion = new Conexion();
	Connection connection = null;
	Statement stm = null;
	ResultSet resultSet = null;
	
	public DefaultTableModel buscarReserva(String dato) {
		
		String [] nombresColumnas = {"Numero de Reserva", "Fecha Check In", "Fecha Check Out", "Valor", "Forma de Pago"};
		String [] reserva = new String[5];
		DefaultTableModel modelo = new DefaultTableModel(null, nombresColumnas);

		// Consulta
		String consultaString = "SELECT * FROM reservas WHERE numReserva LIKE '%"+dato+"%' OR formaPago LIKE '%"+dato+"%'";
		
		try {
			connection = conexion.conectar();
			stm = connection.createStatement();
			resultSet = stm.executeQuery(consultaString);
			
			while (resultSet.next()) {
				reserva[0] = resultSet.getString(2);
				reserva[1] = resultSet.getString(3);
				reserva[2] = resultSet.getString(4);
				reserva[3] = resultSet.getString(5);
				reserva[4] = resultSet.getString(6);
				modelo.addRow(reserva);
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
