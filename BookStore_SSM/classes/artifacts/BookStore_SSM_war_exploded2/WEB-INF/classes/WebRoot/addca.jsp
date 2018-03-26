<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap</title>
<link href="css/bootstrap.min.css">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
.page-header {
	margin: 2px 0;
	padding-bottom: 1px;
}
</style>
</head>
<body>
	添加类别页面
	<form class="form-horizontal"  method="post" action="cate/addCa.do"  role="form">
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">类别名字</label>
			<div class="col-sm-10">
				<input required type="text" name="name" class="form-control" id="name" placeholder="请输入名字">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">提交</button>
			</div>
		</div>
	</form>
</body>
</html>
