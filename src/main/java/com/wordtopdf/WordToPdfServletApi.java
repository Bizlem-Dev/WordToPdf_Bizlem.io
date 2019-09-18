package com.wordtopdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class WordToPdfServletApi
 */
public class WordToPdfServletApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WordToPdfServletApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		try {
			
			BufferedReader br = request.getReader();
			String line = "";
			StringBuffer sb = new StringBuffer();
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			
			String fileData=sb.toString();

			if (!isNullString(fileData)) {

				 boolean checkJsonValid=isJSONValid(fileData);
				if(checkJsonValid){
				
				String input="";
				String output="";
				
				JSONObject allData=new JSONObject(fileData);
				if(allData.has("inputFile")){
					input=allData.getString("inputFile");
				}if(allData.has("outputFile")){
					output=allData.getString("outputFile");
				}
				if( !isNullString(input) && !isNullString(output) ){
					String dataReturn = WordToPdf.wordToPdfMethod(input, output);
					out.println(dataReturn);
				}else{
					out.println("Input And Output Mandatory Field Cant't Be Blank.");
				}
				
				
			}else{
				out.println("Input Json Is Not Valid.");
			}
			} else {
				out.println("Input And Output Mandatory Required.");
			}

		} catch (Exception e) {
			e.printStackTrace(out);
		}

	}

	public static boolean isNullString(String p_text) {
		if (p_text != null && p_text.trim().length() > 0 && !"null".equalsIgnoreCase(p_text.trim())) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isJSONValid(String test) {
		try {
			new JSONObject(test);
		} catch (JSONException ex) {
			
			try {
				new JSONArray(test);
			} catch (JSONException ex1) {
				return false;
			}
		}
		return true;
	}

}
