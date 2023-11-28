<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>Квест</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/w3.css">
	<link rel="stylesheet" href="css/fonts.css">
	<link rel="stylesheet" href="css/fontawesome.css">
	<style>
		body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", Arial, Helvetica, sans-serif}
	</style>
</head>

<body class="w3-light-grey">

<%-- Заголовок  --%>
<div class="w3-bar w3-white w3-border-bottom w3-xlarge">
	<button class="w3-text-blue w3-bar-item w3-white"><b>Quest</b></button>
</div>

<%-- Страница   --%>
<div class="w3-display-container w3-content w3-hide-small" style="max-width: 1500px">
	<img class="w3-image w3-grayscale-min" src="img/background.jpg" alt="Island" width="1920" height="750">

	<div class="w3-display-middle" style="width:40%">

		<div class="w3-bar w3-black">
			<button class="w3-bar-item w3-blue"><i class="fa-solid fa-right-to-bracket w3-margin-right"></i>Регистрация</button>
		</div>

		<div class="w3-container w3-white w3-padding-16">

			<%-- Форма регистрации --%>
			<form method="post" action="${pageContext.request.contextPath}/register" class="w3-container">
				<h2>Укажите свои данные</h2><br>

				<label>Имя
					<input name="name" class="w3-input" type="text" required minlength="2" maxlength="64" pattern="[\w(А-Я)(а-я)Ёё]+" title="Минимальная длина - 3 символа, разрешено использовать цифры и буквы">
				</label><br>

				<label>Логин
					<input name="login" class="w3-input" type="text" required minlength="2" maxlength="64" pattern="[\w(А-Я)(а-я)Ёё]+" title="Минимальная длина - 3 символа, разрешено использовать цифры и буквы">
				</label><br>

				<label>Пароль
					<input name="password" class="w3-input" type="password" required minlength="4" maxlength="256" title="Минимальная длина пароля - 4 символа">
				</label>
				<p><button type="submit" class="w3-btn w3-black w3-hover-blue">Регистрация</button>
					<span class="w3-container w3-right w3-padding">Уже есть аккаунт? <a href="${pageContext.request.contextPath}/login">Войти</a></span>
				</p>

				<%
					if (request.getAttribute("loginistaken") != null)
					{
						out.println("Логин " + request.getAttribute("login") + " занят. <br>Попробуйте другой логин.");
					}
				%>
			</form>
		</div>
	</div>

	<div id="confirmation" class="w3-modal">
		<div class="w3-modal-content w3-card-4">

			<div class="w3-bar w3-black">
				<button class="w3-bar-item w3-blue"><i class="w3-margin-right"></i>Сообщение</button>
			</div>

			<div class="w3-container w3-white">
				<h2>Вы успешно зарегистрировались!</h2>
				<div class="w3-container">
					<p>Ваш логин: <% out.print(request.getAttribute("login")); %></p>
					<p>Ваш пароль: <% out.print(request.getAttribute("password")); %> </p>
				</div>
				<p><button class="w3-btn w3-block w3-black w3-hover-blue" onclick="location.href='/login'">Войти</button></p>
			</div>
		</div>
	</div>
</div>

<footer class="w3-container w3-opacity w3-margin-bottom">
	<div class="w3-padding-16">
		<ul class="w3-ul">
			<li><h5>Статистика</h5></li>
			<li>Количество попыток в текущем сеансе:
				<% if (session.getAttribute("attempt") == null)
					out.println(0);
				else
					out.println(session.getAttribute("attempt")); %> </li>
			<li>User IP: <%= request.getRemoteAddr() %></li>
		</ul>
	</div>
	<p class="w3-container"><i class="fa-regular fa-copyright"></i> D.Kavtasyev</p>
</footer>
<%
	if (request.getAttribute("login") != null && request.getAttribute("loginistaken") == null)
	{
		out.println("<script>document.getElementById('confirmation').style.display='block'</script>");
	}
%>
</body>
</html>