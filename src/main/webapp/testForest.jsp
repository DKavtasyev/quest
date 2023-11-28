<p style="height: 150px">В поисках спасения ты пробираешься через тропический лес. Хотя он не выглядит пугающим, но всё
	же нужно быть начеку. Спустя некоторое время, перед тобой простирается равнина, на другом краю которой ты видишь
	гору. Слева от тебя виднеется река. Выбирай, куда отправишься.</p>
<button class="w3-button w3-black w3-hover-blue" style="width:200px; margin-right: 8px; margin-bottom:8px"
		onclick="doAction('LookForAWayOut', this)">Плутать
</button>
<div class="w3-bar w3-padding-16">
	<button class="w3-button w3-black w3-hover-blue" style="width:200px; margin-right: 8px; margin-bottom:8px"
			onclick="doAction('DrinkWaterFromSpring', this)">Попить воду
	</button>
	<button class="w3-button w3-black w3-hover-blue" style="width:200px; margin-right: 8px; margin-bottom:8px"
			onclick="doAction('SetASnare', this)">Поставить ловушку
	</button>
</div>
<div class="w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity"><p>Тебе попались
	прочные растения, похожие на верёвки. Из них можно сделать ловушку.</p><span
		onclick="this.parentElement.style.display='none'" class="w3-button w3-display-topright w3-hover-blue">×</span>
</div>
<div class="w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity"><p>Тебе попалась
	толстая крепкая палка, которую можно использовать как дубину.</p><span
		onclick="this.parentElement.style.display='none'" class="w3-button w3-display-topright w3-hover-blue">×</span>
</div>
<div class="w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity"><p>Ты наткнулся на
	источник с очень чистой кристально прозрачной водой.</p><span onclick="this.parentElement.style.display='none'"
																  class="w3-button w3-display-topright w3-hover-blue">×</span>
</div>
<div class="w3-panel w3-display-container w3-leftbar w3-pale-red w3-border-red w3-animate-opacity"><p>Ты заблудился, и
	не знаешь, как выбраться.</p><span onclick="this.parentElement.style.display='none'"
									   class="w3-button w3-display-topright w3-hover-red">×</span></div>
<div class="actionContent"></div>