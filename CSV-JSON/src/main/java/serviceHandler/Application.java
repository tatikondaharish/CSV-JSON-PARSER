package serviceHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;

import controller.CSV_JSON_DataFetcher;
import controller.DataControllerImplementor;
import entity.RequestParser;
public class Application implements Servlet{
	public void init(ServletConfig config) throws ServletException {
	
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("Started");
	//	CSV_JSON_DataFetcher DataFetcher=new CSV_JSON_DataFetcher();
		PrintWriter printer=res.getWriter();
		HttpServletRequest httpreq=(HttpServletRequest) req;
		try{
		//	if(uri.contains("/buildjson")) {
			DataControllerImplementor.setGroups(httpreq.getHeader("Grouping"));
			DataControllerImplementor.setReqheaders(httpreq.getHeader("Fields"));
			BufferedReader RequestReader=req.getReader();
			DataControllerImplementor.setReqBody(RequestReader.readLine());
			JSONArray JsonOrgArray=DataControllerImplementor.BuildJson();
			printer.write("Final json is ");
			printer.write(JsonOrgArray.toString());
		
		//	}
		//	else  {
				
			//	String[] parts = uri.split("/");
			//	CSV_JSON_DataFetcher.setQuery("-"+parts[2] +"-"+ parts[3]); 
			//	String ans=(DataFetcher.getemployees()==null)?"No match found":DataFetcher.getemployees().toString();
			//	printer.write(ans);
				
		//	}
		}
		catch(Exception e) {
			res.setContentType("Incorrect uri");
			
		}
	}
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
