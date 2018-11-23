package controller;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class CSV_JSON_DataFetcher {
	static String query;
	public JSONArray getemployees() {
		try {
			System.out.println("query is " + query);
			return getEmployees(query);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String getQuery() {
		return query;
	}

	public static void setQuery(String query) {
		CSV_JSON_DataFetcher.query = query;
	}

	private JSONArray getEmployees(String query) {
			System.out.println(CSV_JSON_DataStorage.getOrg_DeptMap().size());
			Set<String> employees = new TreeSet<String>();
			JSONArray jsonarr=new JSONArray();
			try{
				if (CSV_JSON_DataStorage.getOrg_DeptMap().containsKey(query)) {
				System.out.println("found query");
				Set<String> departments = new HashSet<String>();
				departments = CSV_JSON_DataStorage.getOrg_DeptMap().get(query);
				System.out.println("departments are " + departments.toString());
				for (String depkey : departments) {
					employees.addAll(CSV_JSON_DataStorage.getDept_EmployeeMap().get(depkey));
					for (String employee : CSV_JSON_DataStorage.getDept_EmployeeMap().get(depkey)) {
						JSONObject json = new JSONObject();
						String[] employeerecord = employee.split("-");
						for (int j : DataControllerImplementor.getCoreDataCols()) {
							if(DataControllerImplementor.getHeaders().get(j).equals("name"))
								json.put(DataControllerImplementor.getHeaders().get(j), employeerecord[1]);
							else if(DataControllerImplementor.getHeaders().get(j).equals("employeeid"))
								json.put(DataControllerImplementor.getHeaders().get(j), employeerecord[2]);
							else if(DataControllerImplementor.getHeaders().get(j).equals("role"))
								json.put(DataControllerImplementor.getHeaders().get(j), employeerecord[3]);
						}
						jsonarr.put(json);
					}
				}
				System.out.println("employees are " + jsonarr.toString());

				return jsonarr;
			} 
				else {
					return null;
				}
					
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
