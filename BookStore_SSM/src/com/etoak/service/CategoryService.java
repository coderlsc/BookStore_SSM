package com.etoak.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etoak.bean.Category;
import com.etoak.dao.CategoryMapper;
import com.etoak.factory.SF;

@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private CategoryMapper cateMapper;
	
	public  List<Category> getAllCates(){
		return cateMapper.selectByExample(null);
	}
	
	public void addCategory(Category category){
		try{
			cateMapper.insert(category);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}

	public Category queryCateById(String categoryid) {
		try{
			Category category=cateMapper.selectByPrimaryKey(categoryid);
			return category;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
}
