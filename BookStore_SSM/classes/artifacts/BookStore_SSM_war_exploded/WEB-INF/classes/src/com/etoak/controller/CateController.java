package com.etoak.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.etoak.bean.Category;
import com.etoak.service.CategoryService;

@Controller
@RequestMapping(value="/cate")
public class CateController {
	@Autowired
	private CategoryService cateservice;
	
	@RequestMapping(value="queryAllCate")
	@ResponseBody
	public Map<String,String> queryAllCate(){
		Map<String,String> result=new HashMap<String, String>();
		for(Category category:cateservice.getAllCates()){
			result.put(category.getId(), category.getName());
		}
		return result;
	}
	
	@RequestMapping(value="addCa")
	public String addca(Category category){
		cateservice.addCategory(category);
		return "redirect:/addca.jsp";
	}
	
	
	
	
}
