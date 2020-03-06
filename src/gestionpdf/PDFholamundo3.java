package gestionpdf;

import java.awt.Color;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


// escribir varios textos usando newLineAtOffset
public class PDFholamundo3 {

	public static final String FICHERO = "c:/datos/PDFholamundo3.pdf";

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
			pdf.setFont(fuente, 12); // obligatorio para utilizar showText
			// cambiar color (opcional)
			pdf.setNonStrokingColor(Color.BLUE);
			
			
			// posición de la primera linea
			pdf.newLineAtOffset(100,alto-112);
			pdf.showText("Línea-1");
			// posición de la segunda línea
			// OJO!!! las coordenadas son relativas
			// desde el anterior (donde se encuentra el cursor)
			pdf.newLineAtOffset(0,-24); 
			pdf.showText("Línea-2");
			// posición de la tercera línea
			pdf.newLineAtOffset(0,-24); 
			pdf.showText("Línea-3");
			// escribir horizontalmente
			pdf.newLineAtOffset(100,0); 
			pdf.showText("al lado de linea-3");
			
			// antes me muevo 100 a la izquierda para dejarlo 
			//donde estaba anteriormente
			pdf.newLineAtOffset(-100,0);
			// escribir desde Linea-4 a Linea-10 con un bucle
			for (int i = 4; i <= 10; i++) {
				pdf.newLineAtOffset(0,-24); 
				pdf.showText("Línea-" + i);
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
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}

	}

}
