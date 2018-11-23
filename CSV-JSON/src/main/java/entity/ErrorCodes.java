package entity;

public enum ErrorCodes {
	DUPLICATE_REQUEST("E101","This is a duplicate request",454),
	INCORRECT_HEADERS("E102","Headers are invalid",412),
	INCORRECT_DATA("E103","This is a duplicate request",400),
	PROCESSING_FAILURE("E104","Processing Failure",500),
	PARSING_EXCEPTION("I105","parsing failure");
	
	String appCode;
	String message;
	int httpStatusCode;
	
	ErrorCodes(String appCode, String message, int httpStatusCode) {
		this.appCode = appCode;
		this.message = message;
		this.httpStatusCode = httpStatusCode;
	}
	
	ErrorCodes(String appCode, String message) {
		this.appCode = appCode;
		this.message = message;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}	

	

}
