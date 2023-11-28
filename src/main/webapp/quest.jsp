<%@ page import="com.javarush.quest.kavtasyev.entity.app.User" %>
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
	<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.js"></script>
</head>
<body class="w3-light-grey">

	<%-- Заголовок  --%>
	<div class="w3-bar w3-white w3-border-bottom w3-xlarge">
		<button class="w3-text-blue w3-bar-item w3-white"><b>Quest</b></button>
		<a href="${pageContext.request.contextPath}/logout" class="w3-bar-item w3-button w3-right w3-hover-blue w3-text-blue"><b>Log out</b></a>
		<button class="w3-text-blue w3-bar-item w3-white w3-right"><%=((User) session.getAttribute("user")).getName()%></button>
	</div>

	<%-- Полоса здоровья --%>
	<div class="w3-light-grey">
		<div id="healthBar" class="w3-container w3-green" style="height:3px;width:100%"></div>
	</div>

	<%-- Описание места --%>
	<div class="w3-container w3-content w3-hide-small w3-padding-16 locationContent" style="max-width: 1500px; width: 70%; height: 700px">

		<div style="height: 150px">
			<%
				out.println("<h5> Привет, " + ((User) session.getAttribute("user")).getName() + "</h5>");
			%>
			<p>
				Добро пожаловать на квест! Цель данного квеста - выжить и спастись. На пути к этому тебе встретятся много опасностей. Учти, что в этом квесте не всё зависит от тебя, большую роль играет удача. Возможно, понадобится не одна попытка на то, чтобы пройти квест. События в квесте носят несколько случайный характер! Удачи!
			</p>
		</div>

		<div class="w3-bar w3-padding-16">
			<button class="w3-button w3-black w3-hover-blue w3-block w3-center" style="width:40%" onclick="changeLocation('StartBeach')">Start</button>
		</div>
	</div>

	<%-- Уведомление: Ты выиграл --%>
	<div id="winConfirmation" class="w3-modal">
		<div class="w3-modal-content w3-card-4">

			<div class="w3-bar w3-black">
				<button class="w3-bar-item w3-blue"><i class="w3-margin-right"></i>Сообщение</button>
			</div>

			<div class="w3-container w3-white">
				<h2>Ты выиграл!</h2>
				<div class="w3-container">
					<p id="winMessage">Ты выиграл потому то и потому то</p>
				</div>
				<p><button class="w3-btn w3-block w3-black w3-hover-blue" onclick="location.href='/quest'">Заново</button></p>
			</div>
		</div>
	</div>

	<%-- Уведомление: Игра проиграна --%>
	<div id="failConfirmation" class="w3-modal">
		<div class="w3-modal-content w3-card-4">

			<div class="w3-bar w3-black">
				<button class="w3-bar-item w3-red"><i class="w3-margin-right"></i>Сообщение</button>
			</div>

			<div class="w3-container w3-white">
				<h2>Ты проиграл</h2>
				<div class="w3-container">
					<p id="failMessage">Ты проиграл потому то и потому то</p>
				</div>
				<p><button class="w3-btn w3-block w3-black w3-hover-red" onclick="location.href='/quest'">Заново</button></p>
			</div>
		</div>
	</div>

	<%-- Уведомление: Сервисная информация --%>
	<div id="serviceInformation" class="w3-modal">
		<div class="w3-modal-content w3-card-4">

			<div class="w3-bar w3-black">
				<button class="w3-bar-item w3-blue"><i class="w3-margin-right"></i>Сообщение</button>
			</div>

			<div class="w3-container w3-white">
				<h2>Информация</h2>
				<div class="w3-container">
					<div class="serviceContent"></div>
				</div>
				<p><button class="w3-btn w3-block w3-black w3-hover-blue" onclick="this.parentElement.parentElement.parentElement.parentElement.style.display='none'">Закрыть</button></p>
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

	<script defer>
		function changeLocation(msg) {
			$.ajax({
				url: '/quest/do-operation',
				method: 'get',
				dataType: 'html',
				data: {location: msg},
				success: function (data) {
					$( ".locationContent").html( data );
				}
			});
		}

		function doAction(msg, element) {
			$.ajax({
				url: '/quest/do-operation',
				method: 'get',
				dataType: 'html',
				data: {action: msg},
				success: function (data) {
					$( ".actionContent").html( data );
					element.style.display = 'none';
				}
			});
		}

		function serviceInf() {
			$.ajax({
				url: '/quest/do-operation',
				method: 'get',
				dataType: 'html',
				data: {service: "user"},
				success: function (data) {
					$( ".serviceContent").html( data );
				}
			});
			let x = document.getElementById("serviceInformation");
			x.style.display='block';
		}

		function setHealth(newValue)
		{
			let elem = document.getElementById("healthBar");
			let value =	elem.style.width.replace("%", "");	 //elem.style.width;
			let id = setInterval(frame, 10);
			if (newValue > 100)
				newValue = 100;
			function frame(){
				if (value > newValue)
				{
					value--;
					elem.style.width = value + '%';
					move();
				}
				else if (value < newValue)
				{
					value++;
					elem.style.width = value + '%';
					move();
				}
				else if (value === newValue)
				{
					clearInterval(id);
				}
				function move(){
					if(value < 1)
					{
						gameOverMessage("У тебя закончились жизни!");
						elem.style.display = 'none';
					}
					if (value >= 30 && value < 70)
					{
						elem.className = elem.className.replace(" w3-green", " w3-yellow");
						elem.className = elem.className.replace(" w3-red", " w3-yellow");
					}
					else if (value >= 1 && value < 30)
					{
						elem.className = elem.className.replace(" w3-yellow", " w3-red");
					}
					else if (value >= 70)
						elem.className = elem.className.replace(" w3-yellow", " w3-green");
				}
			}
		}

		function youWinMessage(msg){
			$('#winMessage').html(msg);
			let x = document.getElementById("winConfirmation");
			x.style.display='block';
		}

		function gameOverMessage(msg){
			$('#failMessage').html(msg);
			let x = document.getElementById("failConfirmation");
			x.style.display='block';
		}
	</script>
</body>
</html>
