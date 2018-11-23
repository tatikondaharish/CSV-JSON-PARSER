package controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.json.JSONObject;

public class CSV_JSON_DataStorage {
	static Map<String, JSONObject> dataMap = new HashMap<String, JSONObject>();
	static Map<String,Set<String>> Org_DeptMap=new HashMap<String,Set<String>>();
	static Map<String,Set<String>> Dept_EmployeeMap=new TreeMap<String,Set<String>>();

	
	public static Map<String, Set<String>> getOrg_DeptMap() {
		return Org_DeptMap;
	}

	public static void setOrg_DeptMap(Map<String, Set<String>> org_DeptMap) {
		Org_DeptMap = org_DeptMap;
	}

	public static Map<String, Set<String>> getDept_EmployeeMap() {
		return Dept_EmployeeMap;
	}

	public static void setDept_EmployeeMap(Map<String, Set<String>> dept_EmployeeMap) {
		Dept_EmployeeMap = dept_EmployeeMap;
	}

	public static Map<String, JSONObject> getDataMap() {
		return dataMap;
	}

	public static void setDataMap(Map<String, JSONObject> dataMap) {
		CSV_JSON_DataStorage.dataMap = dataMap;
	}
	
	public static void printAllOrgs() {
		for (String key : Org_DeptMap.keySet()) {
			System.out.print("Org : "+key+" => "+Org_DeptMap.get(key));
		}
	}
	public static void printAllEmployees() {
		for (String key : Dept_EmployeeMap.keySet()) {
			System.out.print("Org : "+key+" => "+Dept_EmployeeMap.get(key));
		}
	}
}
