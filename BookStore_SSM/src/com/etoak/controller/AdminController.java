package com.etoak.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etoak.bean.Book_Admin;
import com.etoak.service.AdminService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	@Autowired
	AdminService service;
	
	@RequestMapping(value="login")
	@ResponseBody
	public Map<String,String> login(String name,String password,HttpSession session){
		Map<String, String> result=new HashMap<String, String>();
		Book_Admin admin=service.getAdminByNameAndPass(name, password);
		if(admin!=null){
			session.setAttribute("admin", admin);
			result.put("result", "loginsuccess");
		}
		else{
			result.put("result", "loginfail");
		}
		return result;
	}
	@RequestMapping(value="invalidate")
	public String invalidate(HttpSession session){
		session.removeAttribute("admin");
		return "redirect:/index.jsp";
	}
}
