package com.sv.todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Todo_Consumer {
	
	static String host = System.getProperty("host"); //"servicev.ct.blue.cdtapps.com"
	static String port = System.getProperty("port"); //"8008"

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    Todo_Consumer.getMethod();
	    Todo_Consumer.postMethod();
	}
	
	public static void getMethod() {
		try {
						
            URL url = new URL("http://"+host+":"+port+"/api/todos/1");//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            
            while ((output = br.readLine()) != null) 
            {
                System.out.println(output);
            }
            
            
            conn.disconnect();
            

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
	}
	
	public static void postMethod() {
		try {

			URL url = new URL("http://"+host+":"+port+"/api/todos/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = "{\"id\":2,\"task\":\"meeting\",\"done\":false}";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		 }
	}

}
