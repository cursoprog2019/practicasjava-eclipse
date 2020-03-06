package gestionpdf;

import java.awt.Color;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


// escribir varios textos usando newLine()
public class PDFholamundo4 {

	public static final String FICHERO = "c:/datos/PDFholamundo4.pdf";

	public static void main(String[] args) {
		// crear un objeto de tipo documento PDF
		PDDocument doc = new PDDocument();
		// crear un objeto de tipo p�gina PDF (tama�o A4)
		// si no le pasmos ning�n par�metro, lo crea tama�o Letter
		PDPage pag1 = new PDPage(PDRectangle.A4);
		// a�adir la p�gina al documento
		doc.addPage(pag1);
		// Obtener el ancho y el alto de la p�gina
		float ancho = pag1.getMediaBox().getWidth();
		float alto = pag1.getMediaBox().getHeight();
		System.out.println("ANCHO: " + ancho);
		System.out.println("ALTO: " + alto);
		try {
			// crear un canal de contenido pdf
			PDPageContentStream pdf = new PDPageContentStream(doc, pag1);
			// inicio del texto
			pdf.beginText(); // obligatorio para utilizar showText
			// establecer fuente y tama�o
			PDFont fuente = PDType1Font.TIMES_BOLD;
			pdf.setFont(fuente, 24); // obligatorio para utilizar showText
			// cambiar color (opcional)
			pdf.setNonStrokingColor(Color.BLUE);
			// cambiar la posici�n (opcional)
			pdf.newLineAtOffset(100,650);
			// establecemos el espacio entre l�neas
			pdf.setLeading(48); // si no lo ponemos, por defecto es 0
			// escribir un texto
			pdf.showText("Hola Mundo\notracosa");
			// nueva linea
			pdf.newLine();
			pdf.showText("Una nueva linea");
			// nueva linea
			pdf.newLine();
			pdf.showText("Otra nueva linea");
			// nueva linea m�s separada
			pdf.setLeading(96);
			pdf.newLine();
			pdf.showText("Otra m�s separada");
			// l�nea muy larga OJO!!! se corta 
			pdf.newLine();
			pdf.showText("L�nea muy larga L�nea muy larga L�nea muy larga L�nea muy larga L�nea muy larga L�nea muy larga L�nea muy larga ");
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
