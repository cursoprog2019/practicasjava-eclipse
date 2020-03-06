package modografico;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Paneles extends JFrame {

	private JPanel contentPane;
	JPanel panel1;
	JPanel panel2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Paneles frame = new Paneles();
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
	public Paneles() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 434, 261);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lblPantalla = new JLabel("Pantalla 1");
		lblPantalla.setBounds(138, 11, 146, 94);
		lblPantalla.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel1.add(lblPantalla);
		
		JButton boton1 = new JButton("Pantalla 2");
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(true);
			}
		});
		boton1.setBounds(154, 158, 130, 23);
		panel1.add(boton1);
		
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(0, 0, 434, 261);
		contentPane.add(panel2);
		
		JLabel lblPantalla_2 = new JLabel("Pantalla 2");
		lblPantalla_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPantalla_2.setBounds(138, 11, 146, 94);
		panel2.add(lblPantalla_2);
		
		JButton boton2 = new JButton("Pantalla 1");
		boton2.setBounds(154, 158, 118, 23);
		panel2.add(boton2);
	}
}
