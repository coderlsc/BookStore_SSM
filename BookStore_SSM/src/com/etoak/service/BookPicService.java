package com.etoak.service;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etoak.bean.BookPic;
import com.etoak.bean.BookPicExample;
import com.etoak.dao.BookPicMapper;
import com.etoak.factory.SF;

@Service
@Transactional
public class BookPicService {
	@Autowired
	private BookPicMapper picMapper;
	
	//增加
			public void addBookPic(BookPic pic){
				try{
					picMapper.insert(pic);
				}catch(Exception e){
					e.printStackTrace();
					}
				}
			//删除
			public void  deleBookPic(BookPic bookPic){
				try{
					picMapper.deleteByPrimaryKey(bookPic.getId());
				}catch(Exception e){
					e.printStackTrace();
				}
			}	
	
	//查找   条数
		public int getCount(String bookid) {
			try{
				BookPicExample example=new BookPicExample();
				example.or().andBookidEqualTo(bookid);
				int count= picMapper.countByExample(example);
				return count;
			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
		}
		public List<BookPic> getAllPics(){
			try{
				BookPicExample example=new BookPicExample();
				List<BookPic> data= picMapper.selectByExample(example);
				return data;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		public List<BookPic> getPicsByBookId(String id){
			try{
				BookPicExample example=new BookPicExample();
				example.or().andBookidEqualTo(id);
				List<BookPic> data= picMapper.selectByExample(example);
				return data;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		public BookPic getBookPicById(String id){
			try{
				BookPic pic=picMapper.selectByPrimaryKey(id);
				return pic;
			}catch(Exception e){
				e.printStackTrace();
				return null;
				}
			}
		public boolean update(BookPic pic){
			try{
				BookPicExample example=new BookPicExample();
				example.or().andIdEqualTo(pic.getId());
				boolean result= picMapper.updateByExampleSelective(pic,example)==1;
				return result;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
		public void deleBookPicById(String id) {
			try{
				picMapper.deleteByPrimaryKey(id);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	
	
}
