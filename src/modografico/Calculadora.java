package modografico;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;

public class Calculadora extends JFrame {

	private JPanel contentPane;
	private JTextField numero1;
	private JTextField numero2;
	private JTextField resultado;
	private JButton btnSumar_1;
	private JButton btnSumar_2;
	private JButton btnSumar_3;
	private JButton btnC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculadora frame = new Calculadora();
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
	public Calculadora() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel etqNumero1 = new JLabel("N\u00FAmero 1");
		etqNumero1.setFont(new Font("Tahoma", Font.BOLD, 16));
		etqNumero1.setBounds(42, 37, 96, 14);
		contentPane.add(etqNumero1);
		
		JLabel etqNumero2 = new JLabel("N\u00FAmero 2");
		etqNumero2.setFont(new Font("Tahoma", Font.BOLD, 16));
		etqNumero2.setBounds(42, 65, 96, 14);
		contentPane.add(etqNumero2);
		
		numero1 = new JTextField();
		numero1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		numero1.setBounds(136, 36, 86, 20);
		contentPane.add(numero1);
		numero1.setColumns(10);
		
		numero2 = new JTextField();
		numero2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		numero2.setColumns(10);
		numero2.setBounds(136, 62, 86, 20);
		contentPane.add(numero2);
		
		JButton btnSumar = new JButton("+");
		btnSumar.setMargin(new Insets(0, 0, 0, 0));
		btnSumar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSumar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num1 = Integer.parseInt(numero1.getText());
				int num2 = Integer.parseInt(numero2.getText());
				String cadena = Integer.toString(num1 + num2);
				resultado.setText(cadena);
			}
		});
		btnSumar.setBounds(136, 90, 22, 30);
		contentPane.add(btnSumar);
		
		JLabel etqResultado = new JLabel("Resultado");
		etqResultado.setFont(new Font("Tahoma", Font.BOLD, 16));
		etqResultado.setBounds(42, 165, 96, 14);
		contentPane.add(etqResultado);
		
		resultado = new JTextField();
		resultado.setEditable(false);
		resultado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		resultado.setColumns(10);
		resultado.setBounds(136, 162, 86, 20);
		contentPane.add(resultado);
		
		btnSumar_1 = new JButton("-");
		btnSumar_1.setMargin(new Insets(0, 0, 0, 0));
		btnSumar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num1 = Integer.parseInt(numero1.getText());
				int num2 = Integer.parseInt(numero2.getText());
				String cadena = Integer.toString(num1 - num2);
				resultado.setText(cadena);
			}
		});
		btnSumar_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSumar_1.setBounds(163, 90, 22, 30);
		contentPane.add(btnSumar_1);
		
		btnSumar_2 = new JButton("*");
		btnSumar_2.setMargin(new Insets(0, 0, 0, 0));
		btnSumar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num1 = Integer.parseInt(numero1.getText());
				int num2 = Integer.parseInt(numero2.getText());
				String cadena = Integer.toString(num1 * num2);
				resultado.setText(cadena);
			}
		});
		btnSumar_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSumar_2.setBounds(188, 90, 22, 30);
		contentPane.add(btnSumar_2);
		
		btnSumar_3 = new JButton("/");
		btnSumar_3.setMargin(new Insets(0, 0, 0, 0));
		btnSumar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double num1 = Double.parseDouble(numero1.getText());
				double num2 = Double.parseDouble(numero2.getText());
				String cadena = Double.toString(num1 / num2);
				resultado.setText(cadena.substring(0,8));
			}
		});
		btnSumar_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSumar_3.setBounds(215, 90, 22, 29);
		contentPane.add(btnSumar_3);
		
		btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numero1.setText("");
				numero2.setText("");
				resultado.setText("");
			}
		});
		btnC.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnC.setBounds(136, 124, 101, 20);
		contentPane.add(btnC);
		
		
	}
}
