package bdConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class test_Conexion {
	public static void main(String[] args) {
		Conexion conexion = new Conexion();
		Connection connection = null;
		Statement stm = null;
		ResultSet resultSet = null;
		
		
		try {
			connection = conexion.conectar();
			stm = connection.createStatement();
			resultSet = stm.executeQuery("SELECT * FROM usuario");
			
			while (resultSet.next()) {
				int idUsuario = resultSet.getInt(1);
				String usuario = resultSet.getString(2);
				String clave = resultSet.getString(3);
				
				System.out.println(idUsuario + " - " + usuario + " - " + clave);
				
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
		
	}
}
