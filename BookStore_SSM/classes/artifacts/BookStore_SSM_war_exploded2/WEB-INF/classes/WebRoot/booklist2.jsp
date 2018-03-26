<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>图书检索</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
     <!-- Bootstrap -->
    <link href="css/bootstrap-table.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

  </head>
  <body>
  		<!-- 条件查询 -->
  		<div id="toolbar"> 
  		<form class="form-inline" role="form">
		  <div class="form-group">
		    <label  for="name">名字:</label>
		    <input type="text" class="form-control" 
		    id="name" name="name" placeholder="输入书名">
		  </div>
		  
  		  <div class="form-group">
		    <label  for="cate">书籍类型:</label>
		    <select  class="form-control" 
		    id="cate" name="cate">
		    	
		    </select>
		  </div>
			
  		  <div class="form-group">
  			<button type="button"
  			 class="et btn btn-default">查询</button>
  		  </div>
  		   <div class="form-group">
  			<button type="button" id="delAll"
  			 class="et btn btn-danger">删除</button>
  		  </div>
		</form>
		</div>
  		<!-- 条件查询 -->
  		<table 
  		data-url="book/queryBooks.do" id="tb" class="table table-bordered table-hover 
  		table-stripted table-condensed">
  		</table>
  		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
							  <div class="modal-dialog" style=" width:600px;height:800px;">   
								<div class="modal-content">
									<div class="modal-header">
									书籍详细信息
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
										X
										</button>
										<h4 class="modal-title" id="myModalLabel">
											
										</h4>
									</div>
									<div class="modal-body">
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">
											关闭
										</button>
										<!-- <button id="submit" type="button" class="btn btn-primary">
											提交更改
										</button> -->
									</div>
											</div><!-- /.modal-content -->
									</div><!-- /.modal-dialog -->
								</div><!-- /.modal -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/ajaxfileupload.js"></script>
    <script src="js/bootstrap-table.js"></script>
    <script src="js/bootstrap-table-zh-CN.js"></script>
    <script>
    var cates;
    var to_update=false;
    var isAll=false;
    var selected=false;
    //定义一个是否是修改命令的标示符  默认是不是修改只是查看书籍详细信息
    function queryAllCate(){
		$.ajax({
				url:"cate/queryAllCate.do",
				data:"",
				dataType:"json",
				type:"post",
				success:function(data){
					cates=data;
					var options="<option value='all'>===全部===</option>";
					$.each(cates,function(key,value){
						options+="<option value='"+key+"'>"+value+"</option>";
					});
						
					$("#cate").html(options);
				},
				error:function(){
					
				}
			});
	}
    $("#cate").change(function(){
    	sssss.bootstrapTable("refresh");
    });
    $("#name").keyup(function(){
    	sssss.bootstrapTable("refresh");
    });
    	$(function(){
    		queryAllCate();
    		queryData();
    		$(".et").on("click",function(){
    			//重新请求
    			sssss.bootstrapTable("refresh");
    		});
    		$("#delAll").click(function(){
    			var ids="";
    			var boxs=$("input:checkbox[name='seleted_id']:checked");
    			if(boxs.length==0){
    				alert("请选择您要删除的书籍!");
    			}
    			else{
    				boxs.each(function(){
        				ids+=$(this).val()+"-";
        			});
    				del(ids);
    			}
    		});
    		$("#selectAll").click(function(){
    			 var boxs=$("input:checkbox");
    			if(isAll==true){
    				boxs.each(function(){
    	   				$(this).prop("checked",false);
    	   			});
    				isAll=false;
    			}
    			else{
    				boxs.each(function(){
    	   				$(this).prop("checked",true);
    	   			});
    				isAll=true;
    			}
    		});
    		
    	});
    	// 默认：假分页 按需显示 
    	// 真分页:按需提取
    	var os="";
    	var os1="";
    	var sssss="";
    	function queryData(){
    		sssss = $("#tb").bootstrapTable({
    			pagination:true,
    			toolbar:"#toolbar",//指定工具条 一般指查询框
    			pageSize:2,//每页显示多少条
    			pageList:[3,6,9],//可以修改的每页的条数
    			sidePagination:"server",//服务器端分页
    			queryParamsType:"undefined",//limit:offset+limit undefined:向服务器传输 pageSize+pageNumber
    			queryParams:function(msg){//参数
    				console.log(msg);
    				os = (msg.pageNumber-1)*msg.pageSize;
    				var params = {
    					currentPage:msg.pageNumber,//当前页码
    					pageSize:msg.pageSize,//每页记录数
    					name:$("#name").val(), //参数
    					categoryid:$("#cate").val()
    				};
    				console.log(params);
    				return params;
    			},
    			//所有列的数据展示：
    			columns:[
					{	
						title:"全选"+"<input type='checkbox' id='selectAll'/>",
						formatter:function(value,row,index){
							return "<input name='seleted_id' type='checkbox' value='"+row.id+"' />";
							}
						},
    			   {
    				title:"编号",
    				formatter:function(value,row,index){
    					//return sssss.bootstrapTable("getPage")+index+1;
    					return os+index+1;
    					}
    				},
    				{
    				field:"name",
    				title:"名字"
    				},
    				{
    				field:"price",
    				title:"价格"
    				},
    				{
    				field:"author",
    				title:"作者"
    				},{
    				field:"dateStr",
    				title:"出版日期"
    				},{
    				field:"category.name",
    				title:"类别"
    				},
    				{	
    				field:"status",
    				title:"状态",
    				formatter:function(value,row,index){
    					var val="";
    					if(value=="1")
    						val="已上架";
    						else{
    							val="已下架";
    						}
    					return val;
    					}
    				},
    				{	field:"total",
    					title:"库存"
    				},
    				{	 
    				 field:"id",
    				 title:"操作",
    				formatter:function(value,row,index){
    					var ops="<a class='del btn btn-danger' "+ 
    					"href='javascript:void(0)' id='"+value+"'>删除</a>&nbsp;&nbsp;"+
    					"<a id='"+value+"' class='update btn btn-success' "+ 
    					"href='javascript:void(0)'>修改</a><button name='"+row.id+"' class='viewBook btn btn-primary'>查看详情</button>";
    					return ops;
    				},
    				align:"center",
    				events:etevents
    			}],
    			//点击一行触发的动作
    			onClickRow:function(row,element,field){
    				console.log(row);
    				console.log(element);
    				console.log(field);
    				if(selected==true){
    					$("input:checkbox[value='"+row['id']+"']").prop("checked",false);
    					selected=false;
    				}
    				else{
    					$("input:checkbox[value='"+row['id']+"']").prop("checked",true);
    					selected=true;
    				}
    				//input:checkbox[name='seleted_id']
    				
    				//alert(element);
    				//alert(field);
    				//var boxs=$("input:checkbox[name='seleted_id']:checked");
    				
    				//alert("点击"+field+"了");
    			}
    			
    		});
    	}
    	window.etevents={
    		'click .del':function(event,value,row,index){
    			var ids=$(this).attr("id")+"-";
    			del(ids);
    		},
    		'click #selectAll':function(event,value,row,index){
    			 var boxs=$("input:checkbox");
    			for(var i=0;i<boxs.length;i++){
    				boxs[i].prop("checked",true);
    			}
    		},
    		'click .viewBook':function(event,value,row,index){
    			showinfo($(this).attr("name"));
    		},
    		'click .update':function(event,value,row,index){
    			to_update=true;
    			showinfo($(this).attr("id"));
    		}
    	};
    	function del(ids){
    		if(confirm("您确定要删除并且清空其所有信息吗?")){
    			$.ajax({
        			url:"book/deleteBook.do",
        			data:"ids="+ids,
        			dataType:"json",
        			type:"post",
        			success:function(data){
        				if(data.result=="delesuccess"){
        					window.location.href="booklist2.jsp";
        				}
        				else{
        					alert("请稍后再试..");
        				}
        			}
        			
        		});
    		}
    	
    	}
    	function showinfo(id){
			  $.ajax({
				  url:"book/queryBook.do",
				  data:"bookid="+id,
				  dataType:"json",
				  type:"post",
				  success:function(data){
					  var book=data;
					  var pics=book.pics; //set集合
					  var html="";
					  if(to_update==true){
						  //不只读 是去更改的 要加上form表单的
						  html+="<form onkeydown='if(event.keyCode==13)return false;' class='form-horizontal' id='form' action='book/update.do' method='post'><input type='hidden' name='id' value='"+book.id+"'/>"+
						  "名字：<input required name='name'  class='form-control' type='text' value='"+book.name+"'/><br>作者:<input required name='author'  class='form-control' type='text' value='"+book.author+"'/><br>价格:<input required name='price'  class='form-control' type='number' value='"+book.price+"'/><br>出版日期:<input type='date' required name='publishdate'  class='form-control' value='"+book.dateStr+"' />"+
						  "库存:<input required name='total' type='number' min='1' max='10000' class='form-control' value="+book.total+" /><br>"+
						  "<button type='submit' class='btn btn-primary'>提交</button></form>";
						  html+="<button name='"+book.id+"' class='uploadpic btn btn-primary' id='"+book.id+"'>上传图片</button><button class='delepic btn btn-primary'>删除图片</button><button name='"+book.id+"' class='changefm btn btn-primary'>设为封面</button><br>";
					  }
					  else{
						  //只读
						  html="名字：<input readonly class='form-control' type='text' value='"+book.name+"'/><br>作者:<input readonly class='form-control' type='text' value='"+book.author+"'/><br>价格:<input readonly class='form-control' type='number' value='"+book.price+"'/><br>出版日期:<input readonly class='form-control' value='"+book.dateStr+"' />库存:<input type='number' min='1' max='10000' class='form-control' value='"+book.total+"' /><br>";
					  }
					 pics.forEach(function(element,index,set){
							  if(element.fm=="1"){
									html+="封面<img style='margin-left:20px;'  height='300px' src='<%=path%>"+element.savepath+ "'/>";
									if(to_update==true){
										html+="<input id='"+element.id+"' type='checkbox' name='selectedpic'/>";
									}
								}
							  else{
								  html+="<img style='margin-left:20px;'  height='150px' src='<%=path%>"+element.savepath + "'/>";
								if(to_update==true){
									  html+="<input id='"+element.id+"' type='checkbox' name='selectedpic'/>";
									 }
								  }
						});
					  if(to_update==true){
						 var ss= "<input type='file' id='pic' name='pic' />";
							html+=ss;
					 } 
					  $(".modal-body").html(html);
					  $("#myModal").modal("show");
						$("button.delepic").click(function(){
							var array_selectedpic=$("input[name='selectedpic']:checked");
							 if(array_selectedpic.length==0){
								 alert("未选择图片！");
								 return;
							 }
							 if(confirm("确认要删除吗?")){ 
									 var pid="-";
									$.each(array_selectedpic,function(){
											pid+=$(this).attr("id");
											pid+="-";	
										 });
									 $.ajax({
										 url:"pic/delePic.do",
										 data:"pid="+pid,
										 type:"post",
										 dataType:"json",
										 success:function(data){
											 if(data.result=="success"){
												 alert("图片删除成功");
												 showinfo(id);
												 return ;
											 }
											 else {
												 alert("删除失败 请稍后再试..");
											 }
										 },
										 error:function(){
											alert("服务器繁忙..."); 
										 }
										 
									 }); 
								 }
						});
						$("button.changefm").click(function(){
							 if(confirm("确认要将此图片设置为封面吗?")){ 
									 var array_selectedpic=$("input[name='selectedpic']:checked");
									if(array_selectedpic.length!=1){
										alert("选择有误请重新选择");
									}else{
										var pid=$("input[name='selectedpic']:checked").attr("id");
											 $.ajax({
												 url:"pic/toFm.do",
												 data:"pid="+pid+"&bookid="+id,//同时带着bookid和已经选中的所有的图书路径
												 type:"post",
												 dataType:"json",
												 success:function(data){
													 if(data.result=="updateSuccess"){
														 //alert("设置封面成功");
														 showinfo(id);
														 return ;
													 }
													 else {
														 alert("设置失败 请稍后再试..");
													 }
												 },
												 error:function(){
													alert("服务器繁忙..."); 
												 }
												 
											 }); 
										
									}
									
								 }
							
						});
						
						$("button.uploadpic").click(function(){
							var bookid=$(this).attr("name");
							alert("选择的书籍id是"+bookid);
							$.ajaxFileUpload({
								url:"pic/uploadPic",
								type:"post",
								fileElementId:"pic",
								data:{
									"bookid":bookid
								},
								dataType:"json",
								success:function(data){
									var res=data.result;
									if(res=="success"){
										alert("上传成功！");
										showinfo(bookid);
										return ;
									}else{
										alert("请选择图片!");
									}
								},
								error:function(){
									alert("上传失败 请稍后再试");
								}
			
							});
							
						});
						$("button#submit").click(function(){
							$("#form").submit();
						});
						$("#form").keypress(function(event){
			   			 var keycode = (event.keyCode ? event.keyCode : event.which);  
					  	  if(keycode == "13"){
					  		  $("button.uploadpic").unbind();
					  		$("#pic").attr("disabled","disabled");
					  		$("#form").submit();
					    } 
			   	});
				  },
				  error:function(){
					  
				  }
			  });		  
		 }
    </script>
  </body>
</html>









