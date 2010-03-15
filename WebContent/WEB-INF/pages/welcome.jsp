<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script language="javascript">
	$(function() {
		$('#loginForm').corner();
	});
</script>

<form id="loginForm" action="${t:url("/welcome/login")}" method="post">
	<div>
		<label for="loginId">ログインID</label>
		<input type="text" name="loginId" id="loginId" size="34" maxlength="32" value="${employee.loginId}"/>
	</div>
	<div>
		<label for="password">パスワード</label>
		<input type="password" name="password" id="password" size="34" maxlength="32" />
	</div>
	<div><input type="submit" name="login" value="ログイン" /></div>
</form>
