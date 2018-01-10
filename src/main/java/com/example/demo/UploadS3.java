package com.example.demo;

import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.util.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;


//In AWS i have attached the IAM Role to my ec2 instance, i hope that fixes the problem
public class UploadS3 {

	private static final String ACCESS_KEY = "AKIAIEJDBNC4JPW5NHRQ";
	private static final String SECRET_KEY = "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";
	private static final String END_POINT_URL = "https://s3.amazonaws.com";
	private static final String BUCKET = "lost-and-found-bucket";
	//private static final String IMAGE_LOCATION = "xxx";
//	private static final String S3_CACHE = "xxx"; // e.g 60
	private static AmazonS3 s3;


	public static String uploadImageToS3(MultipartFile multipartFile) throws IOException{
	    
		String fileName = multipartFile.getOriginalFilename();

	try {
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        s3 = new AmazonS3Client(credentials);
        s3.setEndpoint(END_POINT_URL);
        InputStream stream = multipartFile.getInputStream();
        byte[] contentBytes = IOUtils.toByteArray(stream);
        Long contentLength = Long.valueOf(contentBytes.length);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentLength);

        InputStream inputStream = multipartFile.getInputStream();

        fileName = System.currentTimeMillis() + "_" + fileName;
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET, fileName, inputStream, metadata);
        s3.putObject(putObjectRequest);
    }
    catch(AmazonServiceException e)
    {


    }


        return fileName;

	          }

      }


