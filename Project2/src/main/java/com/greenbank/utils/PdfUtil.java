package com.greenbank.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.greenbank.beans.BankTransaction;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

public class PdfUtil {

	public PdfUtil() {
		super();
	}

	public void createPdf(String fileDirectory) throws FileNotFoundException {
		PdfWriter writer = new PdfWriter(fileDirectory);

		// Creating a PdfDocument
		PdfDocument pdfDoc = new PdfDocument(writer);

		// Adding a new page
		pdfDoc.addNewPage();

		// Creating a Document
		Document document = new Document(pdfDoc);

		// Closing the document
		document.close();
		System.out.println("PDF Created");
	}

	public void populateTransactionPdf(ArrayList<BankTransaction> transactions, String fileDirectory)
			throws IOException {

		PdfWriter writer = null;
		writer = new PdfWriter(fileDirectory);

		// Creating a PdfDocument object
		PdfDocument pdf = new PdfDocument(writer);

		// Creating a Document object
		Document doc = new Document(pdf);

		// Creating a table
		float[] pointColumnWidths = { 30F, 200F, 125F, 125F };
		Table table = new Table(pointColumnWidths);

		//add header
		//PdfFont bold = PdfFontFactory.createFont("resources/fonts/OpenSans-Bold.ttf", true);
		String header = "GreenBank";
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");

		doc.add(
	            new Paragraph()
	            .   setTextAlignment(TextAlignment.RIGHT)
	                .setMultipliedLeading(1)
	                .add(new Text(String.format("%s\n", header))
	                        .setFontSize(14))//.setFont(bold)
	                .add(sdf.format(new Date())));		
		
		// Add Company Material
		String para1 = "This is propriatary GreenBank Information any "
				+ "attempt to share distribute or disclose material contained "
				+ "in this document may result in civil or criminal penalties fully subject the law of United States of America.";

		String para2 = "This document contains transaction information tied to "
				+ "a customers account. It is recommended that the machine you are using is a machine provided by the bank "
				+ "Please always use secure passwords that are unique across your other accounts. "
				+ "GreenBank reserves the right to conduct checks and diagnostics on the machine being used to view "
				+ "this sensitive proprietary material.";

		// Creating Paragraphs
		Paragraph paragraph1 = new Paragraph(para1);
		Paragraph paragraph2 = new Paragraph(para2);

		// Adding paragraphs to document
		doc.add(paragraph1);
		doc.add(paragraph2);

		// Adding cells to the table

		Cell c1 = new Cell().add("ID");
		c1.setBackgroundColor(Color.LIGHT_GRAY);
		c1.setBorder(new SolidBorder(Color.BLACK, 1f, .7f));
		c1.setTextAlignment(TextAlignment.CENTER);

		Cell c2 = new Cell().add("Transaction Details");
		c2.setBackgroundColor(Color.LIGHT_GRAY);
		c2.setBorder(new SolidBorder(Color.BLACK, 1f, .7f));
		c2.setTextAlignment(TextAlignment.CENTER);

		Cell c3 = new Cell().add("Date Created");
		c3.setBackgroundColor(Color.LIGHT_GRAY);
		c3.setBorder(new SolidBorder(Color.BLACK, 1f, .7f));
		c3.setTextAlignment(TextAlignment.CENTER);

		Cell c4 = new Cell().add("Transfer Amount");
		c4.setBackgroundColor(Color.LIGHT_GRAY);
		c4.setBorder(new SolidBorder(Color.BLACK, 1f, .7f));
		c4.setTextAlignment(TextAlignment.CENTER);

		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		table.addCell(c4);

		for (BankTransaction transaction : transactions) {
			Cell cId = new Cell().add(String.valueOf(transaction.getId()));
			cId.setTextAlignment(TextAlignment.CENTER);
			table.addCell(cId);
			
			Cell cDetails = new Cell().add(transaction.getName());
			cDetails.setTextAlignment(TextAlignment.CENTER);
			table.addCell(cDetails);
			
			Cell cDate = new Cell().add(String.valueOf(transaction.getDate().getMonth()) + " "+ String.valueOf(transaction.getDate().getYear()));
			cDate.setTextAlignment(TextAlignment.CENTER);
			table.addCell(cDate);
			
			Cell cAmount = new Cell().add(String.valueOf(transaction.getBalance()));
			cAmount.setTextAlignment(TextAlignment.CENTER);
			table.addCell(cAmount);
		}

		// Adding Table to document
		doc.add(table);

		// Closing the document
		doc.close();
		System.out.println("Table created successfully..");
	}

//	public static void main(String[] args) throws MessagingException {

//
//		String fileDirectory = "C:/Users/andre/Project2/Project2/src/main/resources/accountStatement.pdf";
//		fileDirectory = "iTextHelloWorld.pdf";
//		PdfUtil pdfUtil = new PdfUtil();
//
//		ArrayList<BankTransaction> transactions = new ArrayList<BankTransaction>();
//		for(int i=0; i <=5; i++)
//		{
//			BankTransaction t=new BankTransaction();
//			t.setBalance(i*15);
//			t.setName(String.valueOf(i));
//			transactions.add(t);
//		}
//		
//		try {
//			pdfUtil.populateTransactionPdf(transactions, fileDirectory);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			pdfUtil.createPdf(fileDirectory);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
