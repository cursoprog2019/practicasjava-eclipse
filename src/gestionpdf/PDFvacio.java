package gestionpdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class PDFvacio {

	public static final String FICHERO = "c:/datos/PDFvacio.pdf";

	public static void main(String[] args) {
		// crear un objeto de tipo documento PDF
		PDDocument doc = new PDDocument();
		// crear un objeto de tipo p�gina PDF (tama�o A4)
		// si no le pasmos ning�n par�metro, lo crea tama�o Letter
		PDPage pag1 = new PDPage(PDRectangle.A4);
		// a�adir la p�gina al documento
		doc.addPage(pag1);
		// El try lo ponemos porque nos obliga el m�todo save y el close
		try {
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
