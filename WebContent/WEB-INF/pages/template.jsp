<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="/ikko/resources/js/jquery.js"></script>
		<script type="text/javascript" src="/ikko/resources/js/jquery.form.js"></script>
		<script type="text/javascript" src="/ikko/resources/js/jquery.corner.js"></script>
		<script type="text/javascript" src="/ikko/resources/js/jquery.blockUI.js"></script>
		<script type="text/javascript" src="/ikko/resources/js/input.form.js"></script>
		<link rel="stylesheet" type="text/css" href="/ikko/resources/css/style.css" />
		<title>${pageInfo.title}</title>
	</head>
	<body>
		<div id="wrapper">
			<h1>PROJECT IKKO</h1>

			<div id="content">
				<h2>${pageInfo.title}</h2>
				<div class="message">${pageInfo.message}</div>
<jsp:include page="${pageInfo.page}"></jsp:include>	
			</div><!-- end of content -->

			<div id="footer">&copy;2010 SOFTEM</div>
		</div><!-- end of wrapper -->
	</body>
</html>
