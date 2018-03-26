package com.etoak.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.etoak.bean.Book;
import com.etoak.bean.BookPic;
import com.etoak.page.Page;
import com.etoak.service.BookPicService;
import com.etoak.service.BookService;
import com.etoak.service.CategoryService;
import com.fasterxml.jackson.annotation.JsonFormat;


@Controller
@RequestMapping(value="/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	@Autowired
	private CategoryService cateService;
	
	@Autowired
	private BookPicService picService;
	
	/*					前端传过来的值：
	 * 					currentPage:msg.pageNumber,
    					pageSize:msg.pageSize,
    					name:$("#name").val(),
    					categoryid:$("#cate").val()
	 * 
	 * */
	@RequestMapping(value="queryBooks")
	@ResponseBody
	public Page<Book> queryBooks(Integer currentPage,
			Integer pageSize,
			String name,
			String categoryid){
		System.out.println(name);
		Page<Book> page=new Page<Book>();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotal(bookService.getCount(name, categoryid));
		int start=page.getStart();
		List<Book> rows=bookService.searchBook(name, categoryid, start,pageSize);
		for(Book book:rows){
			System.out.println(book.getCategoryid());
			System.out.println(book.getPublishdate());
			System.out.println(book.getDateStr());
			book.setCategory(cateService.queryCateById(book.getCategoryid()));
		}
		page.setRows(rows);
		return  page;
	}
	
	@RequestMapping(value="queryBook")
	@ResponseBody
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd")  
	public Book queryBook(String bookid){
		System.out.println(bookid);
		Book book=bookService.getBookById(bookid);
		List<BookPic> pics=picService.getPicsByBookId(book.getId());
		book.setPics(pics);
		return  book;
	}
	
	@RequestMapping(value="update")
	public String update(Book book){
		System.out.println(book.getName()+"=============");
		bookService.update(book);
		return  "redirect:/booklist2.jsp";
	}
	
	@RequestMapping(value="deleteBook")
	@ResponseBody
	public Map<String, String> deleteBook(String ids){
		System.out.println(ids+"=============");
		Map<String,String> result=new HashMap<String, String>();
		String[] bid=ids.split("-");
		for(String id:bid){
			for(BookPic pic:picService.getPicsByBookId(id)){
				picService.deleBookPic(pic);
			}
			bookService.deleBook(bookService.getBookById(id));
		}
		result.put("result", "delesuccess");
		return  result;
	}
	@RequestMapping(value="/addBook")
	public String addBook(
			@RequestParam(value="pic") MultipartFile[] pic,
			HttpSession session,
			HttpServletRequest request,Book book) throws IOException{
		System.out.println(book.getName()+"====================");
		//添加图书
		String bookid=bookService.addBook(book);
		//上传图片
		String fm=request.getParameter("fm");
		System.out.println("封面的索引值是"+fm);
		if(pic.length!=0&&pic!=null){
			int i=0;
			for(MultipartFile file:pic){
				System.out.println("originalName====》"+file.getOriginalFilename());
				System.out.println("name=====>"+file.getName());
				String name=file.getName();
				String originalName=file.getOriginalFilename();
				String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
				String newname=UUID.randomUUID().toString()+"."+suffix;
				String path=request.getServletContext().getRealPath("/image/"+newname);
				String savepath="/image/"+newname;
				System.out.println("保存路径是："+path);
				InputStream is=file.getInputStream();
				OutputStream os=new FileOutputStream(new File(path));
				int length=0;
				byte[] data=new byte[1024<<3];
				while((length=is.read(data))!=-1){
					os.write(data,0, length);
				}
				os.close();
				is.close();
				//持久化到数据库中
				BookPic bookPic=new BookPic();
				bookPic.setBookid(bookid);
				bookPic.setFm(i==(Integer.parseInt(fm))?"1":"0");
				bookPic.setSavepath(savepath);
				bookPic.setShowname(originalName);
				picService.addBookPic(bookPic);
				i++;
			}
		}
				//重定向到添加图书页面 
				return "redirect:/addbook.jsp";
		
	}
}
