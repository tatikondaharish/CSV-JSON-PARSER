package entity;

import java.util.HashMap;

public class Data {
	private static HashMap<String,String> headers;
	private static String body;
	public static HashMap<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(HashMap<String, String> headers) {
		Data.headers = headers;
	}
	public static String getBody() {
		return body;
	}
	public static void setBody(String body) {
		Data.body = body;
	}
	
}
