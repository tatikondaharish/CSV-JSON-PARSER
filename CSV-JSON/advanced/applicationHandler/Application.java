package applicationHandler;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import service.DataServiceImpl;

public class Application implements Servlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		DataServiceImpl service = new DataServiceImpl();

		// Get headers from the Request
		Enumeration<String> headerNames = httpReq.getHeaderNames();

		// Loop through the enumeration and get the HeaderName and HeaderBody
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			service.getHeaders(headerName, httpReq.getHeader(headerName));
		}

		service.getBody(httpReq.getReader().readLine());

		service.buildJson();

		try {

		} catch (Exception e) {

		}

	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
