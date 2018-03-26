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

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.etoak.bean.BookPic;
import com.etoak.service.BookPicService;

@Controller
@RequestMapping(value="/pic")
public class BookPicController {
	@Autowired
	private BookPicService picService;
	
	@RequestMapping(value="delePic")
	@ResponseBody
	public Map<String, String> delePic(String pid){
		String[] ids=pid.split("-");
		for(String id:ids){
			picService.deleBookPicById(id);
		}
		Map<String, String> result=new HashMap<String, String>();
		result.put("result", "success");
		return result;
	}
	
	@RequestMapping(value="toFm")
	@ResponseBody
	public Map<String, String> toFm(String pid,String bookid){
		List<BookPic> pics=picService.getPicsByBookId(bookid);
		for(BookPic pic:pics){
			if(!pic.getId().equals(pid)){
				pic.setFm("0");
				picService.update(pic);
			}
			if(pic.getId().equals(pid)){
				pic.setFm("1");
				picService.update(pic);
			}
		}
		Map<String, String> result=new HashMap<String, String>();
		result.put("result", "updateSuccess");
		return result;
	}
	
	@RequestMapping(value="uploadPic")
	@ResponseBody
	public Map<String, String> uploadPic(HttpServletRequest request,String bookid,MultipartFile pic) throws IOException{
		System.out.println("originalName====》"+pic.getOriginalFilename());
		System.out.println("name=====>"+pic.getName());
		String name=pic.getName();
		String originalName=pic.getOriginalFilename();
		String suffix = FilenameUtils.getExtension(pic.getOriginalFilename());
		String newname=UUID.randomUUID().toString()+"."+suffix;
		String path=request.getServletContext().getRealPath("/image/"+newname);
		String savepath="/image/"+newname;
		System.out.println("保存路径是："+path);
		InputStream is=pic.getInputStream();
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
		bookPic.setFm("0");
		bookPic.setSavepath(savepath);
		bookPic.setShowname(originalName);
		picService.addBookPic(bookPic);
		
		Map<String, String> result=new HashMap<String, String>();
		result.put("result", "success");
		return result;
	}
	
}
