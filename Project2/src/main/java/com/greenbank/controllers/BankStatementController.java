package com.greenbank.controllers;

import java.io.ByteArrayOutputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenbank.aws.S3Java;
import com.greenbank.beans.BankTransaction;
import com.greenbank.beans.Customer;
import com.greenbank.beans.LoginResponsePayload;
import com.greenbank.data.BankTransactionDao;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/accountstatement")
public class BankStatementController {

	@Autowired
	private BankTransactionDao accountTransactionDAO;
	
	@Autowired
	private S3Java s3;
	
	//http://localhost:8080/Project2/statement/1/2018/6 
	@GetMapping("{id}/{year}/{month}")
	public ResponseEntity<byte[]> getTransactionsByAccountId(
			@PathVariable("id") int id, @PathVariable("year") int year, @PathVariable("month") int month, HttpSession session)
	{
		//check if logged in
		Customer customer = ((LoginResponsePayload)session.getAttribute("user")).getCustomer();
		if(customer == null) return null;
		/////
		byte[] statement = null;
		FileInputStream inputStream = null;
		
		String filePath = "accountStatement.pdf"+"."+year+"."+month;
		String bucketName = "statement"+""+ id;

//		String bucketName ="statement1";
//		String filePath = "accountStatement.pdf.13.5";

	    try 
	    {
	        inputStream = new FileInputStream(s3.getSimpleObject(bucketName, filePath));
	        statement = readPdf(inputStream);
	    } 
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }

		//generate if null -> upload and return generated
		if(statement == null)
		{	
			System.out.println("S3 object isnt in the cloud");
///////////////EDIT TO fit date criteria at some point 
			ArrayList<BankTransaction> transactions = accountTransactionDAO.getTransactionsByAccountId(id);
			
			//create pdf and upload it
			try {
				inputStream = new FileInputStream(s3.populateSimpleBucket(transactions, bucketName, filePath));
				statement = readPdf(inputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    
		//return the pdf
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_PDF);
	    
	    // Here you have to set the actual filename of your pdf
	    String filename = "statement.pdf";
	    headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = new ResponseEntity<>(statement, headers, HttpStatus.OK);
	    return response;
	}
	
	public static byte[] readPdf(InputStream stream) throws IOException
	{
	    byte[] buffer = new byte[8192];
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();

	    int bytesRead;
	    while ((bytesRead = stream.read(buffer)) != -1)
	    {
	        baos.write(buffer, 0, bytesRead);
	    }
	    return baos.toByteArray();
	}
}

