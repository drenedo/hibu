package us.renedo.find.hibu.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;


public class SyncRead {
	public String getUrl(String url,Proxy p){
		BufferedReader in = null;
		String returned = VarPool.BLANK;
		try{
			URL website = new URL(url);
			
	        URLConnection connection = null; 
	        if(p!=null){
	        	connection = website.openConnection(p);
	        }else{
	        	connection = website.openConnection();
	        }
	        connection.setReadTimeout(15000);
	        
	        in = new BufferedReader(
	                                new InputStreamReader(
	                                    connection.getInputStream()));
	
	        StringBuilder response = new StringBuilder();
	        String inputLine;
	
	        while ((inputLine = in.readLine()) != null) 
	            response.append(inputLine);
	
	        returned = response.toString();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
        	if(in!=null){
		        try {
		        	in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
		}
		
        return returned;
	}
}
