package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import Servlet_Projects.CSV_JSON.InternalCalls;
import entity.GroupConfig;

public class DataControllerImplementor {
	static List<Integer> allGroupedCols = new ArrayList<Integer>();
	static List<GroupConfig> groupConfigs = new ArrayList<GroupConfig>();
	static List<String> Headers = new ArrayList<String>();
	static String ReqBody;
	public static String getReqBody() {
		return ReqBody;
	}
	public static void setReqBody(String reqBody) {
		ReqBody = reqBody;
	}
	static Set<Integer> coreDataCols = new HashSet<Integer>();
	static String Groups;
	static String reqheaders;
	static JSONObject json = new JSONObject();

	public static List<String> getHeaders() {
		return Headers;
	}

	public static void setHeaders(List<String> headers) {
		Headers = headers;
	}

	public static Set<Integer> getCoreDataCols() {
		return coreDataCols;
	}

	public static void setCoreDataCols(Set<Integer> coreDataCols) {
		DataControllerImplementor.coreDataCols = coreDataCols;
	}

	public static List<Integer> getAllGroupedCols() {
		return allGroupedCols;
	}

	public static void setAllGroupedCols(List<Integer> allGroupedCols) {
		DataControllerImplementor.allGroupedCols = allGroupedCols;
	}

	public static List<GroupConfig> getGroupConfigs() {
		return groupConfigs;
	}

	public static void setGroupConfigs(List<GroupConfig> groupConfigs) {
		DataControllerImplementor.groupConfigs = groupConfigs;
	}

	public static JSONArray BuildJson() throws IOException {
		System.out.println("Starting BuildJson");
		InternalCalls Internalcall = new InternalCalls();
		Internalcall.getAllGroups(Groups);
		Internalcall.setHeaders(reqheaders);
		String[] Records = ReqBody.split(";");
		for (String record : Records) {
			String[] InputFields = record.split(",");
			process(InputFields, Headers);
		}
		CSV_JSON_DataStorage.printAllOrgs();
		CSV_JSON_DataStorage.printAllEmployees();
		JSONArray JsonArr = Internalcall.FinalJson();

		return JsonArr;
	}

	public static String getReqheaders() {
		return reqheaders;
	}

	public static void setReqheaders(String reqheaders) {
		DataControllerImplementor.reqheaders = reqheaders;
	}

	public static String getGroups() {
		return Groups;
	}

	public static void setGroups(String groups) {
		Groups = groups;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	private static void process(String[] fields, List headers) {
		JSONArray jsonArray = new JSONArray();
		boolean first=true;
		String Org_Deptmapkey="";
		String Org_Deptmapvalue="";
		for (int i = 0; i < groupConfigs.size(); i++) {
			GroupConfig groupConfig = groupConfigs.get(i);
			String key = String.valueOf(i);
			for (int j : groupConfig.getGroupedCols()) {
				key = key + "-" + fields[j];
				if(first)
					Org_Deptmapkey=(Org_Deptmapkey+"-"+fields[j]);
				else
					Org_Deptmapvalue=Org_Deptmapvalue+"-"+fields[j];
				
			}
			first=false;
			if (CSV_JSON_DataStorage.getDataMap().containsKey(key)) {
				json = CSV_JSON_DataStorage.getDataMap().get(key);
				jsonArray = json.getJSONArray(groupConfig.getGroupName());
			} 
			else {
				JSONObject json = new JSONObject();
				jsonArray.put(json);
				
				for (int j : groupConfig.getGroupedCols()) 
					json.put((String) headers.get(j), fields[j]);
				jsonArray = new JSONArray();
				json.put(groupConfig.getGroupName(), jsonArray);
				CSV_JSON_DataStorage.getDataMap().put(key, json);

			}

			if (i == (groupConfigs.size() - 1)) {
				if(CSV_JSON_DataStorage.getOrg_DeptMap().containsKey(Org_Deptmapkey)) {
					if(!CSV_JSON_DataStorage.getOrg_DeptMap().get(Org_Deptmapkey).contains(Org_Deptmapvalue))
						CSV_JSON_DataStorage.getOrg_DeptMap().get(Org_Deptmapkey).add(Org_Deptmapvalue);
						
				}
				else {
					Set<String> c=new HashSet<String>();
					c.add(Org_Deptmapvalue);
					CSV_JSON_DataStorage.getOrg_DeptMap().put(Org_Deptmapkey,c);
				}
				InternalCalls obj = new InternalCalls();
				JSONObject jsonCore = obj.buildCoreData(fields, headers, coreDataCols,Org_Deptmapvalue);
				jsonArray.put(jsonCore);
			}
			
		}
		
	}

}
