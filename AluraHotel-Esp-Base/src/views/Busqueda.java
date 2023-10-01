package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import backend.BuscarReserva;
import backend.EditarReserva;
import backend.EliminarReserva;
import backend.huesped_buscar;
import backend.huesped_editar;
import backend.huesped_eliminar;
import backend.mostrarReservas;
import backend.huesped_mostrar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	private int rowSelect;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		// Mostrar todos las reservas registradas
		mostrarReservas mostrarR = new mostrarReservas();
		DefaultTableModel modeloR = mostrarR.mostrarReserva();
		tbReservas.setModel(modeloR);
		
		tbReservas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Seleccion
				rowSelect = tbReservas.getSelectedRow();
			}
		});
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		// Mostrar todos los huespedes registrados
		huesped_mostrar mostrarHuepedes = new huesped_mostrar();
		DefaultTableModel modelo = mostrarHuepedes.mostrarHuespedes();
		tbHuespedes.setModel(modelo);
		
		
		rowSelect = tbHuespedes.getSelectedRow();
		tbHuespedes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Seleccion
				rowSelect = tbHuespedes.getSelectedRow();
			}
		});
		
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Obtener datos del input
				String buscar = txtBuscar.getText().toString();
				
				// Validar que no se busque ningun simbolo
				if (!buscar.matches("[a-zA-Z0-9]*")) {
					JOptionPane.showMessageDialog(null, "Solo puedes ingresar letras o numeros");
				} else {
					if (scroll_table.isShowing()) {
						// Buscar reservas
						BuscarReserva buscarReserva = new BuscarReserva();
						DefaultTableModel modelo = buscarReserva.buscarReserva(buscar);
						tbReservas.setModel(modelo);
						
					} else if (scroll_tableHuespedes.isShowing()) {
						// Buscar huespedes
						// Crear nueva tabla
						huesped_buscar bsHuesped = new huesped_buscar();
						DefaultTableModel modelo = bsHuesped.buscarHuesped(buscar);
						tbHuespedes.setModel(modelo);
					}
				}
			}
		});

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (scroll_table.isShowing()) {
					// Editar reservas
					
					EditarReserva editarReserva = new EditarReserva();
					
					if (rowSelect == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona la fila a editar");
					} else {
						String [] reservas = new String[5];
						for (int i = 0; i < reservas.length; i++) {
							reservas[i] = tbReservas.getValueAt(rowSelect, i).toString();
						}
						
						editarReserva.editarReserv(reservas[0], reservas[1], reservas[2], reservas[3], reservas[4]);
						JOptionPane.showMessageDialog(null, "Editado Correctamente");
					}
				} else if (scroll_tableHuespedes.isShowing()) {
					// Editar Huespedes
					huesped_editar editHuesped = new huesped_editar();
					if (rowSelect == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona la fila a editar");
					} else {
						String [] huespedes = new String[7];
						for (int i = 0; i < huespedes.length; i++) {
							huespedes[i] = tbHuespedes.getValueAt(rowSelect, i).toString();
						}
						editHuesped.editarHuesped(huespedes[0], huespedes[1], huespedes[2], huespedes[3], huespedes[4], huespedes[5], huespedes[6]);
						JOptionPane.showMessageDialog(null, "Editado Correctamente");
					}
				}
			}
		});
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EliminarReserva eliminarReserva = new EliminarReserva();
				huesped_eliminar eliminarH = new huesped_eliminar();
				
				if (scroll_table.isShowing()) {
					// Eliminar reservas
					if (rowSelect == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona la fila a eliminar");
					} else {
						eliminarReserva.elimnarReserva(tbReservas.getValueAt(rowSelect, 0).toString());
						eliminarH.eliminarHuesped(tbReservas.getValueAt(rowSelect, 0).toString());
						JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
						//Actualizar tabla reservas
						DefaultTableModel modeloR = mostrarR.mostrarReserva();
						tbReservas.setModel(modeloR);
						//Actualizar tabla huespedes
						DefaultTableModel modelo = mostrarHuepedes.mostrarHuespedes();
						tbHuespedes.setModel(modelo);
					}
					
				} else if (scroll_tableHuespedes.isShowing()) {
					// Eliminar huespedes
					if (rowSelect == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona la fila a eliminar");
					} else {
						eliminarH.eliminarHuesped(tbHuespedes.getValueAt(rowSelect, 6).toString());
						eliminarReserva.elimnarReserva(tbHuespedes.getValueAt(rowSelect, 6).toString());
						JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
						//Actualizar tabla reservas
						DefaultTableModel modeloR = mostrarR.mostrarReserva();
						tbReservas.setModel(modeloR);
						//Actualizar tabla huespedes
						DefaultTableModel modelo = mostrarHuepedes.mostrarHuespedes();
						tbHuespedes.setModel(modelo);
					}
				}
			}
		});
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
// Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
