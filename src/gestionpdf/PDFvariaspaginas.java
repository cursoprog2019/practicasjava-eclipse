package gestionpdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class PDFvariaspaginas {

	public static final String FICHERO = "c:/datos/PDFvariaspaginas.pdf";

	public static void main(String[] args) {
		// crear un objeto de tipo documento PDF
		PDDocument doc = new PDDocument();
		// crear un objeto de tipo página PDF (tamaño A4)
		// si no le pasmos ningún parámetro, lo crea tamaño Letter
		PDPage pag1;
		// añadir varias páginas al documento
		for (int i = 1; i <= 10; i++) {
			pag1 = new PDPage(PDRectangle.A4);
			doc.addPage(pag1);
		}
		// El try lo ponemos porque nos obliga el método save y el close
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
