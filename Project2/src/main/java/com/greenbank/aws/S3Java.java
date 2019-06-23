package com.greenbank.aws;


import com.greenbank.beans.BankTransaction;
import com.greenbank.utils.PdfUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.transfer.Download;


import org.springframework.stereotype.Service;

@Service
public class S3Java {

	private final String fileDirectory = System.getProperty("user.dir")+"\\src\\main\\resources\\accountStatement.pdf";

    public static void main(String[] args)throws Exception {
    	
//    	S3Java s3 = new S3Java();


    	//createSimpleBucket(2);
//        
//        String bucketName = "statement"+1;
//        String filePath = "accountStatement.pdf"+"."+13+"."+2;
//		ArrayList<BankTransaction> transactions = new ArrayList<BankTransaction>();
//		for(int i=0; i <=5; i++)
//		{
//			BankTransaction t=new BankTransaction();
//			t.setBalance(i*15);
//			t.setName(String.valueOf(i));
//			transactions.add(t);
//		}
//		populateSimpleBucket(transactions, bucketName, filePath);
//		
//    	s3.getSimpleObject("statement1","accountStatement.pdf.13.2");


    }


    public File getSimpleObject(String bucketName, String filePath){
  
    	System.out.println("Getting Bucket Object");
    	
    	BasicAWSCredentials awsCreds = new BasicAWSCredentials(Credentials.access_key_id, Credentials.secret_access_key);
    	AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
        TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(s3Client).build();
        
        
        File localFile = new File(fileDirectory);
        try {
            Download transfer = transferManager.download(bucketName, filePath, localFile);
            transfer.waitForCompletion();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            //localFile.delete();
            return null;
        }
        catch( InterruptedException e) {
        	System.out.println("Download Thread Terminated");
            //localFile.delete();
            return null;
        }
        finally {
			//transferManager.shutdownNow();
		}
    	
    	if(localFile.exists() && localFile.canRead()) {
    		return localFile;
    	}
    	else return null;
	}

    public Bucket createSimpleBucket(int accountNumber) throws Exception{

    	System.out.println("Creating Bucket");
    	
    	BasicAWSCredentials awsCreds = new BasicAWSCredentials(Credentials.access_key_id, Credentials.secret_access_key);
    	AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
        
    	Bucket b = null;
    	String bucketName = "statement"+accountNumber;
    	if (s3Client.doesBucketExistV2(bucketName)) {
    	    System.out.format("Bucket %s already exists.\n", bucketName);
    	    b = getBucket(bucketName);
    	} else {
    	    try {
    	        b = s3Client.createBucket(bucketName);
    	    } catch (AmazonS3Exception e) {
    	        System.err.println(e.getErrorMessage());
    	    }
    	}
    	return b;
    }

	public File populateSimpleBucket(ArrayList<BankTransaction> transactions, String bucketName, String filePath) throws Exception {

		System.out.println("Populating Bucket");
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(Credentials.access_key_id, Credentials.secret_access_key);
    	AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
        TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(s3Client).build();


        //create populate pdf
        PdfUtil pdfUtil = new PdfUtil();
        pdfUtil.createPdf(fileDirectory);
        pdfUtil.populateTransactionPdf(transactions, fileDirectory);
        
        //UpploadFile
        File localFile = new File(fileDirectory);
        try {
            Upload transfer = transferManager.upload(bucketName, filePath, localFile);
            transfer.waitForCompletion();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            return null;
        }
        catch( InterruptedException  e) {
        	System.out.println("Upload Thread Terminated");
            return null;
        }
		finally {
			transferManager.shutdownNow();
			return localFile;
		}

		
	}

	
	
	
    private static Bucket getBucket(String bucket_name) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        Bucket named_bucket = null;
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket b : buckets) {
            if (b.getName().equals(bucket_name)) {
                named_bucket = b;
            }
        }
        return named_bucket;
    }

}

 