package entity;

public class RequestParser {
static String[] HeaderFields;
static String RequestBody;
	public static void getHeaders(String requestHeaders) {
		String[] HeaderFields = requestHeaders.split(",");
	}

}
