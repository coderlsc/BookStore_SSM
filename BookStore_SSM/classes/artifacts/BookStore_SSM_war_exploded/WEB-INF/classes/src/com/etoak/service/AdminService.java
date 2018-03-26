package com.etoak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etoak.bean.Book_Admin;
import com.etoak.bean.Book_AdminExample;
import com.etoak.bean.Book_AdminExample.Criteria;
import com.etoak.dao.Book_AdminMapper;

@Service
public class AdminService {
	@Autowired
	private Book_AdminMapper adminMapper;
	
	public Book_Admin getAdminByNameAndPass(String name,String pass){
			try{
				Book_AdminExample example=new Book_AdminExample();
				Criteria criteria=example.or();
				if(name!=null&&!"".equals(name)){
					criteria.andNameEqualTo(name);
				}
				if(pass!=null&&!"".equals(pass)){
					criteria.andPasswordEqualTo(pass);
				}
				List<Book_Admin> list=adminMapper.selectByExample(example);
				if(list!=null&&list.size()!=0){
					Book_Admin admin=list.get(0);
					return admin;
				}
				return null;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}
