<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form action="${siteUrl}/action/login" method="post">
	<div><label for="loginId">ログインID</label>
	<input type="text" name="loginId" id="loginId" size="20" maxlength="32" /></div>
	<div><label for="password">パスワード</label>
	<input type="password" name="password" id="password" size="20" maxlength="32" /></div>
	<div><input type="submit" id="login_button" value="ログイン" /></div>
</form>

${sample.hello(parameterMap)}