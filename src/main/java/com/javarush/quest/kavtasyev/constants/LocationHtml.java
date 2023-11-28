package com.javarush.quest.kavtasyev.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LocationHtml
{

	//------------------------------------------------------------------------------------------------------------------
	//												Теги
	//------------------------------------------------------------------------------------------------------------------

	public static final String LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG = "<div class=\"w3-bar w3-padding-16\">";
	public static final String LOCATION_BUTTON = "<button class=\"w3-button w3-black w3-hover-blue\" style=\"width:200px; margin-right:8px; margin-bottom:8px\" onclick=\"changeLocation('%1$s')\">%2$s</button>";
	public static final String ACTION_BUTTON = "<button class=\"w3-button w3-black w3-hover-blue\" style=\"width:200px; margin-right: 8px; margin-bottom:8px\" onclick=\"doAction('%1$s', this)\">%2$s</button>";
	public static final String CLOSE_DIV_TAG = "</div>";
	public static final String ACTION_NOTIFICATIONS_OPEN_DIV_TAG = "<div class=\"actionContent\">";
	public static final String NOTIFICATION_OPEN_DIV_TAG = "<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity\">";
	public static final String NOTIFICATION_CLOSE_BUTTON = "<span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-blue\">×</span>";
	public static final String ALARM_OPEN_DIV_TAG = "<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-red w3-border-red w3-animate-opacity\">";
	public static final String ALARM_CLOSE_BUTTON = "<span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-red\">×</span>";
	public static final String GAME_OVER_SCRIPT = "<script>gameOverMessage(\"%s\");</script>";
	public static final String YOU_WIN_SCRIPT = "<script>youWinMessage(\"%s\");</script>";
	public static final String SET_HEALTH_SCRIPT = "<script>setHealth(\"%s\");</script>";
	//------------------------------------------------------------------------------------------------------------------


	//------------------------------------------------------------------------------------------------------------------
	//												Параметры
	//------------------------------------------------------------------------------------------------------------------

	//												Значения параметра location
	public static final String LOCATION_PARAMETER_BEACH = "Beach";
	public static final String LOCATION_PARAMETER_FOREST = "Forest";
	public static final String LOCATION_PARAMETER_RIVER = "River";
	public static final String LOCATION_PARAMETER_JUNGLE = "Jungle";
	public static final String LOCATION_PARAMETER_FAR_BEACH = "FarBeach";
	public static final String LOCATION_PARAMETER_PLAIN = "Plain";
	public static final String LOCATION_PARAMETER_CAVE = "Cave";
	public static final String LOCATION_PARAMETER_MOUNTAIN = "Mountain";
	public static final String LOCATION_PARAMETER_SETTLEMENT = "Settlement";
	public static final String LOCATION_PARAMETER_LOOK_FOR_A_WAY_OUT = "LookForAWayOut";

	//												Значения параметра action
	public static final String ACTION_PARAMETER_SET_A_SNARE = "SetASnare";
	public static final String ACTION_PARAMETER_CHECK_THE_SNARE = "CheckTheSnare";
	public static final String ACTION_PARAMETER_STEAL_THE_CAR_BATTERY = "StealTheCarBattery";
	public static final String ACTION_PARAMETER_STEAL_THE_MACHETE = "StealTheMachete";
	public static final String ACTION_PARAMETER_DRINK_WATER_FROM_SPRING = "DrinkWaterFromSpring";
	public static final String ACTION_PARAMETER_DRINK_WATER_FROM_RIVER = "DrinkWaterFromRiver";
	public static final String ACTION_PARAMETER_FRY_THE_FOWL = "FryTheFowl";
	public static final String ACTION_PARAMETER_EAT_FRUITS = "EatFruits";
	public static final String ACTION_PARAMETER_TURN_ON_THE_BEACON = "TurnOnTheBeacon";
	public static final String ACTION_PARAMETER_FISHING = "Fishing";
	public static final String ACTION_PARAMETER_FRY_THE_FISH = "FryTheFish";
	public static final String ACTION_PARAMETER_SHOOT_A_FLARE_GUN = "ShootAFlareGun";
	public static final String ACTION_PARAMETER_LIGHT_A_FIRE = "LightAFire";
	//------------------------------------------------------------------------------------------------------------------


	//------------------------------------------------------------------------------------------------------------------
	//												Наименования кнопок
	//------------------------------------------------------------------------------------------------------------------

	//												Наименования кнопок локаций
	public static final String BUTTON_BEACH = "Пляж";
	public static final String BUTTON_FOREST = "Лес";
	public static final String BUTTON_RIVER = "Река";
	public static final String BUTTON_JUNGLE = "Джунгли";
	public static final String BUTTON_PLAIN = "Равнина";
	public static final String BUTTON_CAVE = "Пещера";
	public static final String BUTTON_MOUNTAIN = "Гора";
	public static final String BUTTON_SETTLEMENT = "Поселение";

	//												Наименования кнопок действий
	public static final String BUTTON_SET_A_SNARE = "Поставить ловушку";
	public static final String BUTTON_CHECK_THE_SNARE = "Проверить ловушку";
	public static final String BUTTON_STEAL_THE_CAR_BATTERY = "Украсть аккумулятор";
	public static final String BUTTON_STEAL_THE_MACHETE = "Украсть мачете";
	public static final String BUTTON_DRINK_WATER_FROM_SPRING = "Попить из источника";
	public static final String BUTTON_DRINK_WATER_FROM_RIVER = "Попить из реки";
	public static final String BUTTON_FRY_THE_FOWL = "Пожарить дичь";
	public static final String BUTTON_FRY_THE_FISH = "Пожарить рыбу";
	public static final String BUTTON_EAT_FRUITS = "Поесть";
	public static final String BUTTON_TURN_ON_THE_BEACON = "Включить маяк";
	public static final String BUTTON_FISHING = "Ловить рыбу копьём";
	public static final String BUTTON_LOOK_FOR_A_WAY_OUT = "Плутать";
	public static final String BUTTON_SHOOT_A_FLARE_GUN = "Выстрелить из ракетницы";
	public static final String BUTTON_LIGHT_A_FIRE = "Разжечь костёр";
	//------------------------------------------------------------------------------------------------------------------


	//------------------------------------------------------------------------------------------------------------------
	//												Текст
	//------------------------------------------------------------------------------------------------------------------

	//												Описание локаций
	public static final String TEXT_START_BEACH = "<p style=\"height: 150px\">Ты просыпаешься на песчаном лазурном берегу моря или океана. Над бескрайним океаном поднимается алое солнце. Перед тобой стоит нетронутый тропический лес, справа - неприступные скалы, слева - бесконечный песчаный берег. Придя в себя, понимаешь, что ты тут совершенно один. Последняя информация в базе данных твоей памяти - сильный шторм, шквальный ливень, затем стена воды и попытки удержаться на её поверхности. Тебе нужно решить, что делать и куда идти дальше чтобы выжить.</p>";
	public static final String TEXT_BEACH = "<p style=\"height: 150px\">В тусклой надежде ты уже долго бредёшь по пляжу из чистейшего белого песка. С одной стороны тянется тропический лес, с другой - океан. По-прежнему ни души. И только редкие обломки, прибитые к берегу.<br>Ты выходишь к небольшой реке, которая впадает в океан. Дальше идти невозможно из за острых крутых скал. С твоей стороны берег пологий, вдоль него вполне можно идти вверх по реке вдоль леса. Другой берег реки отвесный, в густых зарослях. Куда ты направишься?</p>";
	public static final String TEXT_FOREST = "<p style=\"height: 150px\">В поисках спасения ты пробираешься через тропический лес. Хотя он не выглядит пугающим, но всё же нужно быть начеку. Спустя некоторое время, перед тобой простирается равнина, на другом краю которой ты видишь гору. Слева от тебя виднеется река. Выбирай, куда отправишься.</p>";
	public static final String TEXT_RIVER = "<p style=\"height: 150px\">Решив, что вдоль реки идти безопаснее, ты продолжаешь изведывать дикую местность. Берег пологий и хорошо просматривается, но и тебя на нём тоже легко увидеть. Не стоит терять бдительность: кто знает, кто придёт на водопой. Пройдя достаточно, ты видишь впереди отвесный скалистый обрыв и водопад. Дальше идти не получится.</p>";
	public static final String TEXT_JUNGLE = "<p style=\"height: 150px\">Тропические джунгли - одно из самых труднопроходимых мест. Из за неровного рельефа и зарослей растений здесь очень легко заблудиться. Кто знает, что может ожидать в джунглях.</p>";
	public static final String TEXT_FAR_BEACH = "<p style=\"height: 150px\">Ты уже порядком изцарапался, пробираясь сквозь непролазные джунгли. Ты даже не можешь приблизительно прикинуть, сколько ты прошёл - так долго и так медленно ты пробирался. Когда ты уже подумал, что тебе никогда не выбраться из бесконечных джунглей, впереди мелькнуло что то светлое. Ты выходишь на очень укромный пляж, с одной стороны закрытый острыми скалами, с другой - скалистым утёсом и бухтой. Сзади стоит непролазная стена джунглей. Ты не видишь берега бухты - он скрывается за скалами с множеством ущелий. Течение здесь заметно сильнее, чем на том пляже, где ты очнулся некоторое время назад.</p>";
	public static final String TEXT_PLAIN = "<p style=\"height: 150px\">Ты идёшь по равнине. Она хорошо просматривается во все стороны. С одной стороны ты видишь темную полосу леса, с другой - скалистый обрыв, переходящий в горы. В этом отвесном обрыве ты видишь небольшой вход в пещеру. Вдали бегают несколько газелей. Они, безусловно, не несут угрозы. Вот кто может представлять угрозу - так это тот, кто на них охотится.</p>";
	public static final String TEXT_CAVE = "<p style=\"height: 150px\">В пещере тихо, темно, холодно и мокро. Никто не тревожит эту тишину. Летучие мыши спокойно висят на потолке. Здесь явно можно укрыться, но от темноты немного не по себе. Пещера уходит глубоко внутрь гор, главное, найти потом выход.</p>";
	public static final String TEXT_MOUNTAIN = "<p style=\"height: 150px\">Спустя долгое время тяжелого подъёма в гору, ты наконец-то достиг вершины, и можешь оглядеть окресности. Перед тобой простирается равнина, у её края протекает река, отделяя её от густых зарослей джунглей. За джунглями ты видишь лазурную гладь океана. Вверх по течению реки виден небольшой туман из брызг водопада. С другой стороны горы ты видишь поселение людей. Это первые люди, которых ты видишь в этой дикой местности. Хотя они не выглядят цивилизованно, ты разглядел у них пикапы, и, кажется, автоматы.</p>";
	public static final String TEXT_SETTLEMENT = "<p style=\"height: 150px\">Ты оказался в поселении. Тихо пробираясь вдоль него, ты понял, что лучше не попадаться на глаза местным жителям. Постоянные крики поодаль, дикий нрав настораживают. Надо бы поскорее убраться отсюда.</p>";

	//												Текст оповещений
	public static final String FIND_COMPASS = "<p>Среди хлама, прибитого к берегу, тебе попался компас</p>";
	public static final String FIND_LIGHTER = "<p>Среди хлама, прибитого к берегу, тебе попалась зажигалка</p>";
	public static final String FIND_BEACON = "<p>Ты нашёл радиомаяк. Он неисправен. Возможно, батарея разряжена. На шильде ты увидел надпись DC 12V 0.8A</p>";
	public static final String FIND_ROPE = "<p>Тебе попались прочные растения, похожие на верёвки. Из них можно сделать ловушку.</p>";
	public static final String YOU_FOUND_THE_FIT_BATTERY = "<p>Тебе на глаза попался подходящий пикап. Наверняка в нём есть аккумулятор.</p>";
	public static final String YOU_STOLE_THE_CAR_BATTERY = "<p>Тебе удалось украсть аккумулятор со старого пикапа. Теперь ты сможешь включить маяк. Но лучше его включать в подходящем месте</p>";
	public static final String FIND_FLARE_GUN = "<p>Среди многочисленного хлама ты нашёл сигнальную ракетницу и пять ракет к ней.</p>";
	public static final String FIND_TRUNCHEON = "<p>Тебе попалась толстая крепкая палка, которую можно использовать как дубину.</p>";
	public static final String YOU_FOUND_THE_MACHETE = "<p>Ты увидел неаккуратно оставленный мачете. Очень может пригодиться.</p>";
	public static final String YOU_STOLE_THE_MACHETE = "<p>Тебе удалось стащить неаккуратно оставленный мачете.</p>";
	public static final String FIND_SPEAR = "<p>Ты нашёл длинную, прямую и очень прочную палку. Ты заточил её острыми камнями и сделал себе копьё.</p>";
	public static final String FIND_SPRING = "<p>Ты наткнулся на источник с очень чистой кристально прозрачной водой.</p>";
	public static final String FIND_BERRIES = "<p>Тебе попались необычные тропические ягоды.</p>";
	public static final String FIND_FRUITS = "<p>Ты нашёл фрукты. Похоже, они съедобные.</p>";
	public static final String THE_SNARE_WAS_SET = "<p>Ловушка была установлена.</p>";
	public static final String YOU_CHECKED_THE_SNARE_AND_GOT_A_FOWL = "<p>В ловушке оказалась довольно крупная птица. Хорошо бы было её зажарить.</p>";
	public static final String THE_SNARE_TURNED_OUT_TO_BE_EMPTY = "<p>Ловушка оказалась пустой.</p>";
	public static final String YOU_HAVE_DRUNK_THE_WATER_FROM_THE_RIVER = "<p>Ты попил воды. На вкус она не очень.</p>";
	public static final String YOU_HAVE_DRUNK_THE_WATER_FROM_THE_SPRING = "<p>Ты попил воды из источника. На вкус она очень хорошая, такую воду можно встретить только в природных условиях.</p>";
	public static final String YOU_HAVE_EATEN_FRUITS = "<p>Ты поел разных фруктов. Это восстановило твои силы.</p>";
	public static final String YOU_FRIED_AND_ATE_THE_FOWL = "<p>Ты плотно поел пойманную дичь. Это хорошо восстановило твои силы</p>";
	public static final String YOU_FRIED_AND_ATE_THE_FISH = "<p>Ты плотно поел рыбой, пожаренной на костре. Это хорошо восстановило твои силы</p>";
	public static final String YOU_TURN_ON_THE_BEACON = "<p>Ты включил маяк.</p>";
	public static final String YOU_FOUND_OUT_THE_PLANE = "<p>Ты заметил разыскной самолёт. Но вопрос, заметил ли он тебя.</p>";
	public static final String YOU_SHOT_A_FLARE_GUN = "<p>Ты выстрелил сигнальной ракетой. Это резко увеличит шансы, что тебя заметят и спасут.</p>";
	public static final String YOU_LIT_A_FIRE = "Ты зажёг костёр. Теперь остаётся надеяться, что его заметят.";
	public static final String YOU_CAUGHT_A_FISH = "<p>Тебе удалось поймать рыбу. Теперь её нужно приготовить на костре.</p>";
	public static final String YOU_DID_NOT_CATCH_A_FISH = "<p>Не так то просто оказалось поразить рыбу копьём. Ты ничего не поймал, попробуй в другой раз.</p>";
	public static final String YOU_HAVE_BEEN_ATTACKED_BY_A_HEAVY_PREDATOR = "<p>На тебя внезапно напал тигр. Ты получил критичные раны, но остался жив. Похоже, ты не интересовал его в качестве еды. Ты чуть живой и не можешь ничего делать.</p>";
	public static final String YOU_HAVE_FOUGHT_OFF_A_HEAVY_PREDATOR = "<p>На тебя внезапно напал тигр. Ты победил его %s, но в схватке получил сильные раны. Ты не можешь ничего делать.</p>";
	public static final String YOU_HAVE_FLUSHED_OFF_A_HEAVY_PREDATOR = "<p>Ты столкнулся с тигром. Сигнальная ракета спугнула его, и он решил не связываться и удалился.</p>";
	public static final String YOU_HAVE_BEEN_ATTACKED_BY_A_PREDATOR = "<p>На тебя напал шакал. Он сильно покусал тебя и сбежал. Надо поскорее смываться отсюда, пока он не вернулся.</p>";
	public static final String YOU_HAVE_FOUGHT_OFF_A_PREDATOR = "<p>На тебя напал шакал. Тебе удалось отбиться от него %s. Получив раны, он удалился.</p>";
	public static final String YOU_HAVE_FLUSHED_OFF_A_PREDATOR = "<p>На тебя напал шакал. Ты стрельнул в него ракетницей, и он, поджав хвост, убежал.</p>";
	public static final String THE_SNAKE_BIT_YOU = "<p>Тебя укусила змея. Тебе удалось отсосать яд с места укуса, но тебя сильно лихорадит. Сейчас не до дел.</p>";
	public static final String YOU_GOT_LOST_BUT_COMPASS_BROUGHT_YOU_OUT = "<p>Ты заблудился, но компас помог тебе выйти</p>";
	public static final String YOU_GOT_LOST = "<p>Ты заблудился, и не знаешь, как выбраться.</p>";
	public static final String YOU_ARE_STILL_LOST = "<p>Ты пока что не нашёл выход.</p>";
	public static final String YOU_FOUND_THE_WAY_OUT = "<p>Ты наконец то нашёл выход.</p>";
	public static final String YOU_GOT_AN_INFECTION = "<p>Ты подхватил какую то инфекцию. Тебя лихорадит и совсем нет сил.</p>";

	//												Текст сообщений конца игры
	public static final String YOU_HAVE_BEEN_CAPTURED = "Тебя захватили в плен местные бандиты.";
	public static final String YOU_WERE_NOT_FOUND = "В результате поисково-спасательной операции тебя так и не нашли.";
	public static final String YOU_WERE_SAVED = "Тебя обнаружили и вскоре тебя забрали с острова.";
	//------------------------------------------------------------------------------------------------------------------

	//------------------------------------------------------------------------------------------------------------------
	//												Текст локаций
	//------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("all")
	public static final String START_BEACH_LOCATION_BUTTONS = new StringBuilder()
			.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG)
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_BEACH, "Вдоль пляжа"))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_FOREST, BUTTON_FOREST))
			.append(CLOSE_DIV_TAG)
			.toString();
	@SuppressWarnings("all")
	public static final String BEACH_LOCATION_BUTTONS = new StringBuilder()
			.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG)
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_RIVER, BUTTON_RIVER))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_FOREST, BUTTON_FOREST))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_JUNGLE, BUTTON_JUNGLE))
			.append(CLOSE_DIV_TAG)
			.toString();
	@SuppressWarnings("all")
	public static final String FOREST_LOCATION_BUTTONS = new StringBuilder()
			.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG)
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_PLAIN, BUTTON_PLAIN))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_RIVER, BUTTON_RIVER))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_BEACH, BUTTON_BEACH))
			.append(CLOSE_DIV_TAG)
			.toString();

	@SuppressWarnings("all")
	public static final String RIVER_LOCATION_BUTTONS = new StringBuilder()
			.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG)
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_JUNGLE, BUTTON_JUNGLE))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_PLAIN, BUTTON_PLAIN))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_FOREST, BUTTON_FOREST))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_BEACH, BUTTON_BEACH))
			.append(CLOSE_DIV_TAG)
			.toString();
	@SuppressWarnings("all")
	public static final String JUNGLE_LOCATION_BUTTONS = new StringBuilder()
			.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG)
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_FAR_BEACH, "Углубляться"))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_RIVER, BUTTON_RIVER))
			.append(CLOSE_DIV_TAG)
			.toString();
	@SuppressWarnings("all")
	public static final String FAR_BEACH_LOCATION_BUTTONS = new StringBuilder()
			.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG)
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_SETTLEMENT, "Бухта и скалы"))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_JUNGLE, BUTTON_JUNGLE))
			.append(CLOSE_DIV_TAG)
			.toString();
	@SuppressWarnings("all")
	public static final String PLAIN_LOCATION_BUTTONS = new StringBuilder()
			.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG)
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_RIVER, BUTTON_RIVER))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_CAVE, BUTTON_CAVE))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_MOUNTAIN, BUTTON_MOUNTAIN))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_FOREST, BUTTON_FOREST))
			.append(CLOSE_DIV_TAG)
			.toString();
	@SuppressWarnings("all")
	public static final String CAVE_LOCATION_BUTTONS = new StringBuilder()
			.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG)
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_MOUNTAIN, BUTTON_MOUNTAIN))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_PLAIN, BUTTON_PLAIN))
			.append(CLOSE_DIV_TAG)
			.toString();
	@SuppressWarnings("all")
	public static final String MOUNTAIN_LOCATION_BUTTONS = new StringBuilder()
			.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG)
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_SETTLEMENT, BUTTON_SETTLEMENT))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_PLAIN, BUTTON_PLAIN))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_CAVE, BUTTON_CAVE))
			.append(CLOSE_DIV_TAG)
			.toString();
	@SuppressWarnings("all")
	public static final String SETTLEMENT_LOCATION_BUTTONS = new StringBuilder()
			.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG)
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_MOUNTAIN, BUTTON_MOUNTAIN))
			.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_JUNGLE, BUTTON_JUNGLE))
			.append(CLOSE_DIV_TAG)
			.toString();
}
