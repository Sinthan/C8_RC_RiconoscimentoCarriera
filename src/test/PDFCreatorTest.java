package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import controller.PDFCreator;
import model.ValidatedExam;

class PDFCreatorTest extends Mockito {
	private static final ArrayList<Integer> CfuInterni = null;
	PDFCreator pdfCre;

	@BeforeEach
	void setUp() throws Exception {
		pdfCre= new PDFCreator("test");
	}

	@Test
	void createReportPdfTest() {
		ArrayList<ValidatedExam> esamiValidati = new ArrayList<ValidatedExam>();
		String nomeStudente = "test";
		ArrayList<Integer> cfuEsterni = new ArrayList<Integer>();
		String note = "test";
		pdfCre.createReportPdf(esamiValidati, CfuInterni, nomeStudente, cfuEsterni, note);
		
	}

}
