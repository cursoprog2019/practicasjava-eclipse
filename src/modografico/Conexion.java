package modografico;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Conexion extends JFrame {

	
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conexion frame = new Conexion();
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
	public Conexion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 200, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("...");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(48, 56, 89, 14);
		contentPane.add(label);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        // String driver = "oracle.jdbc.driver.OracleDriver" // ORACLE
		        String driver = "com.mysql.cj.jdbc.Driver"; // MySQL
		        // String url = "jdbc:oracle:thin:@localhost:1521:xe"; // ORACLE
		        //String url = "jdbc:mysql://remotemysql.com:3306/8QznhvYaIi"; // MySQL
		        String url = "jdbc:mysql://db4free.net:3306/cursoprog2019"; // MySQL
		        // estructura de la url: protocolo://servidor:puerto/basedatos
		        String usuario = "cursoprog2019";
		        String password = "12345678";
		        
		        try {
		            // 1. Registrar el Driver JDBC
		            // (lo carga en tiempo real en la memoria)
		            Class.forName(driver);
		            // 2. Obtener una conexión
		            // Necesitamos url (protocolo,servidor,puerto,base de datos),
		            // usuario y contraseña
		            Connection con = DriverManager.getConnection(url, usuario, password);
		            label.setText("Conectado");
		            // 3. Cerrar la conexión
		            con.close();
		        } catch (Exception e2) {
		            System.out.println("ERROR: " + e2.getMessage());
		            e2.printStackTrace();
		        }
				
				
				
			}
		});
		btnConectar.setBounds(48, 22, 89, 23);
		contentPane.add(btnConectar);
		
	
	}
}
