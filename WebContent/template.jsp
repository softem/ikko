<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<fmt:setBundle basename="messages" var="rs" />
<?xml version="1.0" encoding="URF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<title><fmt:message bundle="${rs}" key="${title}"/></title>
</head>
<body>

<div id="content">
<jsp:include page="${page}" />
</div>

</body>
</html>