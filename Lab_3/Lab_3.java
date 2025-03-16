import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

//---------------------------------------------------------------------------------------------------------------
// Запускаемый файл
public class Lab_3 {

    public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		ArrayList<filmSession> schedule = new ArrayList<filmSession>();
		initialize(cinemas, schedule);
		User admin = new User("admin", "admin", true);
		admin.setCinemaList(cinemas);
		admin.setSchedule(schedule);
		User user = new User();
		System.out.println("Авторизация:");
		while (true) {
	    	if (!user.isLoggedIn()) {                // авторизация/регистрация
				System.out.print("Введите логин: ");
				var login = in.next();
				System.out.print("Введите пароль: ");
				var password = in.next();
				if (login.equals(admin.getUsername())) {
		    		if (admin.login(login, password)) {
						user = admin;
						System.out.println("Вход выполнен успешно! Статус: администратор. Напишите <<help>>, чтобы вывести список команд.");
		    		}
		    		else 
						System.out.println("Пароль неверный. Попробуйте снова.");
				}
				else {
		    		user.setUsername(login);
		    		user.setPassword(password);
		    		user.setCinemaList(cinemas);
		    		user.setSchedule(schedule);
		    		user.login(login, password);
		    		System.out.println("Вход выполнен успешно! Статус: пользователь. Напишите <<help>>, чтобы вывести список команд.");
				}
	    	}
	    	else {
				var input = in.next();
				if (input.equals("help")) {
		    		if (user.isAdmin())
						System.out.println("Команды пользователя:\n'help'  - вывести список команд\n'schedule' - вывести все сеансы фильмов\n'cinemas' - вывести список кинотеатров\n'hallScheme' - вывести рассадку определенного зала\n'closest'  - вывести ближайший сеанс\n'reserve' - зарезервировать место\n'logOut'  - выйти из профиля\n'end'  - закончить программу\n\nКоманды администратора:\n'addCinema' - добавить кинотеатр\n'removeCinema' - убрать кинотеатр\n'addSession' - добавить сеанс фильма\n'removeSession' - убрать сеанс фильма\n'addHall' - добавить зал\n'removeHall' - убрать зал\n'removeRow' - убрать ряд из зала\n");
		    		else
						System.out.println("Команды пользователя:\n'help'  - вывести список команд\n'schedule' - вывести все сеансы фильмов\n'cinemas' - вывести список кинотеатров\n'hallScheme' - вывести рассадку определенного зала\n'closest'  - вывести ближайший сеанс\n'reserve' - зарезервировать место\n'logOut'  - выйти из профиля\n'end'  - закончить программу\n");
				}
				else if (input.equals("schedule"))
		    		System.out.print(user.scheduleToString());
				else if (input.equals("cinemas")) 
		    		System.out.println(user.cinemasToString());
				else if (input.equals("hallScheme")) {
		    		System.out.print("Введите номер кинотеатра: ");
		    		int cinemaIndexTemp = in.nextInt()-1;
		    		System.out.print("Введите номер зала из кинотеатра: ");
		    		System.out.println(user.hallScheme(cinemaIndexTemp, in.nextInt()-1));
				}
				else if (input.equals("closest")) {
		    		System.out.print("Название фильма: ");
		    		var title = in.next();
		    		var closest = user.findClosestScreening(title, Calendar.getInstance());
		    		if (closest != null) 
						System.out.print(closest.toString());
		    		else
						System.out.println("Ошибка! Нет подходящих сеансов");
				}
				else if (input.equals("reserve")) {
		    		System.out.println(user.scheduleToString());
		    		System.out.print("Введите номер сеанса: ");
		    		var num = in.nextInt()-1;
					user.makeReservation(num);
				}
				else if (input.equals("logOut"))
		    		user = new User();
				else if (input.equals("end"))
		    		break;
				else if (input.equals("addCinema") && user.isAdmin()) {
		    		System.out.print("Введите название нового кинотеатра: ");
					user.addCinema(in.next());
				}
				else if (input.equals("removeCinema") && user.isAdmin()) {
		    		System.out.print("ВНИМАНИЕ! Расписание сеансов также может измениться от удаления кинотеатра. Продолжить (да/нет)? ");
		    		input = in.next();
		    		if (input.equals("да")) {
						System.out.println(admin.cinemasToString());
						System.out.print("Введите номер кинотеатра, который хотите убрать: ");
						user.removeCinema(in.nextInt()-1);
		    		}
				}	
				else if (input.equals("addSession") && user.isAdmin()) {
		    		System.out.print("Введите название фильма: ");
		    		var title = in.next();
		    		System.out.print("Введите название кинотеатра: ");
		    		var cinemaIndex = in.nextInt()-1;
		    		System.out.print("Введите номер зала: ");
		    		var hallIndex = in.nextInt()-1;
		    		System.out.print("Введите длительность (в минутах): ");
		    		var duration = in.nextLong();
		    		System.out.print("Введите год начала сеанса: ");
		    		var year = in.nextInt();
		    		System.out.print("Введите месяц начала сеанса: ");
		    		var month = in.nextInt();
		    		System.out.print("Введите день начала сеанса: ");
		    		var day = in.nextInt();
		    		System.out.print("Введите час начала сеанса: ");
		    		var hour = in.nextInt();
		    		System.out.print("Введите минуту начала сеанса: ");
		    		var minute = in.nextInt();
		    		Calendar date = Calendar.getInstance();
		    		date.set(year, month, day, hour, minute, 0);
					user.addScreening(date, cinemaIndex, title, duration, hallIndex);
				}
				else if (input.equals("removeSession") && user.isAdmin()) {
		    		System.out.println(user.scheduleToString());
		    		System.out.print("Введите номер сеанса для удаления: ");
					user.removeScreening(in.nextInt()-1);
				}
				else if (input.equals("addHall") && user.isAdmin()) {
		    		System.out.print("Введите номер кинотеатра: ");
		    		int cinemaIndex = in.nextInt()-1;
		    		System.out.print("Введите количество рядов для нового зала: ");
		    		int rows = in.nextInt();
		    		int[] seatsPerRow = new int[rows];
		    		for (int i = 0; i < rows; i++) {
						System.out.print("Введите количество мест для ряда %d: ".formatted(i+1));
						seatsPerRow[i] = in.nextInt();
		    		}
					user.addHall(cinemaIndex, seatsPerRow);
				}
				else if (input.equals("removeHall") && user.isAdmin()) {
		    		System.out.print("Внимание! Расписание сеансов также может измениться от удаления зала. Продолжить (да/нет)? ");
		    		input = in.next();
		    		if (input.equals("да")) {
						System.out.print("Введите номер кинотеатра: ");
						int cinemaIndex = in.nextInt()-1;
						System.out.print("Введите номер зала: ");
						user.removeHall(cinemaIndex, in.nextInt()-1);
		    		}
				}
				else if (input.equals("removeRow") && user.isAdmin()) {
		    		System.out.print("Внимание! Удаление ряда может затронуть уже зарезервированные места. Продолжить (да/нет)? ");
		    		input = in.next();
		    		if (input.equals("да")) {
						System.out.print("Введите номер кинотеатра: ");
						int cinemaIndex = in.nextInt()-1;
						System.out.print("Введите номер зала в кинотеатре: ");
						int hallIndex = in.nextInt()-1;
						System.out.println(user.hallScheme(cinemaIndex, hallIndex));
						System.out.println("Введите номер ряда для удаления: ");
						user.removeRowFromHall(cinemaIndex, hallIndex, in.nextInt()-1);
		    		}
				}
				else
		    		System.out.println("Ошибка! Введенная вами команда не распознана. Попробуйте снова.");
	    	}
		}
		in.close();
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------

    public static void initialize(ArrayList<Cinema> cinemas, ArrayList<filmSession> schedule) {  // начальные данные

		Cinema cinema1 = new Cinema("Киносфера");
		cinemas.add(cinema1);
		Cinema cinema2 = new Cinema("Люмен");
		cinemas.add(cinema2);
		Cinema cinema3 = new Cinema("Киноленд");
		cinemas.add(cinema3);

		Hall hall1_1 = new Hall();
		Hall hall1_2 = new Hall();
		hall1_1.addRow(6);
		hall1_1.addRow(8);
		hall1_1.addRow(10);
		hall1_1.addRow(12);
		hall1_2.addRow(5);
		hall1_2.addRow(5);
		hall1_2.addRow(7);
		hall1_2.addRow(7);
		cinema1.getHalls().add(hall1_1);
		cinema1.getHalls().add(hall1_2);

		Hall hall2_1 = new Hall();
		Hall hall2_2 = new Hall();
		hall2_1.addRow(7);
		hall2_1.addRow(7);
		hall2_1.addRow(7);
		hall2_1.addRow(7);
		hall2_1.addRow(7);
		hall2_2.addRow(5);
		hall2_2.addRow(6);
		hall2_2.addRow(7);
		hall2_2.addRow(8);
		hall2_2.addRow(9);
		cinema2.getHalls().add(hall2_1);
		cinema2.getHalls().add(hall2_2);

		Hall hall3_1 = new Hall();
		hall3_1.addRow(25);
		cinema3.getHalls().add(hall3_1);

		Calendar session1_date = Calendar.getInstance();
		session1_date.add(Calendar.HOUR, 2);
		filmSession session1 = new filmSession(session1_date, cinema1, "Анора", 120, 0);
		schedule.add(session1);

		Calendar session2_date = Calendar.getInstance();
		session2_date.add(Calendar.HOUR, 5);
		filmSession session2 = new filmSession(session2_date, cinema2, "Онегин", 141, 0);
		schedule.add(session2);

		Calendar session3_date = Calendar.getInstance();
		session3_date.add(Calendar.HOUR, 2);
		filmSession session3 = new filmSession(session3_date, cinema3, "Граф Монте-Кристо", 178, 0);
		schedule.add(session3);
    }
}