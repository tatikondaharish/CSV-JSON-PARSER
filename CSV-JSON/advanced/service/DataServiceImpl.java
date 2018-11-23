package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.json.JSONObject;

import entity.Data;

public class DataServiceImpl implements DataService{
	
	private HashMap<Integer,String> dataMap=new HashMap<Integer,String>();
	private HashMap<String,List<Integer>> groupMap=new HashMap<String,List<Integer>>();
	private JSONObject jsonObj=new JSONObject();
	private List<Integer> coreCol=new ArrayList<Integer>();
	
	
	@Override
	public void getHeaders(String headerName,String headerData) {
		Data.getHeaders().put(headerName, headerData);
		
	}

	@Override
	public void getBody(String data) {
		Data.setBody(data);
		
	}

	@Override
	public void buildJson() {
		HashMap<String,String> headerMap=Data.getHeaders();
		String groupingInfo=headerMap.get("Groups");
		
			
		DataServiceImpl service=new DataServiceImpl();
		service.setGroups();
		String rawData=Data.getBody();
		Pattern.compile(";").splitAsStream(rawData).map(service::dataMapper);
		
		
		
	}
	
	public void setGroups() {
		
		HashMap<String,String> headerMap=Data.getHeaders();
		String groupingInfo=headerMap.get("Groups");
		String[] groups=groupingInfo.split(";");
		for(String group:groups) {
			String[] groupFields=group.split(",");
			List<Integer> col=new ArrayList<Integer>();
			for(int i=1;i<groupFields.length;i++) {
				col.add(Integer.parseInt(groupFields[i]));
			}
			groupMap.put(groupFields[0],col);
		}
		
	}
	
	
	public JSONObject dataMapper(String dataField) {
		
		HashMap<String,String> headerMap=Data.getHeaders();
		String fieldInfo=headerMap.get("Fields");
		String[] dataName=dataField.split(",");
		String[] fieldName=fieldInfo.split(",");
		
		for(int i=0;i<fieldName.length;i++) {
			dataMap.put(i, fieldName[i]+"_"+dataName[i]);
		}
		                             
		for(Map.Entry<String,List<Integer>> entry : groupMap.entrySet()) {
			List<Integer> col=entry.getValue();
			String jsonKey=entry.getKey();
			for(Integer i:col) {
				String data=dataMap.get(i);
				String[] fields=data.split("_");
				jsonObj.put(fields[0], fields[1]);
			}
		}

		return null;
	}

}
