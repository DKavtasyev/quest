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

        <div class="w3-display-middle" style="width:30%">

            <div class="w3-bar w3-black">
                <button class="w3-bar-item w3-blue"><i class="fa-solid fa-right-to-bracket w3-margin-right"></i>Добро пожаловать в квест</button>
            </div>

            <div class="w3-container w3-white w3-center">
                <span class="w3-padding-32 w3-container">Создайте свой аккаунт для прохождения квеста</span>
                <p><button class="w3-btn w3-block w3-black w3-hover-blue" onclick="location.href='/register'">Зарегистрироваться</button></p>
                Уже зарегистрированы?
                <p><button class="w3-btn w3-block w3-black w3-hover-blue" onclick="location.href='/login'">Войти</button></p>
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
</body>
<%
    if (session.getAttribute("attempt") == null)
        session.setAttribute("attempt", 0);
%>
</html>