<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap </title>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
  </head>
  <body>
	<nav class="navbar navbar-inverse" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">书籍管理系统</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">首页</a></li>
        <li><a href="#">Link</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
        	<c:if test="${not empty sessionScope.admin}">
        		<span style="color: white;">欢迎您管理员:${sessionScope.admin.name}</span>
        		<a style=" display:inline; color: white;" href="#" onclick="invalidate()">注销登录</a>
        	</c:if>
        	<c:if test="${empty sessionScope.admin}">
        		 <a style="color: white;" data-toggle='modal' data-target='#myModal' href="##">登录</a>
        	</c:if>
          </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
	<!-- 导航条end -->
	<!-- 登录弹框开始 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-dialog" style=" width:500px;height:1000px;">   
								<!-- 这里加上表单认证 -->
								<!-- <form id="loginform" action="" method="post"> -->
								<div  id="loginformDiv">
								<input type="hidden" name="method" value="formlogin" />
								<div class="modal-content">
									<div class="modal-header">
									登录
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
										X
										</button>
										<h4 class="modal-title" id="myModalLabel" >
											
										</h4>
									</div>
									
									<div class="modal-body">
										账号:<input id="name" name="name"  class='form-control' type='text' />
										<br>
										密码:<input id="password"  name="password" class='form-control' type="password" />	
									<label id="message" style="color: red;">  </label>
									</div>
									<div class="modal-footer">
										<input  value="重置"  type="reset" class="btn btn-default" />
										<input value="登录" type="submit" class="btn btn-primary" />
									</div>
											</div><!-- /.modal-content内容结束 -->
											<!-- </form> -->
											</div>
									</div><!-- /.modal-dialog -->
									
								</div><!-- 弹框结束 -->
								
	<!-- 主题内容 -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4">
				<div class="list-group">
				  <a href="#" class="list-group-item active">
				   书籍管理系统
				  </a>
				  <a href="addca.jsp" target="et" class="list-group-item">添加类别</a>
				  <a href="addbook.jsp" target="et" class="list-group-item">添加书籍</a>
				  <a href="booklist2.jsp"  target="et" class="list-group-item">书籍列表</a>
				</div>
			</div>
			<div class="col-md-8">
				<iframe name="et" frameborder="0" height="1000px" width="100%" src="welcome.jsp">
				</iframe>
			
			
			
			</div>
			</div>
	</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    
    <script type="text/javascript">
    	var result=false;
    	$("input:submit").click(function(){
    		validate();
    		if(result==true){
    			login();
    		}
    		else{
    			$("#message").text("用户名或者密码不能为空！");
    		}
    	});
    	$("#loginformDiv").keypress(function(event){
    			validate();
   		 var keycode = (event.keyCode ? event.keyCode : event.which);  
		    if(keycode == "13"){
		    	if(result==true){
	    			login();
	    		}
	    		else{
	    			$("#message").text("用户名或者密码不能为空！");
	    		}
		    } 
   	});
    	function validate(){
    		var name=$("#name").val();
    		var password=$("#password").val();
    		if(name==""||password==""){
    			result=false;
    		}
    		else{
    			result=true;
    		}
    		
    	}
    	function login(){
    		$.ajax({
    			url:"admin/login.do",
    			data:"name="+$("#name").val()+"&password="+$("#password").val(),
    			dataType:"json",
    			type:"post",
    			success:function(data){
    				if(data.result=="loginsuccess"){
    					window.location.href="index.jsp";
    				}
    				else{
    					$("#message").text("用户名或者密码错误！");
    				}
    			},
    			error:function(){
    				
    			}
    			});
    	}
    	function invalidate(){
    		if(confirm("你确定要注销登录吗？")){
    			window.location.href="admin/invalidate.do";
    		}
    	}
    
    </script>
  </body>
</html>

