package com.wordtopdf;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordToPdf {

	public static void main(String[] args) {

		String inputDocxPathPlusExtension="D:\\doctiger\\aspose_lib\\docx_files\\downloadedFiles\\japanese.docx";
		String outputPathPulsExtension="D:\\doctiger\\aspose_lib\\docx_files\\downloadedFiles\\japanese.pdf";
		
		String data=wordToPdfMethod(inputDocxPathPlusExtension, outputPathPulsExtension);
		System.out.println(data);
		
		/*String inputDocxPathPlusExtension="/home/ubuntu/generationTomcat/apache-tomcat-8.5.41/webapps/ROOT/Attachment/japanese.docx";
		String outputPathPulsExtension="/home/ubuntu/generationTomcat/apache-tomcat-8.5.41/webapps/ROOT/Attachment/japanese.docx";
		
		try{
		JSONObject d=new JSONObject();
		d.put("inputFile", inputDocxPathPlusExtension);
		d.put("outputFile", outputPathPulsExtension);
		
		  String data= getResponseWordToPdfApi(d.toString());
		  System.out.println(data);
		  
		}catch(Exception e){
			e.printStackTrace();
		}*/
		
	}
	
public static String wordToPdfMethod(String inputDocxPathPlusExtension, String outputPathPulsExtension){
		String returnData="";
		try {
			
			 File inputWord = new File(inputDocxPathPlusExtension);
			 File outputFile = new File(outputPathPulsExtension);
			 
//			 File base = new File("/home/ubuntu/generationTomcat/apache-tomcat-8.5.41/webapps/ROOT/Attachment/tempPdfDir");
			 
			 File base = new File("D:\\doctiger\\aspose_lib\\docx_files\\downloadedFiles\\tempPdfDir");
			 
		        if (!base.exists()) {
		            if (base.mkdir()) {
		               // System.out.println("Directory is created!");
		            } else {
		               // System.out.println("Failed to create directory!");
		            }
		        }
			 InputStream docxInputStream = new FileInputStream(inputWord);
	         OutputStream outputStream = new FileOutputStream(outputFile);
			 
	         //IConverter converter = LocalConverter.builder().baseFolder(base).workerPool(20, 25, 1, TimeUnit.SECONDS).processTimeout(5, TimeUnit.SECONDS).build();
	         IConverter converter = LocalConverter.builder().baseFolder(base).workerPool(20, 25, 1, TimeUnit.SECONDS).processTimeout(5, TimeUnit.SECONDS).build();
	       /* boolean s = converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();*/
	         boolean s = converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
	         outputStream.close();
	         
	        if(s==true){
	        	returnData="success";
	        	if(base.exists()){
	        		 try{
		               delete(base);
		                }catch(Exception e){
		                    e.printStackTrace();
		                }
	             }
	      
	        }else{
	        	returnData="failed";
	        }
	        
		} catch (Exception e) {
			return e.getMessage();
		}
		
		 return returnData;
		
	}
public static void delete(File file)
    	{
 
    	try {
			if(file.isDirectory()){
 
				//directory is empty, then delete it
				if(file.list().length==0){
					
				   file.delete();
				  // System.out.println("Directory is deleted : "  + file.getAbsolutePath());
			                                        
					
				}else{
					
				   //list all the directory contents
				   String files[] = file.list();
    
				   for (String temp : files) {
				      //construct the file structure
				      File fileDelete = new File(file, temp);
					 
				      //recursive delete
				     delete(fileDelete);
				   }
					
				   //check the directory again, if empty then delete it
				   if(file.list().length==0){
			   	     file.delete();
				   //  System.out.println("Directory is deleted : "  + file.getAbsolutePath());
			                                         
				   }
				}
				
			}else{
				//if file, then delete it
				file.delete();
				//System.out.println("File is deleted : " + file.getAbsolutePath());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

public static String getResponseWordToPdfApi(String filePath) {
    String count=null;
		try {

			URL obj = new URL("http://bizlem.io:8085/WordToPdfApi/WordToPdfServletApi");
			HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
			postConnection.setRequestMethod("POST");
//			postConnection.setRequestProperty("Content-Type", "application/json");
			postConnection.setDoOutput(true);
			OutputStream os = postConnection.getOutputStream();
			os.write(filePath.getBytes());
			os.flush();
			os.close();
			int responseCode = postConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
					count=response.toString();
				}
				in.close();
			} else {
				System.out.println("POST NOT WORKED");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;

	}



}
