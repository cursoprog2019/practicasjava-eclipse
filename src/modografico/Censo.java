/*
 * SUPUESTO PRACTICO DE INTERFAZ GRAFICO
 * GESTION DE UN CENSO
 * Debemos crear dos tabla una para menoresy otra para mayores edad 
 * 1.- 	Disponer de una casilla donde ponemos la contraseña
 * 		y un botón que nos permita conectar con la BD
 * 2.- 	Otra casilla donde vamos poniendo edades 
 * 		y un botón para ir insertando
 * 3.- 	Una sección de estadisticas donde mostramos el nº de registros
 * 		y la media de cada tabla
 * 		 
 */


package modografico;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.JPasswordField;

public class Censo extends JFrame {

	private Connection con = null;
	private JPanel contentPane;
	private JLabel etqConectado;
	private JPasswordField txtContrasena;
	private JTextField txtEdad;
	private JTextField txtMenoresTotal;
	private JTextField txtMenoresMedia;
	private JTextField txtMayoresTotal;
	private JTextField txtMayoresMedia;
	private JLabel etqTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Censo frame = new Censo();
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
	public Censo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAytoJumilla = new JLabel("AYTO. JUMILLA - CENSO");
		lblAytoJumilla.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAytoJumilla.setHorizontalAlignment(SwingConstants.CENTER);
		lblAytoJumilla.setBounds(0, 11, 434, 14);
		contentPane.add(lblAytoJumilla);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 36, 434, 2);
		contentPane.add(separator);
		
		JLabel etqConectar = new JLabel("BASE DE DATOS - Contrase\u00F1a:");
		etqConectar.setHorizontalAlignment(SwingConstants.RIGHT);
		etqConectar.setBounds(10, 49, 172, 14);
		contentPane.add(etqConectar);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.setMargin(new Insets(2, 2, 2, 2));
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clave = new String(txtContrasena.getPassword());
				con = CensoLib.conexion(clave);
				if (con != null) { // conexión correcta
					etqConectado.setForeground(Color.GREEN);
					etqConectado.setText("Conectado");
				} else {
					etqConectado.setForeground(Color.RED);
					etqConectado.setText("ERROR");
				}
			}
		});
		btnConectar.setBounds(278, 45, 68, 23);
		contentPane.add(btnConectar);
		
		etqConectado = new JLabel("");
		etqConectado.setForeground(Color.GREEN);
		etqConectado.setBounds(356, 49, 68, 14);
		contentPane.add(etqConectado);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 74, 434, 2);
		contentPane.add(separator_1);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(192, 46, 84, 20);
		contentPane.add(txtContrasena);
		
		txtEdad = new JTextField();
		txtEdad.setBounds(192, 87, 84, 20);
		contentPane.add(txtEdad);
		txtEdad.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (con != null) {
					int edad = 0;
					
					try {
						edad = Integer.parseInt(txtEdad.getText());
						boolean correcto = CensoLib.enviar(con,edad);
						if (correcto) {
							//JOptionPane.showMessageDialog(null, "Enviado correctamente");
							int total = (int)CensoLib.calculaTotal(con,"menores") + (int)CensoLib.calculaTotal(con,"mayores");
							etqTotal.setText(Integer.toString(total));
							
							txtEdad.setText(""); // poner en blanco la casilla
							txtEdad.grabFocus(); // poner el cursor en la casilla de texto
							
							txtMenoresTotal.setText( Double.toString(CensoLib.calculaTotal(con,"menores")) );
							txtMenoresMedia.setText( Double.toString(CensoLib.calculaMedia(con,"menores")) );
							txtMayoresTotal.setText( Double.toString(CensoLib.calculaTotal(con,"mayores")) );
							txtMayoresMedia.setText( Double.toString(CensoLib.calculaMedia(con,"mayores")) );

							
						} else {
							JOptionPane.showMessageDialog(null, "Error al enviar");
						}
						
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Número incorrecto");
						System.out.print("ERROR: " + ex.getMessage());
						//ex.printStackTrace();
					}
					
					
				} else {
					JOptionPane.showMessageDialog(null, "Antes necesita conectar");
				}
			}
		});
		btnEnviar.setMargin(new Insets(2, 2, 2, 2));
		btnEnviar.setBounds(278, 86, 68, 23);
		contentPane.add(btnEnviar);
		
		JLabel etqEdad = new JLabel("Introduce edad:");
		etqEdad.setHorizontalAlignment(SwingConstants.RIGHT);
		etqEdad.setBounds(77, 90, 105, 14);
		contentPane.add(etqEdad);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(0, 118, 434, 2);
		contentPane.add(separator_1_1);
		
		JLabel lblEstadisticas = new JLabel("ESTADISTICAS");
		lblEstadisticas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstadisticas.setBounds(38, 131, 139, 14);
		contentPane.add(lblEstadisticas);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMenoresTotal.setText( Double.toString(CensoLib.calculaTotal(con,"menores")) );
				txtMenoresMedia.setText( Double.toString(CensoLib.calculaMedia(con,"menores")) );
				txtMayoresTotal.setText( Double.toString(CensoLib.calculaTotal(con,"mayores")) );
				txtMayoresMedia.setText( Double.toString(CensoLib.calculaMedia(con,"mayores")) );
			}
		});
		btnCalcular.setBounds(141, 129, 89, 23);
		contentPane.add(btnCalcular);
		
		JLabel lblMenores = new JLabel("MENORES");
		lblMenores.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMenores.setBounds(38, 167, 84, 14);
		contentPane.add(lblMenores);
		
		JLabel lblNewLabel = new JLabel("Total");
		lblNewLabel.setBounds(38, 192, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtMenoresTotal = new JTextField();
		txtMenoresTotal.setEditable(false);
		txtMenoresTotal.setBounds(116, 189, 86, 20);
		contentPane.add(txtMenoresTotal);
		txtMenoresTotal.setColumns(10);
		
		JLabel lblMedia = new JLabel("Media");
		lblMedia.setBounds(38, 220, 46, 14);
		contentPane.add(lblMedia);
		
		txtMenoresMedia = new JTextField();
		txtMenoresMedia.setEditable(false);
		txtMenoresMedia.setColumns(10);
		txtMenoresMedia.setBounds(116, 217, 86, 20);
		contentPane.add(txtMenoresMedia);
		
		JLabel lblMayores = new JLabel("MAYORES");
		lblMayores.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMayores.setBounds(241, 167, 84, 14);
		contentPane.add(lblMayores);
		
		JLabel lblNewLabel_1 = new JLabel("Total");
		lblNewLabel_1.setBounds(241, 192, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtMayoresTotal = new JTextField();
		txtMayoresTotal.setEditable(false);
		txtMayoresTotal.setColumns(10);
		txtMayoresTotal.setBounds(319, 189, 86, 20);
		contentPane.add(txtMayoresTotal);
		
		JLabel lblMedia_1 = new JLabel("Media");
		lblMedia_1.setBounds(241, 220, 46, 14);
		contentPane.add(lblMedia_1);
		
		txtMayoresMedia = new JTextField();
		txtMayoresMedia.setEditable(false);
		txtMayoresMedia.setColumns(10);
		txtMayoresMedia.setBounds(319, 217, 86, 20);
		contentPane.add(txtMayoresMedia);
		
		etqTotal = new JLabel("");
		etqTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		etqTotal.setBounds(356, 74, 68, 46);
		contentPane.add(etqTotal);
	}
}
