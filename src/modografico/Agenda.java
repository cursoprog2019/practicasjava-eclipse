package modografico;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

public class Agenda extends JFrame {

	private JPanel contentPane;
	private static JTextField codigo;
	private static JTextField nombre;
	private static JTextField telefono;
	private static JButton btnEnviar;

	static JButton btnAlta;
	static JButton btnBaja;
	static JButton btnModificacion;
	static JButton btnConsulta;
	static JButton btnCancelar;
	static JButton btnE;

	static Connection con = null;
	static JLabel errores;
	static JLabel ayuda;

	String opcion = "";
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JCheckBox chckbxOrdenado;
	
	ArrayList<Contacto> contactos = null;
	private JTextField filtro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// antes de abrir la ventana nos conectamos
					System.out.println("Conectando...");
					con = bdutil.conexion();
					System.out.println("Conectado");
					Agenda frame = new Agenda();
					frame.setVisible(true);
					if (con == null) {
						errores.setText("Error al conectar a la base de datos");
					} else {
						ayuda.setText("Pulsa una de las opciones de arriba");
						activarCampos(false);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	static void activarCampos(boolean activar) {
		// activar a true: activa todos
		// activar a dalse: desactiva todos
		codigo.setEditable(activar);
		nombre.setEditable(activar);
		telefono.setEditable(activar);
		btnEnviar.setEnabled(activar);
		btnCancelar.setEnabled(activar);
	}

	static void limpiarCampos() {
		codigo.setText("");
		nombre.setText("");
		telefono.setText("");
		ayuda.setText("");
		errores.setText("");
		btnAlta.setEnabled(false);
		btnBaja.setEnabled(false);
		btnModificacion.setEnabled(false);
		btnConsulta.setEnabled(false);
		btnCancelar.setEnabled(true);
		btnE.setVisible(false);
	}

	static void activarMenu() {
		// Activa todos los menús de la barra superior
		btnAlta.setEnabled(true);
		btnBaja.setEnabled(true);
		btnModificacion.setEnabled(true);
		btnConsulta.setEnabled(true);
		btnE.setVisible(false);
	}

	static void inicializar() {
		// Deja la pantalla como al inicio de la aplicación
		codigo.setText("");
		nombre.setText("");
		telefono.setText("");
		activarCampos(false);
		activarMenu();
	}

	/**
	 * Create the frame.
	 */
	public Agenda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAlta = new JButton("ALTA");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcion = "ALTA";
				limpiarCampos();
				btnAlta.setEnabled(true);
				ayuda.setText("Rellena los campos y pulsa Enviar");
				activarCampos(true);
				// rellenamos de forma automática el siguiente código
				codigo.setText(Integer.toString(bdutil.siguienteCodigo(con)));
				// con esta instrucción no permite modificar el código
				codigo.setEditable(false);
				nombre.grabFocus();
				btnE.setVisible(true);
			}
		});
		btnAlta.setBounds(20, 11, 89, 23);
		contentPane.add(btnAlta);

		btnBaja = new JButton("BAJA");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcion = "BAJA";
				limpiarCampos();
				ayuda.setText("Introduce el código a borrar y pulsa Enviar");
				btnBaja.setEnabled(true);
				codigo.setEditable(true);
				btnEnviar.setEnabled(true);
				codigo.grabFocus();
			}

		});
		btnBaja.setBounds(106, 11, 102, 23);
		contentPane.add(btnBaja);

		btnModificacion = new JButton("MODIFICACION");
		btnModificacion.setMargin(new Insets(2, 2, 2, 2));
		btnModificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcion = "MODIFICACION";
				limpiarCampos();
				ayuda.setText("Introduce el código a modificar y pulsa Enviar");
				btnModificacion.setEnabled(true);
				codigo.setEditable(true);
				btnEnviar.setEnabled(true);
				codigo.grabFocus();
			}
		});
		btnModificacion.setBounds(205, 11, 102, 23);
		contentPane.add(btnModificacion);

		btnConsulta = new JButton("CONSULTA");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcion = "CONSULTA";
				limpiarCampos();
				ayuda.setText("Introduce el código a consultar y pulsa Enviar");
				btnConsulta.setEnabled(true);
				codigo.setEditable(true);
				btnEnviar.setEnabled(true);
				codigo.grabFocus();

			}
		});
		btnConsulta.setBounds(305, 11, 105, 23);
		contentPane.add(btnConsulta);

		ayuda = new JLabel("");
		ayuda.setFont(new Font("Tahoma", Font.BOLD, 11));
		ayuda.setForeground(Color.BLUE);
		ayuda.setBounds(20, 45, 386, 14);
		contentPane.add(ayuda);

		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(20, 70, 46, 14);
		contentPane.add(lblCdigo);

		codigo = new JTextField();
		codigo.setBounds(98, 67, 86, 20);
		contentPane.add(codigo);
		codigo.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(20, 98, 46, 14);
		contentPane.add(lblNombre);

		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(98, 95, 231, 20);
		contentPane.add(nombre);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(20, 126, 68, 14);
		contentPane.add(lblTelfono);

		telefono = new JTextField();
		telefono.setColumns(10);
		telefono.setBounds(98, 123, 86, 20);
		contentPane.add(telefono);

		errores = new JLabel("");
		errores.setFont(new Font("Tahoma", Font.BOLD, 11));
		errores.setForeground(Color.RED);
		errores.setBounds(20, 156, 386, 14);
		contentPane.add(errores);

		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contacto contacto = null;
				ayuda.setText("");
				errores.setText("");
				// Cuando asignamos un valor a la variable opcion
				// lo que hacemos en realidad es: programar el botón "Enviar"
				switch (opcion) {
				case "ALTA":
					contacto = new Contacto(Integer.parseInt(codigo.getText()), nombre.getText(), telefono.getText());
					String mensaje = bdutil.alta(con, contacto);
					if (mensaje == "") {
						ayuda.setText("Contacto insertado correctamente");
						inicializar();
					} else {
						errores.setText(mensaje);
					}
					break;
				case "BAJA":
					boolean correcto = false;
					try {
						correcto = bdutil.baja(con, Integer.parseInt(codigo.getText()));
					} catch (Exception e2) {
						System.out.println("ERROR: " + e2.getMessage());
					}
					if (correcto) {
						ayuda.setText("Contacto borrado correctamente");
						inicializar();
					} else {
						errores.setText("Contacto no encontrado");
					}

					break;
				case "MODIFICACION":
					// PASO-1 Obtener los datos para un código
					try {
						contacto = bdutil.consulta(con, Integer.parseInt(codigo.getText()));
					} catch (Exception e2) {
						System.out.println("ERROR: " + e2.getMessage());
					}
					if (contacto != null) { // encontrado
						nombre.setText(contacto.getNombre());
						telefono.setText(contacto.getTelefono());
						codigo.setEditable(false);
						nombre.setEditable(true);
						telefono.setEditable(true);
						ayuda.setText("Modifica los datos y pulsa Enviar");
						// Creamos una segunda opción para terminar el proceso de guardar el contacto
						opcion = "MODIFICACION2";
					} else {
						errores.setText("Contacto no encontrado");
					}
					break;
				case "MODIFICACION2":
					// PASO-1 Guardar los datos de ese código
					contacto = new Contacto(Integer.parseInt(codigo.getText()), nombre.getText(), telefono.getText());
					if (bdutil.modificacion(con, contacto)) {
						ayuda.setText("Datos modificados correctamente");
						inicializar();
					} else {
						errores.setText("Error al modificar. Revise los datos");
					}
					break;
				case "CONSULTA":
					try {
						contacto = bdutil.consulta(con, Integer.parseInt(codigo.getText()));
					} catch (Exception e2) {
						System.out.println("ERROR: " + e2.getMessage());
					}
					if (contacto != null) { // encontrado
						nombre.setText(contacto.getNombre());
						telefono.setText(contacto.getTelefono());
						activarMenu();
					} else {
						nombre.setText("");
						telefono.setText("");
						errores.setText("Contacto no encontrado");
					}
				} // switch

			}
		});
		btnEnviar.setBounds(205, 66, 89, 23);
		contentPane.add(btnEnviar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicializar();
				errores.setText("");
			}
		});
		btnCancelar.setBounds(292, 66, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnListado = new JButton("LISTADO");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = filtro.getText();
				// inicializar la caja de texto
				textArea.setText("");
				// comprobar si hemos marcado el check en Ordenado
				if (chckbxOrdenado.isSelected()) {
					if (texto == "") { // no hay filtro 
						contactos = bdutil.consultaOrdenada(con);
					} else {
						contactos = bdutil.consulta(con,texto,true);
					}
					
				} else { // sin ordenar
					if (texto == "") {
						contactos = bdutil.consulta(con);
					} else {
						contactos = bdutil.consulta(con,texto,false);
					}
				}
				
				for (int i = 0; i < contactos.size(); i++) {
					Contacto contacto = contactos.get(i);
					textArea.append(contacto.getCodigo() + ": " + 
									contacto.getNombre() + " --> " +
									contacto.getTelefono() + "\n");
				}
				System.out.println(textArea.getText());
				
			}
		});
		btnListado.setBounds(407, 11, 89, 23);
		contentPane.add(btnListado);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 181, 478, 193);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 20));
		scrollPane.setViewportView(textArea);
		
		chckbxOrdenado = new JCheckBox("Ordenado");
		chckbxOrdenado.setBounds(417, 45, 97, 23);
		contentPane.add(chckbxOrdenado);
		
		btnE = new JButton("E");
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				codigo.setEditable(true);
			}
		});
		btnE.setToolTipText("Pulsa para editar");
		btnE.setVisible(false);
		btnE.setMargin(new Insets(2, 2, 2, 2));
		btnE.setBounds(193, 66, 15, 23);
		contentPane.add(btnE);
		
		filtro = new JTextField();
		filtro.setBounds(410, 123, 86, 20);
		contentPane.add(filtro);
		filtro.setColumns(10);
		
		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setBounds(375, 126, 31, 14);
		contentPane.add(lblFiltro);
	}
}
