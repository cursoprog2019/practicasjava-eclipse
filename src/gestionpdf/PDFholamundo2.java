package gestionpdf;

import java.awt.Color;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


// hola mundo, con posición y color
public class PDFholamundo2 {

	public static final String FICHERO = "c:/datos/PDFholamundo2.pdf";

	public static void main(String[] args) {
		// crear un objeto de tipo documento PDF
		PDDocument doc = new PDDocument();
		// crear un objeto de tipo página PDF (tamaño A4)
		// si no le pasmos ningún parámetro, lo crea tamaño Letter
		PDPage pag1 = new PDPage(PDRectangle.A4);
		// añadir la página al documento
		doc.addPage(pag1);
		// Obtener el ancho y el alto de la página
		float ancho = pag1.getMediaBox().getWidth();
		float alto = pag1.getMediaBox().getHeight();
		System.out.println("ANCHO: " + ancho);
		System.out.println("ALTO: " + alto);
		try {
			// crear un canal de contenido pdf
			PDPageContentStream pdf = new PDPageContentStream(doc, pag1);
			// inicio del texto
			pdf.beginText(); // obligatorio para utilizar showText
			// establecer fuente y tamaño
			PDFont fuente = PDType1Font.TIMES_BOLD;
			pdf.setFont(fuente, 24); // obligatorio para utilizar showText
			// cambiar color (opcional)
			pdf.setNonStrokingColor(Color.BLUE);
			// cambiar la posición (opcional)
			pdf.newLineAtOffset(300,420);
			// escribir un texto
			pdf.showText("Hola Mundo");
			// cierre del texto
			pdf.endText(); // obligatorio
			// cerrar el canal
			pdf.close();  // obligatorio 
			// guardar el documento PDF en un fichero
			doc.save(FICHERO);
			// cerrar el documento PDF
			doc.close();
			System.out.println("Fichero PDF generado");
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}

	}

}
