package controller;

import java.util.ArrayList;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.UnitValue;

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
      
     public void inserisciPiePagine() {
    	  String spaces = "\n\n\n\n";
          document.add(new Paragraph(spaces));
    	  long millis=System.currentTimeMillis();  
    	  java.sql.Date date=new java.sql.Date(millis);  
    	  
    	  String piePagina = "Cordiali saluti, il PCD.\nData: " + date.toString();
    	  document.add(new Paragraph(piePagina));
      }

      public void inserisciIntestazione(String nomeStudente) {
    	  String universita = "UNIVERSITA' DEGLI STUDI DI SALERNO\nDIPARTIMENTO DI INFORMATICA";
    	  String init = "Gentile " + nomeStudente +  "\nLe comunico l'esito della sua domanda di riconoscimento carriera.";
          document.add(new Paragraph(universita));
          document.add(new Paragraph(init));
      }
      

      public void inserisciTabellaEsamiValidati(ArrayList<ValidatedExam> esamiValidati, ArrayList<Integer> CfuInterni,ArrayList<Integer> cfuEsterni, String note ) {


    	  String spaces = "\n\n\n\n";
          document.add(new Paragraph(spaces));
	      Table table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
	      int nCelle = esamiValidati.size();
	      for (int i = 0; i < nCelle; i++) {
	          table.addCell(esamiValidati.get(i).getExamName()); //Prendere il nome esame dall arrayList
	          table.addCell( cfuInterni.get(i)+ " / " + cfuEsterni.get(i) ); //Prendere i cfuValidati e quelli non validati
	          System.out.println(esamiValidati.get(i).getValidationProcedure());
	          table.addCell(esamiValidati.get(i).getValidationProcedure()); //Prendere la modalita dall'arrayList
	      }
	      document.add(table);
          document.add(new Paragraph(spaces));
          document.add(new Paragraph(note));
      }
}