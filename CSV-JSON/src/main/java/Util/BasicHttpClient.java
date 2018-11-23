package Util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;

public class BasicHttpClient {
	
	public static void main(String args[]) throws Exception{
		
		BasicHttpClient client = new BasicHttpClient();
		client.callPost("http://localhost:8080/jersey-1/srvc/ping", new HashMap<String,String>(),"abcd");
		
	}
	
	public void callGet(String url) throws Exception {
		
		URL myurl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "*/*");
		conn.setRequestProperty("Content-Type", "text/plain");
		conn.connect();
		
		InputStream inStream  = conn.getInputStream();
		int respCode = conn.getResponseCode();
		
		String respData = IOUtils.toString(inStream);
		
		System.out.println("Response Code : "+respCode+" data : "+respData);
		
	}
	
	public String callPost(String url,Map<String,String> headers,String postData) throws Exception {
		
		URL myurl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		
		for (Entry<String,String> header : headers.entrySet()) {
			conn.setRequestProperty(header.getKey(), header.getValue());
		}
		
		conn.connect();
		
		conn.getOutputStream().write(postData.getBytes());
		int respCode = conn.getResponseCode();
		String respData="";
		if (respCode >= 200 && respCode < 300) {
			InputStream inStream  = conn.getInputStream();
			 respData = IOUtils.toString(inStream);
		}
		
		System.out.println("Response Code : "+respCode+" Response Msg : "+conn.getResponseMessage()+" data : "+respData);
		
		conn.disconnect();
		
		return respData;
		
	}

}
