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
	<script>
		function gameOverMessage(msg){
			$('#message').html(msg);
			let x = document.getElementById("confirmation");
			x.style.display='block';
		}
	</script>
</head>
<body class="w3-light-grey">

	<%-- Заголовок  --%>
	<div class="w3-bar w3-white w3-border-bottom w3-xlarge">
		<button class="w3-text-blue w3-bar-item w3-white"><b>Quest</b></button>
		<a href="${pageContext.request.contextPath}/logout" class="w3-bar-item w3-button w3-right w3-hover-blue w3-text-blue"><b>Log out</b></a>
		<button class="w3-text-blue w3-bar-item w3-white w3-right"><%=request.getAttribute("name")%></button>
	</div>

	<%-- Полоса здоровья --%>
	<div class="w3-light-grey">
		<div id="healthBar" class="w3-container w3-green" style="height:2px;width:1%"></div>
	</div>

	<div id="confirmation" class="w3-modal">
		<div class="w3-modal-content w3-card-4">

			<div class="w3-bar w3-black">
				<button class="w3-bar-item w3-blue"><i class="w3-margin-right"></i>Сообщение</button>
			</div>

			<div class="w3-container w3-white">
				<h2>Вы проиграли</h2>
				<div class="w3-container">
					<div id="message"><p>Вы проиграли потому то и потому то</p></div>
				</div>
				<p><button class="w3-btn w3-block w3-black w3-hover-blue" onclick="location.href='/quest'">Заново</button></p>
			</div>
		</div>
	</div>

	<%-- Весь HTML текст --%>
	<div class="w3-container w3-content w3-hide-small w3-padding-16 locationContent" style="max-width: 1500px; width: 70%">

		<%-- htmlLocationText --%>
		<p style="height: 150px">
			В тусклой надежде ты уже долго бредёшь по пляжу из чистейшего белого песка. С одной стороны тянется
			тропический лес, с другой - океан. По-прежнему ни души. И только редкие обломки, прибитые к берегу.
			Ты выходишь к небольшой реке, которая впадает в океан. Дальше идти невозможно из за острых крутых скал.
			С твоей стороны берег пологий, вдоль него вполне можно идти вверх по реке вдоль леса. Другой берег реки
			отвесный, в густых зарослях. Куда ты направишься?
		</p>

		<%-- htmlLocationButtons --%>
		<div class="w3-bar w3-padding-16">
			<button class="w3-button w3-black w3-hover-blue" style="width:200px; margin-right:8px; margin-bottom:8px" onclick="changeLocation('river')">Река</button>
			<button class="w3-button w3-black w3-hover-blue" style="width:200px; margin-right:8px; margin-bottom:8px" onclick="changeLocation('forest')">Лес</button>
			<button class="w3-button w3-black w3-hover-blue" style="width:200px; margin-right:8px; margin-bottom:8px" onclick="changeLocation('jungle')">Джунгли</button>
		</div>

		<%-- htmlActionButtons --%>
		<div class="w3-bar w3-padding-16">
			<button class="w3-button w3-black w3-hover-blue" style="width:200px; margin-right: 8px; margin-bottom:8px" onclick="doAction('%1$s', this)">Поставить ловушку</button>
			<button class="w3-button w3-black w3-hover-blue" style="width:200px; margin-right: 8px; margin-bottom:8px" onclick="doAction('%1$s', this)">Украсть аккумулятор</button>
		</div>


		<%-- htmlAlerts --%>
		<div class="w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity">
			<p>Тебе попались прочные растения, похожие на верёвки. Из них можно сделать ловушку.</p>
			<button onclick="this.parentElement.style.display='none'" class="w3-button w3-display-right w3-hover-blue">×</button>
		</div>
		<div class="w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity">
			<p>Тебе на глаза попался подходящий пикап. Наверняка в нём есть аккумулятор.</p>
			<button onclick="this.parentElement.style.display='none'" class="w3-button w3-display-right w3-hover-blue">×</button>
		</div>
		<div class="w3-panel w3-display-container w3-leftbar w3-pale-red w3-border-red w3-animate-opacity">
			<p>На тебя внезапно напал тигр. Ты получил критичные раны, но остался жив. Похоже, ты не интересовал его в качестве еды.</p>
			<button onclick="this.parentElement.style.display='none'" class="w3-button w3-display-right w3-hover-red">×</button>
		</div>

		<%-- htmlActionAlerts --%>
		<div class="actionContent">
			<div class="w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity">
				<p>Тебе удалось украсть аккумулятор со старого пикапа. Теперь ты сможешь включить маяк. Но лучше его включать в подходящем месте</p>
				<button onclick="this.parentElement.style.display='none'" class="w3-button w3-display-right w3-hover-blue">×</button>
			</div>
			<div class="w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity">
				<p>Тебе попались прочные растения, похожие на верёвки. Из них можно сделать ловушку.</p>
				<button onclick="this.parentElement.style.display='none'" class="w3-button w3-display-right w3-hover-blue">×</button>
				<button class="w3-button w3-black w3-hover-blue" style="width:200px; margin-right: 8px; margin-bottom:8px" onclick="doAction('%1$s', this)">%2$s</button>
			</div>
		</div>

	</div>



	<script>
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
	</script>
</body>
</html>
