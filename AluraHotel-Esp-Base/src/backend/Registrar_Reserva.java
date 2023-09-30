    package backend;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	import bdConnect.Conexion;

	public class Registrar_Reserva {

	    private Conexion conexion = new Conexion();
	    private Connection connection = null;
	    private PreparedStatement ps = null;
	    
	    

	    public void registrarEstadia(String numReserva, String fechaEntrada, String fechaSalida, String costo,String formaPago) {
	        try {
	            
	            connection = conexion.conectar();

	            
	            String consultaString = "INSERT INTO reservas (numReserva, fechaEntrada, fechaSalida, costo, formaPago) VALUES (?, ?, ?, ?, ?)";

	            
	            ps = connection.prepareStatement(consultaString);
	            ps.setString(1, numReserva);
	            ps.setString(2, fechaEntrada);
	            ps.setString(3, fechaSalida);
	            ps.setString(4, costo);
	            ps.setString(5, formaPago);

	            // Ejecutamos la consulta
	            ps.executeUpdate();

	        } catch (SQLException ex) {
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
