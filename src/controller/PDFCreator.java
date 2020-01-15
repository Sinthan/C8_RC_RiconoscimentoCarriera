package controller;

import java.util.ArrayList;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import model.ValidatedExam;

import com.itextpdf.layout.element.Table;

import java.io.*;
import com.itextpdf.kernel.pdf.PdfDocument;

public class PDFCreator {
	
	PdfDocument pdf;
	Document document;
	
	  public PDFCreator(String destinazione) throws FileNotFoundException {
		  pdf = new PdfDocument(new PdfWriter(destinazione));
	      document = new Document(pdf);
	  }
	  
      public void createReportPdf(ArrayList<ValidatedExam> esamiValidati, ArrayList<Integer> CfuInterni, String nomeStudente,ArrayList<Integer> cfuEsterni,String note) {
    	  inserisciIntestazione(nomeStudente);
    	  inserisciTabellaEsamiValidati(esamiValidati, CfuInterni, cfuEsterni, note);
    	  inserisciPiePagine();
	      document.close();    
      }
      
      private void inserisciPiePagine() {
    	  String spaces = "\n\n\n\n";
          document.add(new Paragraph(spaces));
    	  long millis=System.currentTimeMillis();  
    	  java.sql.Date date=new java.sql.Date(millis);  
    	  
    	  String piePagina = "Cordiali saluti, il PCD.\nData: " + date.toString();
    	  document.add(new Paragraph(piePagina));
      }

      private void inserisciIntestazione(String nomeStudente) {
    	  String universita = "UNIVERSITA' DEGLI STUDI DI SALERNO\nDIPARTIMENTO DI INFORMATICA";
    	  String init = "Gentile " + nomeStudente +  "\nLe comunico l'esito della sua domanda di riconoscimento carriera.";
          document.add(new Paragraph(universita));
          document.add(new Paragraph(init));
      }
      
      private void inserisciTabellaEsamiValidati(ArrayList<ValidatedExam> esamiValidati, ArrayList<Integer> cfuInterni,ArrayList<Integer> cfuEsterni, String note ) {
    	  String spaces = "\n\n\n\n";
          document.add(new Paragraph(spaces));
	      Table table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
	      int nCelle = esamiValidati.size();
	      
	      table.addHeaderCell("Esame"); //Prendere il nome esame dall arrayList
          table.addHeaderCell( "CFU validati/totali" ); //Prendere i cfuValidati e quelli non validati
          table.addHeaderCell("Modalità di convalida"); //Prendere la modalita dall'arrayList
          
          table.getHeader().setBold();
          
	      for (int i = 0; i < nCelle; i++) {
	          table.addCell(esamiValidati.get(i).getExamName()); //Prendere il nome esame dall arrayList
	          table.addCell( cfuInterni.get(i)+ " / " + cfuEsterni.get(i) ); //Prendere i cfuValidati e quelli non validati
	          table.addCell(esamiValidati.get(i).getValidationProcedure()); //Prendere la modalita dall'arrayList
	      }
	      document.add(table);
          document.add(new Paragraph(spaces));
          Font bold = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
          
          document.add(new Paragraph("Note aggiuntive").setBold());
          document.add(new Paragraph(note));
      }
}