package CSV_JSON_FILTERS;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CSV_JSON_DataStorage;

public class Url_Filter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse httpres=(HttpServletResponse) response;
		HttpServletRequest httpreq=(HttpServletRequest) request;
		String uri=httpreq.getRequestURI();
		//String[] parts = uri.split("/");
		//String query ="-"+parts[2] +"-"+ parts[3];
		//if ((CSV_JSON_DataStorage.getOrg_DeptMap().containsKey(query)||uri.contains("/buildjson"))) {
			
			chain.doFilter(request, response);
	//	}
	//	else
		//	httpres.sendError(405, "sorry no match found in database");
	}

}
