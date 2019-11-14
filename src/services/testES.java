package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class testES {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URL urlForGetRequest;
		try {
			urlForGetRequest = new URL("http://localhost:9200/accounts/person/_search");
			String readLine = null;
			HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
			conection.setRequestMethod("GET");
			int responseCode = conection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
			    BufferedReader in = new BufferedReader(
			        new InputStreamReader(conection.getInputStream()));
			    StringBuffer response = new StringBuffer();
			    while ((readLine = in .readLine()) != null) {
			        response.append(readLine);
			    } in .close();
			    // print result
			    System.out.println("JSON String Result " + response.toString());
			    //GetAndPost.POSTRequest(response.toString());
			} else {
			    System.out.println("GET NOT WORKED");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
