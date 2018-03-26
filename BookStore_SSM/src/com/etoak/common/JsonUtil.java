package com.etoak.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	private static ObjectMapper mapper = null;
	private static SimpleDateFormat dateFormat 
					= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static {
		//创建json对象
		mapper = new ObjectMapper();
		//json处理时间格式
		mapper.setDateFormat(dateFormat);
	}
	
	
	/**
	 * 将对象转换成json字符串
	 * @param object 对象
	 * @return
	 */
	public String toStr(Object object){
		String json = null;
		
		try {
			json = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
	/**
	 * 将json字符串转换成对象 
	 * @param json json字符串
	 * @param ref  将json字符串转换成对象的类型
	 * @return
	 */
	public <T> T toObject(String json,TypeReference<T> ref){
		T t = null;
		
		try {
			t = mapper.readValue(json, ref);
			//JsonParser
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	public static void main(String[] args) {
		
		JsonUtil ju = new JsonUtil();
		
		//1.将对象转换成json字符串
		List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("name", "张三");
		map.put("age", 100);
		map.put("date", new Date());
		
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("name", "李四");
		map1.put("age", 200);
		map1.put("date", new Date());
		
		map.put("obj", map1);
		
		
		list.add(map);
		
		System.out.println(list);
		
		String result = ju.toStr(list);
		
		System.out.println(result);
		
		//2.将json字符串转换成对象 
		
		String json = "[{\"age\":100,\"name\":\"张三\",\"obj\":{\"age\":200,\"name\":\"李四\",\"date\":\"2017-11-17 09:45:07\"},\"date\":\"2017-11-17 09:45:07\"}]";
		List<Map<String,Object>> reObj = ju.toObject(
				json, 
				new TypeReference<List<Map<String,Object>>>(){});
		
		System.out.println(reObj);
	}

}
