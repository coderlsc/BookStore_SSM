<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="chrome">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap </title>
	<link href="css/bootstrap.min.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	.page-header{
    		margin: 2px 0;
    		padding-bottom: 1px;
    	}
    </style>
  </head>
  <body>
	添加书籍页面
	<form enctype="multipart/form-data"  method="post" class="form-horizontal" action="book/addBook.do" >
	  <div class="form-group">
	    <label for="name" class="col-sm-2 control-label">书籍名称</label>
	    <div class="col-sm-10">
	      <input  name="name" type="text" required class="form-control" id="name" placeholder="输入书籍名字">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="author" class="col-sm-2 control-label">作者</label>
	    <div class="col-sm-10">
	      <input name="author" type="text" required class="form-control" id="author" placeholder="作者">
	    </div>
	  </div>
	   <div class="form-group">
	    <label for="price" class="col-sm-2 control-label">价格</label>
	    <div class="col-sm-10">
	      <input name="price" type="number" required class="form-control" id="price" placeholder="输入价格">
	    </div>
	  </div>
	   <div class="form-group">
	    <label for="publishdate" class="col-sm-2 control-label">出版日期</label>
	    <div class="col-sm-10">
	      <input  name="publishdate" type="date" required class="form-control" id="publishdate" placeholder="出版日期" />
	    </div>
	  </div>
	   <div class="form-group">
	    <label for="cateogoryid" class="col-sm-2 control-label">书籍类别</label>
	    <div class="col-sm-10">
	     	<select class="form-control"  id="categoryid" name="categoryid">
	     	</select>
	     	<label id="message_cate" style="color:red;"></label>
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="status" class="col-sm-2 control-label">状态</label>
	    <div class="col-sm-10">
	 		 <select class="form-control" id="status" name="status">
			  	<option value="1" selected>上架</option>
			  	<option value="0">下架</option>
	     	</select>
	    </div>
	  </div>	
	   <div class="form-group">
	    <label for="status" class="col-sm-2 control-label">库存</label>
	    <div class="col-sm-10">
	    	<input class="form-control" required="required" name="total" type="number" value="1" min="1" max="10000"/>
	    </div>
	  </div>
	  <div class="form-group">
	   <div class="col-sm-2">
	    	<button type="button" onclick="addpic()" class="btn btn-primary">添加图片</button>
	    </div>
	    <br>
	    <div class="col-sm-10" id="pics">
	    </div>
	    <div id="result"></div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-default">提交</button>
	    </div>
	  </div>
	</form>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript">
		var i=0;
	function addpic(){
			var ss= "<input class='btn btn-primary' type='file' name='pic' onchange='showImg()' />"+
			"是否是封面图片"+"<input type='radio' name='fm' value='"+(i++)+"'/>";
			$("#pics").append(ss);
	}
	$("button:submit").click(function(event){
		var caid=$("#categoryid").val();
		if(caid=="all"){
			$("#message_cate").html("请选择类型");
			 var event =  window.event;
			  event.preventDefault(); // 兼容标准浏览器
		}
	});
	/* function validate(){
		var caid=$("#categoryid").val();
		if(caid=="all"){
			
		}
	}	 */
    // 将文件以Data URL形式进行读入页面
    function showImg(){
		 var result = $("#result");
		    if(typeof FileReader == 'undefined') {
		        result.innerHTML = "抱歉，你的浏览器不支持FileReader";
		    }
        // 检查是否为图像类型
        var files=$("input:file");
        result.html("");
        for(var i=0;i<files.length;i++){
        	 var simpleFile = files.get(i).files[0];
             if(!/image\/\w+/.test(simpleFile.type)) {
                 alert("请确保文件类型为图像类型");
                 return false;
             }
             var reader = new FileReader();
             // 将文件以Data URL形式进行读入页面
             reader.readAsDataURL(simpleFile);
             reader.onload = function(e){
                 console.log(this.result);
                 result.append("<img style='height:150px;' src='"+this.result+"' alt='img'/>");
             };
        }
       
    }
	
		$(function(){
			$.ajax({
				url:"cate/queryAllCate.do",
				dataType:"json",
				type:"post",
				data:"",
				success:function(data){
					cates=data;
					var options="<option value='all'>===全部===</option>";
					$.each(cates,function(key,value){
						options+="<option value='"+key+"'>"+value+"</option>";
					});
					$("#categoryid").html(options);
				},
				error:function(){
					
				}
			});
			
		});
	
	</script>
  </body>
</html>

