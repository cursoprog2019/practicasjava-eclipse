package gestionpdf;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

public class GenerarPDF extends JFrame {

	private JPanel contentPane;
	private JTextField campotexto;
	private JTextArea textarea;
	private final String FICHERO = "c:/datos/GenerarPDF.pdf";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarPDF frame = new GenerarPDF();
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
	public GenerarPDF() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GENERACION DE PDFs");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setBounds(10, 11, 414, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblTexto = new JLabel("Texto");
		lblTexto.setBounds(10, 53, 46, 14);
		contentPane.add(lblTexto);
		
		campotexto = new JTextField();
		campotexto.setBounds(51, 50, 280, 20);
		contentPane.add(campotexto);
		campotexto.setColumns(10);
		
		textarea = new JTextArea();
		textarea.setEditable(false);
		textarea.setBounds(10, 78, 414, 135);
		contentPane.add(textarea);
		
		JButton btnAnadir = new JButton("A\u00F1adir");
		btnAnadir.addActionListener(new ActionListener() {
			
			// Este método se ejecuta al pulsar el botón "Añadir"
			public void actionPerformed(ActionEvent e) {
				
				// obtener el texto del campo
				String texto = campotexto.getText();
				
				// añadirlo al textarea
				textarea.append(texto + "\n");
				// textarea.setText(textarea.getText() + texto);
				
				// limpiar campo de texto
				campotexto.setText("");
				
				// ponerle el foco al campo texto
				campotexto.grabFocus();			
				
				
			}  // termina la acción del botón "Añadir"
		});
		btnAnadir.setBounds(335, 49, 89, 23);
		contentPane.add(btnAnadir);
		
		JButton btnGenerarPdf = new JButton("Generar PDF");
		btnGenerarPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// obtener todo el texto del textarea
				String datos = textarea.getText();
				// separar en un array de String cada elemento
				// el separador de elementos es \n
				String[] lineas = datos.split("\n");
				
				// recorrer el array de String
				for (int i = 0; i < lineas.length; i++) {
					System.out.println(i + ": " + lineas[i]);
				}
				
				// crear un objeto de tipo documento PDF
				PDDocument doc = new PDDocument();
				// crear un objeto de tipo página PDF (tamaño A4)
				// si no le pasmos ningún parámetro, lo crea tamaño Letter
				PDPage pag1 = new PDPage(PDRectangle.A4);
				// añadir la página al documento
				doc.addPage(pag1);

				try {
					// crear un canal de contenido pdf
					PDPageContentStream pdf = new PDPageContentStream(doc, pag1);
					// inicio del texto
					pdf.beginText(); // obligatorio para utilizar showText
					// establecer fuente y tamaño
					PDFont fuente = PDType1Font.TIMES_BOLD;
					pdf.setFont(fuente, 12); // obligatorio para utilizar showText
					
					// colocar el cursor arriba a la izquierda
					pdf.newLineAtOffset(100,750);
					// espacio entre líneas
					pdf.setLeading(24);
					// escribir las líneas del textarea
					for (int i = 0; i < lineas.length; i++) {
						pdf.showText(lineas[i]);
						pdf.newLine();
					}
					// cierre del texto
					pdf.endText(); // obligatorio
					// cerrar el canal
					pdf.close();  // obligatorio 
					// guardar el documento PDF en un fichero
					doc.save(FICHERO);
					// cerrar el documento PDF
					doc.close();
					System.out.println("Fichero PDF generado");
				} catch (Exception e2) {
					System.out.println("ERROR: " + e2.getMessage());
				}
				
				
				
			}
		});
		btnGenerarPdf.setBounds(157, 224, 136, 23);
		contentPane.add(btnGenerarPdf);
	}
}
