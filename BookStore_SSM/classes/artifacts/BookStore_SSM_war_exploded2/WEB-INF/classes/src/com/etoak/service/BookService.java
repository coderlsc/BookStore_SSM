package com.etoak.service;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etoak.bean.Book;
import com.etoak.bean.BookExample;
import com.etoak.bean.BookExample.Criteria;
import com.etoak.dao.BookMapper;

@Service
@Transactional
public class BookService {
	
	@Autowired
	private BookMapper bookMapper;
	
			//增加
			public String addBook(Book book){
				try{
					bookMapper.insert(book);
					return book.getId();
				}catch(Exception e){
					e.printStackTrace();
					return null;
				}
			}
			//删除
			public void  deleBook(Book book){
				try{
					bookMapper.deleteByPrimaryKey(book.getId());
				}catch(Exception e){
					e.printStackTrace();
				}
			}	
	
			//查找   条数
		public int getCount(String bookname,String categoryid) {
			try{
				BookExample example=new BookExample();
				Criteria criteria=example.or();
				if(bookname!=null&&!"".equals(bookname)){
					criteria.andNameLike("%"+bookname+"%");
				}
				if(categoryid!=null&&!"".equals(categoryid)&&!"all".equals(categoryid)){
					criteria.andCategoryidEqualTo(categoryid);
				}
				int count= bookMapper.countByExample(example);
				return count;
			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
		}
		public List<Book> getAllBooks(int index,int count){
			try{
				BookExample example=new BookExample();
				example.setIndex(index);
				example.setCount(count);
				List<Book> data= bookMapper.selectByExample(example);
				return data;
			}catch(Exception e){
				e.printStackTrace();
				return null;
				}
			}
		
		public List<Book> searchBook(String bookname, String categoryid,int index,int count) {
			try{
				BookExample example=new BookExample();
				Criteria criteria=example.or();
				if(bookname!=null&&!"".equals(bookname)){
					criteria.andNameLike("%"+bookname+"%");
				}
				if(categoryid!=null&&!"".equals(categoryid)&&!"all".equals(categoryid)){
					criteria.andCategoryidEqualTo(categoryid);
				}
				example.setIndex(index);
				example.setCount(count);
				 List<Book> data=bookMapper.selectByExample(example);
				 return data;
			}catch(Exception e){
				e.printStackTrace();
				return null;
				}
			}
		public Book getBookById(String id){
			try{
				Book book=bookMapper.selectByPrimaryKey(id);
				return book;
			}catch(Exception e){
				e.printStackTrace();
				return null;
				}
			}
		public boolean downorup(Book book){
			try{
				if(book.getStatus().equals("1")){
					book.setStatus("0");
				}
				else{
					book.setStatus("1");
				}
				boolean result= bookMapper.updateByExampleSelective(book,null)==1;
				return result;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
		public boolean update(Book book){
			try{
				BookExample example=new BookExample();
				example.or().andIdEqualTo(book.getId());
				boolean result= bookMapper.updateByExampleSelective(book,example)==1;
				return result;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
	
	
	
	
}
