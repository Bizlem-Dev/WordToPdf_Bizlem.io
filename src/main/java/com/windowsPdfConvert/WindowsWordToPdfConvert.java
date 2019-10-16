package com.windowsPdfConvert;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;


public class WindowsWordToPdfConvert {

	public static void main(String[] args) {
		
		String UrlLink="http://uk.uk.bluealgo.com:8085/Attachment/555cac45-cc17-4069-9236-0cf0cf51aa84.docx";
//		String data=wordToPdfMethod(UrlLink);
		String data=checkWordToPdfApi(UrlLink);
		System.out.println("data: "+data);
		
		// /home/ubuntu/generationTomcat/apache-tomcat-8.5.41/webapps/ROOT/Attachment/
//		downloadFileFromUrl(data, "/home/ubuntu/generationTomcat/apache-tomcat-8.5.41/webapps/ROOT/Attachment/");
		
	}
	
	
	
	public static String wordToPdfMethod(String UrlLink){
		String pdfUrl="";
		try {
//			String savePath="D:\\doctiger\\aspose_lib\\docx_files\\downloadedFiles\\";
			String savePath="D:\\apache-tomcat-9.0.12\\webapps\\ROOT\\DocumentGeneration\\";
			String docxUrlWindows= downloadFileFromUrl(UrlLink, savePath);
			System.out.println(docxUrlWindows);
			
			 File inputWord = new File(docxUrlWindows);
			
			if (UrlLink.lastIndexOf("/") != -1) {
				String documentData = UrlLink.substring(UrlLink.lastIndexOf("/") + 1);
				if (documentData.contains(".docx")) {
					documentData = documentData.substring(0,documentData.indexOf(".docx"));
					 File outputFile = new File(savePath+documentData+".pdf");
					 
//					 File base = new File("D:\\doctiger\\aspose_lib\\docx_files\\downloadedFiles\\tempPdfDir");
					 File base = new File("D:\\apache-tomcat-9.0.12\\webapps\\ROOT\\DocumentGeneration\\tempPdfDir");
					 
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
			        boolean s = converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
			        outputStream.close();
			         
			        if(s==true){
			        	
//			        	pdfUrl=savePath+documentData+".pdf";
			        	pdfUrl="http://35.231.202.149:8095/DocumentGeneration/"+documentData+".pdf";
			        	
			        	/*if(base.exists()){
			        		 try{
				               WordToPdf.delete(base);
				                }catch(Exception e){
				                    e.printStackTrace();
				                }
			             }*/
			      
			        }else{
			        	pdfUrl="failed";
			        }
					 
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pdfUrl;
	}
	
	public static String downloadFileFromUrl(String UrlLink, String savePath){
		String docxUrlWindows="";
		if (UrlLink.lastIndexOf("/") != -1) {
			String documentData = UrlLink.substring(UrlLink.lastIndexOf("/") + 1);
				try (BufferedInputStream in = new BufferedInputStream(new URL(UrlLink).openStream());
						  FileOutputStream fileOutputStream =new FileOutputStream(savePath+documentData)) {
						    byte dataBuffer[] = new byte[1024];
						    int bytesRead;
						    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
						        fileOutputStream.write(dataBuffer, 0, bytesRead);
						    }
						    
						    docxUrlWindows=savePath+documentData;
						    
						} catch (Exception e) {
						    // handle exception
							e.printStackTrace();
						}
				
				//System.out.println("downloaded: "+documentData);

		}
		return docxUrlWindows;
			
	}
	
	public static String checkWordToPdfApi(String filePath) {
	      String count=null;
			try {
				//http://35.231.202.149:8095

				URL obj = new URL("http://35.231.202.149:8095/WordToPdfApi/WindowsServletWordToPdf");
				HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
				postConnection.setRequestMethod("POST");
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
					
				} 

			} catch (Exception e) {
				return count;
			}
			return count;

		}

}
